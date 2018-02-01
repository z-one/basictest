
/*
 * 文件名：TestFactoryBean.java 版权：Copyright by <company> 描述： 修改人：Administrator 修改时间：2017年10月10日 跟踪单号：
 * 修改单号： 修改内容：
 */

package com.spring.test.bean;


import org.springframework.beans.factory.FactoryBean;


public class TestFactoryBean implements FactoryBean<TestBean>
{

    @Override
    public TestBean getObject()
        throws Exception
    {
        return new TestBean("create TestBean by FactoryBean!");
    }

    @Override
    public Class<?> getObjectType()
    {
        return TestBean.class;
    }

    @Override
    public boolean isSingleton()
    {
        return true;
    }

}
