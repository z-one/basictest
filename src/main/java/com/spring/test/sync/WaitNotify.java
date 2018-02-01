package com.spring.test.sync;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.spring.test.utils.SleepUtils;


public class WaitNotify
{
    static boolean flag = true;
    
    static Object lock = new Object();
    
    public static void main(String[] args)
    {
        Thread waitThread = new Thread(new Wait(), "WaitingThread");
        waitThread.start();
        
        SleepUtils.second(1);
        
        Thread notifyThread = new Thread(new Notify(), "NotifyThread");
        notifyThread.start();
    }
    
    static class Wait implements Runnable
    {
        @Override
        public void run()
        {
            synchronized (lock)
            {
                while (flag)
                {
                    try
                    {
                        System.out.println(Thread.currentThread() + "flag is true. wait@"
                            + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        lock.wait();
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread() + "flag is false. running@"
                    + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }
    
    static class Notify implements Runnable
    {
        @Override
        public void run()
        {
            synchronized (this)
            {
                System.out.println(Thread.currentThread() + "hold lock. notify@"
                    + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                lock.notifyAll();
                flag = false;
                SleepUtils.second(5);
            }
            
            // lock again
            synchronized (lock)
            {
                System.out.println(Thread.currentThread() + "hold lock again. sleep@"
                    + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                SleepUtils.second(5);
            }
        }
        
    }
}
