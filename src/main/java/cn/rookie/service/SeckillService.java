package cn.rookie.service;

import cn.rookie.dto.Exposer;
import cn.rookie.dto.SeckillExecution;
import cn.rookie.entity.Seckill;
import cn.rookie.exception.RepeatKillException;
import cn.rookie.exception.SeckillCloseException;
import cn.rookie.exception.SeckillException;

import java.util.List;

/**
 * Created by Rookie on 2016/5/16.
 * Package_name is cn.rookie.service
 * Description: 站在使用者的角度设计接口
 * 1. 方法定义粒度，要将方法定义的很明确
 * 2. 参数， 简练直接
 * 3. 返回类型  友好
 */
public interface SeckillService {

    /**
     * 查询所有秒杀记录
     * @return
     */
    public List<Seckill> getSeckillList();

    /**
     * 查询单个秒杀记录
     * @param seckillId
     * @return
     */
    public Seckill getById(long seckillId);

    /**
     * 秒杀开启时候，输出秒杀接口地址，
     * 否则输出系统时间和秒杀时间
     * @param seckillId
     */
    public Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀操作
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException,RepeatKillException,SeckillCloseException;
}
