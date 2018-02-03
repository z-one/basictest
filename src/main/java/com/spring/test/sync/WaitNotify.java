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
    
    /**
     * <pre>
     *  等待方准守原则：
     *  1.获取对象锁
     *  2.如果条件不满足，那么调用对象的wait方法，被通知后仍需要条件检查 
     *  3.条件满足则执行对应代码逻辑
     *  
     *  伪代码：
     *  synchronized(对象){
     *    while(条件不满足){
     *       对象.wait();
     *    }
     *  }
     *  对应业务逻辑
     * </pre>
     * 
     * @author nullable
     */
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
    
    /**
     * <pre>
     * 1.获得对象的锁
     * 2.改变条件
     * 3.通知所有等待在对象的线程
     * 
     * 伪代码：
     *  synchronized(对象){
     *   改变条件
     *   对象.notifyAll();
     *  }
     * </pre>
     *
     * @author Administrator
     * @version [版本号, 2018年2月3日]
     * @see [相关类/方法]
     * @since [产品/模块版本]
     */
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
