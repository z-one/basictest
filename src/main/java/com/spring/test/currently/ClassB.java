package com.spring.test.currently;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassB
{
    @Autowired
    private ClassC c;

    public ClassB()
    {
        
    }

    public ClassB(ClassC c)
    {
        this.c = c;
    }

//    public void setC(ClassC c)
//    {
//        this.c = c;
//    }
}
