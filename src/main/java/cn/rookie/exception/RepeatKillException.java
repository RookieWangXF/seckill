package cn.rookie.exception;

/**
 * Created by Rookie on 2016/5/16.
 * Package_name is cn.rookie.exception
 * Description:重复秒杀异常 运行期异常
 */
public class RepeatKillException extends SeckillException {

    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
