package lh.premain;

import javassist.*;
import lh.annotations.Getter;
import lh.annotations.Setter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

import static lh.premain.util.javassist.JavaBeanSetterAndGetterUtil.getterMethod;
import static lh.premain.util.javassist.JavaBeanSetterAndGetterUtil.setterMethod;

/**
 * @author ${USER}
 */
public class Agent {
    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(
                    ClassLoader loader,
                    String className,
                    Class<?> classBeingRedefined,
                    ProtectionDomain protectionDomain,
                    byte[] classfileBuffer) {
                if (className.equals("lh/test/Apple")) {
                    ClassPool classPool = ClassPool.getDefault();
                    classPool.appendClassPath(new LoaderClassPath(loader));

                    try {
                        CtClass ctApp = classPool.makeClass(new ByteArrayInputStream(classfileBuffer));
                        CtField[] declaredFields = ctApp.getDeclaredFields();
                        for (Object annotation : ctApp.getAvailableAnnotations()) {
                            if (annotation instanceof Setter) {
                                for (CtField declaredField :declaredFields) {
                                    try {
                                        ctApp.addMethod(setterMethod(declaredField));
                                    } catch (CannotCompileException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            }
                            if (annotation instanceof Getter) {
                                for (CtField declaredField :declaredFields) {
                                    try {
                                        ctApp.addMethod(getterMethod(declaredField));
                                    } catch (CannotCompileException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            }
                        }
                        ctApp.writeFile();
                        return ctApp.toBytecode();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                        throw new RuntimeException(e);
                    } catch (CannotCompileException e) {
                        throw new RuntimeException(e);
                    } catch (NotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
                return null;
            }
        });
    }
}