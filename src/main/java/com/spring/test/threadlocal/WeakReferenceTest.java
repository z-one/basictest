
package com.spring.test.threadlocal;

import java.lang.ref.WeakReference;

/**
 * 模拟ThreadLocal为什么在gc是Entry<ThreadLocal,Object>中的key为何没有别回收呢？
 *
 */
public class WeakReferenceTest
{
    public static void main(String[] args)
    {
        TTOO t = new TTOO();
        WeakReference<TTOO> weakReference = new WeakReference<TTOO>(t);
        t = null;
        System.gc();

        if (weakReference.get() == null)
        {
            System.out.println("weakReference已经被GC回收");
        }
        else
        {
            System.out.println(weakReference.get());
        }

        while (true)
        {
        }
    }
}

class TTOO
{
}