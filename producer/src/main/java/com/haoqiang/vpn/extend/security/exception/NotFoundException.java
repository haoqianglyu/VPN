package com.haoqiang.vpn.extend.security.exception;

import org.springframework.stereotype.Service;

/**
 * @author Haoqiang Lyu
 * @date 2019-06-25 10:32
 */

public class NotFoundException extends Exception {
    public NotFoundException(String msg) {
        super(msg);
    }
    public NotFoundException() {
        super();
    }

    public NotFoundException(String msg, Exception e) {
        super(msg + " because of " + e.toString());
    }
}
