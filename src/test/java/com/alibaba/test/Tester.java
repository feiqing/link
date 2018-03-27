package com.alibaba.test;

/**
 * @author jifang.zjf@alibaba-inc.com (FeiQing)
 * @version 1.0
 * @since 2018-03-27 18:55:00.
 */
/*
 *
 *  * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

import com.alipay.jarslink.api.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 模块加载和执行测试
 *
 * @author tengfei.fangtf
 * @version $Id: ModuleManagerTest.java
 * <p>
 * v 0.1 2017年06月20日 3:24 PM tengfei.fangtf Exp $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:META-INF/spring/jarslink.xml"})
public class Tester {

    @Autowired
    private ModuleManager moduleManager;

    @Autowired
    private ModuleLoader moduleLoader;

    @Test
    public void shouldDoAction() throws IOException {
        Module findModule = moduleLoader.load(buildModuleConfig());
        Module removedModule = moduleManager.register(findModule);
        //4.1:查找和执行Action

        String actionName = "myAction";
        String result = findModule.doAction(actionName, "feiqing");
        System.out.println(result);

        //4.2:查找和执行Action
        Action<String, String> action = findModule.getAction(actionName);
        Assert.assertNotNull(action);
        result = action.execute("lot of");
        System.out.println(result);
        Assert.assertNotNull(result);

        //卸载模块
        moduleManager.remove(findModule.getName());
    }

    /**
     * 构建模块配置信息
     */
    public static ModuleConfig buildModuleConfig() throws IOException {
        return buildModuleConfig(true);
    }

    public static ModuleConfig buildModuleConfig(boolean enabled) throws IOException {
        String packageName = "com.alibaba.test";

        ModuleConfig moduleConfig = new ModuleConfig();
        moduleConfig.setName("demo");
        moduleConfig.setEnabled(enabled);
        // moduleConfig.setOverridePackages(ImmutableList.of("com.alibaba.test"));
        moduleConfig.setVersion("1.0.0.20170621");
        Map<String, Object> properties = new HashMap();
        properties.put("url", "127.0.0.1");
        moduleConfig.setProperties(properties);
        moduleConfig.setPackageName(packageName);
        return moduleConfig;
    }

}