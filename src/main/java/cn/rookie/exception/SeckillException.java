package cn.rookie.exception;

/**
 * Created by Rookie on 2016/5/16.
 * Package_name is cn.rookie.exception
 * Description:秒杀业务相关异常
 */
public class SeckillException extends RuntimeException {

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
