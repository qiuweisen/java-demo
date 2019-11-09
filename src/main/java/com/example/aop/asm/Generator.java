package com.example.aop.asm;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.asm.ClassReader;
import org.springframework.asm.ClassVisitor;
import org.springframework.asm.ClassWriter;

/**
 * 字节码生成
 */
public class Generator {

    public static void main(String[] args) throws Exception {
        //读取
        ClassReader classReader = new ClassReader("com/example/aop/asm/Base");
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        //处理，通过classVisitor修改类
        ClassVisitor classVisitor = new MyClassVisitor(classWriter);
        classReader.accept(classVisitor, ClassReader.SKIP_DEBUG);
        byte[] data = classWriter.toByteArray();
        //输出
        File file = new File("target/classes/com/example/aop/asm/Base.class");
        FileOutputStream fout = new FileOutputStream(file);
        fout.write(data);
        fout.close();
        System.out.println("now generator cc success!!!!!");
    }

}
