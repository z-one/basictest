
/*
 * 文件名：TestSon.java 版权：Copyright by <company> 描述： 修改人：Administrator 修改时间：2017年9月21日 跟踪单号： 修改单号：
 * 修改内容：
 */

package com.spring.test.son;

import com.spring.test.bean.TestBean;

public class TestSon extends TestBean
{
    @Override
    public void print()
    {
        System.out.println("TestSon子类！");
    }
}
