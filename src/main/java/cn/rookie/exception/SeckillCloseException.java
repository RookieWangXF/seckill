package cn.rookie.exception;

/**
 * Created by Rookie on 2016/5/16.
 * Package_name is cn.rookie.exception
 * Description:秒杀关闭异常
 */
public class SeckillCloseException extends SeckillException {

    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
