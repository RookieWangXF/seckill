package cn.rookie.dao;

import cn.rookie.entity.Seckill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Rookie on 2016/5/13.
 * Package_name is cn.rookie.dao
 * Description:
 */

public interface SeckillDao {
    /**
     * 建库存
     * @param seckillId
     * @param killTime
     * @return 如果影响行数>1,表示更新的记录行数
     */
    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

    /**
     * 查询根据id
     * @param seckillId
     * @return
     */
    Seckill queryById(long seckillId);

    /**
     * 查询商品列表根据偏移量
     * @param offset
     * @param limit
     * @return
     */
    List<Seckill> queryAll(@Param("offset") int offset,@Param("limit") int limit);
}
