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
package com.alibaba.link.impl;

import com.alibaba.link.Module;
import com.alibaba.link.ModuleConfig;
import com.alibaba.link.ModuleService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.MalformedURLException;

/**
 *
 * @author tengfei.fangtf
 * @version $Id: ModuleServiceTest.java, v 0.1 2017年07月21日 10:33 AM tengfei.fangtf Exp $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:jarslink.xml"})
public class ModuleServiceTest{

    @Autowired
    private ModuleService moduleService;

    @Test
    public void shouldLoadAndRegister() throws MalformedURLException {
        //先加载并注册模块
        ModuleConfig moduleConfig = ModuleManagerTest.buildModuleConfig();
        Module module = moduleService.loadAndRegister(moduleConfig);
        Assert.assertNotNull(module);
        Assert.assertNotNull(module.getCreation());
        Assert.assertNotNull(module.getChildClassLoader());
        Assert.assertEquals(moduleConfig.getName(),module.getName());
        Assert.assertEquals(moduleConfig.getVersion(),module.getVersion());

        //再禁用模块
        module = moduleService.loadAndRegister(ModuleManagerTest.buildModuleConfig(false));
        Assert.assertNull(module);

    }

}