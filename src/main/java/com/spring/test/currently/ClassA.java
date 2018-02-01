package com.spring.test.currently;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassA
{
    @Autowired
    private ClassB b;

    public ClassA()
    {

    }

    public ClassA(ClassB b)
    {
        this.b = b;
    }

//    public void setB(ClassB b)
//    {
//        this.b = b;
//    }
}
