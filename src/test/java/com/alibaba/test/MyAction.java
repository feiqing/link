package com.alibaba.test;

import com.alipay.jarslink.api.Action;

/**
 * @author jifang.zjf@alibaba-inc.com (FeiQing)
 * @version 1.0
 * @since 2018-03-27 18:53:00.
 */
public class MyAction implements Action<String, String> {

    @Override
    public String execute(String actionRequest) {
        return "Hello " + actionRequest;
    }

    @Override
    public String getActionName() {
        return "MyAction";
    }
}
