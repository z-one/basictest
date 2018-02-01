package com.TestSpring;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class AppTest
{
    @Test
    public void test()
    {
        System.out.println("test!");
    }
    
    
    @Before
    public void testBefore()
    {
        System.out.println("test Before!");
    }
    
    @After
    public void testAfter()
    {
        System.out.println("test Afetr!");
    }
}
