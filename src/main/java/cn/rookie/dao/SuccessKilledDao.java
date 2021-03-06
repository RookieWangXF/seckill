package cn.rookie.dao;

import cn.rookie.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Rookie on 2016/5/13.
 * Package_name is cn.rookie.dao
 * Description:
 */
public interface SuccessKilledDao {

    /**
     * 插入购买明细，可以过滤重复
     * @param seckillId
     * @param userPhone
     * @return 插入的行数
     */
    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

    /**
     * 根据id查询Successkilled并携带秒杀产品对象
     * @param seckillId
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);


}
