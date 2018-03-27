package com.alipay.jarslink.api.exception;

/**
 * @author jifang.zjf@alibaba-inc.com (FeiQing)
 * @version 1.0
 * @since 2018-03-27 23:35:00.
 */
public class JarLinkException extends RuntimeException {

    private static final long serialVersionUID = -9187252144042974041L;

    public JarLinkException() {
    }

    public JarLinkException(String message) {
        super(message);
    }

    public JarLinkException(String message, Throwable cause) {
        super(message, cause);
    }

    public JarLinkException(Throwable cause) {
        super(cause);
    }

    public JarLinkException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
