package com.spring.test.tricks.listorvector;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;

/**
 * 请参考这篇文章进行的性能测试
 * https://www.javacodegeeks.com/2010/08/java-best-practices-vector-arraylist.html
 *
 * @author nullable
 * @version [版本号, 2018年2月7日]
 */
public class ListOrVectorTest
{
    public static void main(String[] args)
    {
        System.out.println("List test");
        List<String> list = new ArrayList<String>(5000);
        testList(list);
        
        System.out.println("================");
        System.out.println("Vector test");
        List<String> vector = new Vector<String>(5000);
        testList(vector);
        
        System.out.println("================");
        System.out.println("Linked test");
        List<String> Linkedlist = new LinkedList<String>();
        testList(Linkedlist);
    }
    
    private static void testList(final List<String> list)
    {
        long start = System.currentTimeMillis();
        
        final CountDownLatch latch = new CountDownLatch(5000);
        
        for (int i = 0; i < 5000; i++)
        {
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    list.add(System.currentTimeMillis() + "i");
                    // SleepUtils.second(2);
                    latch.countDown();
                }
            }).start();
        }
        
        try
        {
            latch.await();
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
        
        System.out.println(list.getClass() + "耗时：" + (System.currentTimeMillis() - start) + " 总量：" + list.size());
    }
}
