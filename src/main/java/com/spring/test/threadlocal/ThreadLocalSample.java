package com.spring.test.threadlocal;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 这里是ThreadLocal代码自带的示例.
 */
public class ThreadLocalSample
{
    public static void main(String[] args)
    {
        // main Thread
        incrementSameThreadId();
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                incrementSameThreadId();
            }
        }).start();
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                incrementSameThreadId();
            }
        }).start();
    }
    private static void incrementSameThreadId()
    {
        try
        {
            for (int i = 0; i < 5; i++)
            {
                System.out.println(Thread.currentThread() + "_" + i + ",threadId:" + ThreadLocalId.get());
            }
        }
        finally
        {
            // 使用后请清除
            ThreadLocalId.remove();
        }
    }
}

/**
 * inner class.
 */
class ThreadLocalId
{
    // Atomic integer containing the next thread ID to be assigned
    private static final AtomicInteger nextId = new AtomicInteger(0);

    // Thread local variable containing each thread's ID
    private static final ThreadLocal<Integer> threadId = new ThreadLocal<Integer>()
    {
        @Override
        protected Integer initialValue()
        {
            return nextId.getAndIncrement();
        }
    };

    // Returns the current thread's unique ID, assigning it if necessary
    public static int get()
    {
        return threadId.get();
    }

    // remove currentid
    public static void remove()
    {
        threadId.remove();
    }
}