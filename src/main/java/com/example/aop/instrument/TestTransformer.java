package com.example.aop.instrument;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

/**
 * ClassFileTransformer测试
 */
public class TestTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
            ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        System.out.println("Transforming " + className);
        try {
            ClassPool classPool = ClassPool.getDefault();
            CtClass ctClass = classPool.get("com.example.aop.instrument.Base");
            CtMethod ctMethod = ctClass.getDeclaredMethod("process");
            ctMethod.insertBefore("{ System.out.println(\"start\"); }");
            ctMethod.insertAfter("{ System.out.println(\"after\"); }");
            return ctClass.toBytecode();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new byte[0];
        }
    }
}
