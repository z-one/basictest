package com.spring.test;


import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.test.bean.TestBean;
import com.spring.test.son.TestSon;



/**
 * Hello world!
 */
public class App
{
    // private static ApplicationContext factory;

    public static void main(String[] args)
    {
        ApplicationContext factory = new ClassPathXmlApplicationContext(
            "classpath:applicationContext.xml");

        TestBean bean = factory.getBean("testBean", TestBean.class);
        TestBean bean2 = factory.getBean("testBean2", TestBean.class);
        TestSon son = factory.getBean("testSon", TestSon.class);
        bean2.print();
        bean.print();
        son.print();
        int r = new Date().compareTo(new Date());

        System.out.println(r);
        // System.out.println(bean.getBeanName());
        // System.out.println("Hello World!");

        // IntStream.of(new int[] {1, 2, 3}).forEach(System.out::println);
        // IntStream.range(1, 3).forEach(System.out::println);
        // IntStream.rangeClosed(1, 3).forEach(System.out::println);
        // String[] strArray = new String[] {"a", "b", "c"};
        // Stream stream = Stream.of(strArray);
        // 1. Array
        // String[] strArray1 = (String[])stream.toArray(String[]::new);
        // 2. Collection
        // List<String> list1 = stream.collect(Collectors.toList());
        // List<String> list2 = stream.collect(Collectors.toCollection(ArrayList::new));
        // Set set1 = stream.collect(Collectors.toSet());
        // Stack stack1 = stream.collect(Collectors.toCollection(Stack::new));
        // 3. String
        // String str = stream.collect(Collectors.joining()).toString();
        // System.out.println(get());
    }

    @SuppressWarnings("finally")
    public static int get()
    {
        try
        {
            System.out.println("try");
            return 1;
        }
        catch (Exception e)
        {
            System.out.println("catch");
            return 2;
        }
        finally
        {
            System.out.println("finall");
            return 3;
        }
    }
}
