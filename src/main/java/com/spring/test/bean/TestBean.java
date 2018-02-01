
/*
 * 文件名：TestBean.java 版权：Copyright by <company> 描述： 修改人：zoneC 修改时间：2017年5月7日
 */

package com.spring.test.bean;

public class TestBean
{

    private String beanName;

    public TestBean()
    {
        this.beanName = "test";
    }

    public TestBean(String beanName)
    {
        this.setBeanName(beanName);
    }

    public void setBeanName(String beanName)
    {
        this.beanName = beanName;
    }

    public String getBeanName()
    {
        return beanName;
    }

    public void print()
    {
        System.out.println("TestBean父类！" + beanName);
    }
}
