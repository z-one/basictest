package com.spring.test.sync;

public class TestSychronized
{
    public static void main(String[] args)
    {
        synchronized (TestSychronized.class)
        {
        }
        m();
    }
    
    public static synchronized void m()
    {
        
    }
}
