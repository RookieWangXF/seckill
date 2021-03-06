package cn.rookie.dao;

import cn.rookie.entity.Seckill;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by Rookie on 2016/5/13.
 * Package_name is cn.rookie.dao
 * Description:
 */

/**
 * 配置spring和junit整合，junit启动时加载springIOC容器
 * spring-test,junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

    //private ApplicationContext ctx;
    @Resource
    private SeckillDao seckillDao;

    /*@Before
    public void setUp() {
        ctx = new ClassPathXmlApplicationContext("/spring/spring-dao.xml");
        this.seckillDao = (SeckillDao) ctx.getBean("seckillDao");
    }*/


    @Test
    public void reduceNumber() throws Exception {

        Date killTime = new Date();
        int updateCount = seckillDao.reduceNumber(1000L, killTime);
        System.out.println("[updateCount]"+updateCount);
    }

    @Test
    public void queryById() throws Exception {
        long id = 1000;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println("[seckill.getName()]:"+seckill.getName());
        System.out.println("[seckill]:"+seckill);
    }

    @Test
    public void queryAll() throws Exception {
        List<Seckill> seckills = seckillDao.queryAll(0, 100);
        for (Seckill seckill : seckills) {
            System.out.println("[seckill]:"+seckill);
        }
    }


}
