package com.example.aop.instrument;

import java.lang.instrument.Instrumentation;

/**
 * Test Agent
 */
public class TestAgent {

    public static void agentmain(String args, Instrumentation inst) {
        //指定我们自己定义的Transformer，在其中利用Javassist做字节码替换
        inst.addTransformer(new TestTransformer(), true);
        try {
            //重定义类并载入新的字节码
            inst.retransformClasses(Base.class);
            System.out.println("Agent load done.");
        } catch (Exception e) {
            System.out.println("Agent load failed!");
        }
    }
}
