package cn.rookie.dao;

import cn.rookie.entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by Rookie on 2016/5/14.
 * Package_name is cn.rookie.dao
 * Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKillDaoTest {

    @Resource
    private SuccessKilledDao successKillDao;

    @Test
    public void insertSuccessKilled() throws Exception {
        long id = 1002L;
        long phone = 123432246L;
        int res = successKillDao.insertSuccessKilled(id, phone);
        System.out.println("[res]:" + res);
    }

    @Test
    public void queryByIdWithSeckill() throws Exception {
        long id = 1002L;
        long phone = 123432246L;
        SuccessKilled successKilled = successKillDao.queryByIdWithSeckill(id, phone);
        System.out.println("[successKilled]:" + successKilled);
    }

}