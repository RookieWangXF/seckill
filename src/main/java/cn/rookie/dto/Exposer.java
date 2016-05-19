package cn.rookie.dto;

/**
 * Created by Rookie on 2016/5/16.
 * Package_name is cn.rookie.dto
 * Description: 暴露秒杀地址DTO
 */
public class Exposer {
    //是否开启秒杀
    private boolean exposed;

    private String md5;

    private long seckillId;

    /**
     * 系统当前时间
     */
    private long now;

    //开启时间
    private long start;

    //结束时间
    private long end;

    public Exposer(boolean exposed, String md5, long seckillId) {
        this.exposed = exposed;
        this.md5 = md5;
        this.seckillId = seckillId;
    }

    public Exposer(boolean exposed, long seckillId, long now, long start, long end) {

        this.exposed = exposed;
        this.now = now;
        this.start = start;
        this.end = end;
    }

    public Exposer(boolean exposed, long seckillId) {
        this.exposed = exposed;
        this.seckillId = seckillId;
    }

}
