package com.spring.test.currently;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassC
{
    @Autowired
    private ClassA a;

    public ClassC()
    {
        
    }

    public ClassC(ClassA a)
    {
        this.a = a;
    }

//    public void setA(ClassA a)
//    {
//        this.a = a;
//    }
    
}
