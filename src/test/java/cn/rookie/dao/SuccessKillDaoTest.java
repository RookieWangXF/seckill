package cn.rookie.dao;

import cn.rookie.entity.Successkilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by Rookie on 2016/5/14.
 * Package_name is cn.rookie.dao
 * Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKillDaoTest {

    @Resource
    private SuccessKillDao successKillDao;

    @Test
    public void insertSuccessKilled() throws Exception {
        long id = 1000;
        long phone = 123432451;
        int res = successKillDao.insertSuccessKilled(id, phone);
        System.out.println(res);
    }

    @Test
    public void queryByIdWithSeckill() throws Exception {

    }

}