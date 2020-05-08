package com.wuhe.background.exception;

/**
 * @author wuhe
 * @create 2019/9/30 0030-上午 11:27
 */
public class UserNotExitException extends RuntimeException {
    public UserNotExitException() {
        super("用户不存在！");
    }
}
