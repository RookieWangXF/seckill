package cn.rookie.service.impl;

import cn.rookie.dao.SeckillDao;
import cn.rookie.dao.SuccessKillDao;
import cn.rookie.dto.Exposer;
import cn.rookie.dto.SeckillExecution;
import cn.rookie.entity.Seckill;
import cn.rookie.entity.Successkilled;
import cn.rookie.enums.SeckillStateEnum;
import cn.rookie.exception.RepeatKillException;
import cn.rookie.exception.SeckillCloseException;
import cn.rookie.exception.SeckillException;
import cn.rookie.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by Rookie on 2016/5/16.
 * Package_name is cn.rookie.service.impl
 * Description:
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    private Logger logger = LoggerFactory.getLogger(SeckillServiceImpl.class);

    @Autowired
    private SeckillDao seckillDao;

    @Autowired
    private SuccessKillDao successKillDao;

    private final String slat = "adsfasgdrfg345234656*^&64352asdf";
    @Override
    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0,4);
    }

    @Override
    public Seckill getById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    @Override
    public Exposer exportSeckilllUrl(long seckillId) {
        Seckill seckill = seckillDao.queryById(seckillId);
        if (seckill == null) {
            return new Exposer(false, seckillId);
        }
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date nowTime = new Date();
        if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
            return new Exposer(false,seckillId,nowTime.getTime(),startTime.getTime(),endTime.getTime());
        }
        //转化特定的字符串的过程
        String md5 = getMD5(seckillId);
        return new Exposer(true, md5, seckillId);
    }

    private String getMD5(long seckillId){
        String base = seckillId + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    @Override
    @Transactional
    /**
     * 使用注解控制事务方法的优点：
     * 1. 开发团队达成一致约定，明确标注事务方法的编程风格。
     * 2. 保证事务方法的执行时间尽可能的短，不要穿插其他的网络操作，RPC/HTTP请求或者剥离到事务方法外部。
     * 3. 不是所有的方法都需要事务，如只有一条修改操作，只读操作，不需要事务控制。
     */
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException, RepeatKillException, SeckillCloseException {
        if (md5 == null || !md5.equals(getMD5(seckillId))) {
            throw new SeckillException("seckill data rewrite");
        }

        //执行秒杀逻辑：减库存+记录购买行为
        Date nowTime = new Date();
        try {
            int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
            if (updateCount <= 0) {
                //没有更新到记录,意味着秒杀结束了
                throw new SeckillCloseException("seckill is closed");
            } else {
                //记录购买行为
                int insertCount = successKillDao.insertSuccessKilled(seckillId, userPhone);
                if (insertCount <= 0) {
                    throw new RepeatKillException("seckill repeated");
                } else {
                    //秒杀成功
                    Successkilled successkilled = successKillDao.queryByIdWithSeckill(seckillId, userPhone);
                    return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, successkilled);
                }

            }
        } catch (SeckillCloseException e) {
            throw e;
        } catch (RepeatKillException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new SeckillException("seckill inner error:" + e.getMessage());
        }
    }
}
