import javassist.*;
import lh.javassist.Hello;

import java.io.IOException;

/**
 * @author lh
 */
public class Test {
//    @org.junit.jupiter.api.Test
    void name() throws NotFoundException, CannotCompileException, InstantiationException, IllegalAccessException {
        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get("lh.javassist.Hello");
        CtMethod m = cc.getDeclaredMethod("say");
        m.insertBefore("{ System.out.println(\"lh.javassist.Hello.say():\"); }");
        Class c = cc.toClass();
        Hello h = (Hello)c.newInstance();
        h.say();
    }

//    @org.junit.jupiter.api.Test
    void changeString() throws NotFoundException, CannotCompileException, IOException {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("java.lang.String");
        CtField f= new CtField(CtClass.intType,"hiddenValue",cc);
        f.setModifiers(Modifier.PUBLIC);
        cc.addField(f);
        cc.writeFile(".");
    }
}
