
package com.spring.test.threadlocal;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.ReflectionUtils;

/**
 * @author ponyzi
 *
 * 模拟ThreadLocal为什么在gc是Entry<ThreadLocal,Object>中的key为何没有别回收呢？
 */
public class TestThreadLocalLeak {

    public static void main(String[] args)
            throws InterruptedException {

        //  为了复现key被回收的场景，我们使用临时变量
        ThreadLocalMemory memeory = new ThreadLocalMemory();

        // 调用
        incrementSameThreadId(memeory);

        System.out.println("GC前：key:" + memeory.threadId);
        System.out.println("GC前：value-size:" + refelectThreadLocals(Thread.currentThread()));

        // 设置为null，调用gc并不一定触发垃圾回收，但是可以通过java提供的一些工具进行手工触发gc回收。
        memeory.threadId = null;
        System.gc();

        System.out.println("GC后：key:" + memeory.threadId);
        System.out.println("GC后：value-size:" + refelectThreadLocals(Thread.currentThread()));

        //进一步回收，保证ThreadLocal实例被回收
        memeory=null;
        System.gc();

        // 模拟线程一直运行
        while (true) {
        }
    }

    /**
     * 使用threadlocal
     * @param memeory
     */
    private static void incrementSameThreadId(final ThreadLocalMemory memeory) {
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread() + "_" + i + ",threadId:" + memeory.get().size());
            }
        } finally {
            // 使用后请清除，这里为了复现内存泄漏，故意不回收
            // ThreadLocalMemory.remove();
        }
    }

    /**
     * 利用反射获取ThreadLocal对应的值
     *
     * @param t
     * @return
     */
    public static Object refelectThreadLocals(Thread t) {
        try {
            // Thread
            Field field = ReflectionUtils.findField(Thread.class, "threadLocals");
            field.setAccessible(true);
            Object localmap = ReflectionUtils.getField(field, t);

            // ThreadLocalMap.Entry[]
            Field entryField = ReflectionUtils.findField(localmap.getClass(), "table");
            entryField.setAccessible(true);
            Object[] entry = (Object[]) ReflectionUtils.getField(entryField, localmap);

            List<Object> list = new ArrayList<>();
            for (Object o : entry) {
                if (o != null)
                    list.add(o);
            }

            List<Object> result = new ArrayList<>();
            for (Object o : list) {

                // Entry.value
                Field entryValue = ReflectionUtils.findField(o.getClass(), "value");
                entryValue.setAccessible(true);
                Object keyvalue = ReflectionUtils.getField(entryValue, o);
                if (keyvalue instanceof ArrayList) {
                    result.add(keyvalue);
                }
            }
            return ((ArrayList<?>) result.get(0)).size();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

class ThreadLocalMemory {
    // Thread local variable containing each thread's ID
    public ThreadLocal<List<Object>> threadId = new ThreadLocal<List<Object>>() {
        @Override
        protected List<Object> initialValue() {
            List<Object> list = new ArrayList<Object>();
            for (int i = 0; i < 10000; i++) {
                list.add(String.valueOf(i));
            }
            return list;
        }
    };

    // Returns the current thread's unique ID, assigning it if necessary
    public List<Object> get() {
        return threadId.get();
    }

    // remove currentid
    public void remove() {
        threadId.remove();
    }
}


