package org.example;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * @author ${USER}
 */
public class Agent {
    public static void premain(String agentArgs, Instrumentation inst){
        System.out.println("test1");
    }
}