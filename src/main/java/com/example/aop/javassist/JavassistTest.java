package com.example.aop.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

/**
 * javassist使用测试
 */
public class JavassistTest {

    public static void main(String[] args) throws Exception {
        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = classPool.get("com.example.aop.javassist.Base");
        CtMethod ctMethod = ctClass.getDeclaredMethod("process");
        ctMethod.insertBefore("{ System.out.println(\"start\"); }");
        ctMethod.insertAfter("{ System.out.println(\"after\"); }");
        // 输出文件做比较
        ctClass.writeFile("target/classes");
        Class newCtClass = ctClass.toClass();
        Base instance = (Base) newCtClass.newInstance();
        instance.process();
    }
}
