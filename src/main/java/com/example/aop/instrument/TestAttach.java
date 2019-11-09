package com.example.aop.instrument;

import java.io.IOException;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

/**
 * Attach测试
 */
public class TestAttach {

    public static void main(String[] args)
            throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        // 传入目标 JVM pid
        VirtualMachine virtualMachine = VirtualMachine.attach("87484");
        virtualMachine.loadAgent("out/artifacts/test_agent/test-agent.jar");
    }
}
