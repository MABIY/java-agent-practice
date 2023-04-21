package lh.premain.util.javassist;

import javassist.CannotCompileException;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;

import static lh.premain.util.StringUtil.capitalizeFirstLetter;

/**
 * @author lh
 */
public class JavaBeanSetterAndGetterUtil {
    public static CtMethod setterMethod(CtField ctField) throws CannotCompileException {
       return CtNewMethod.setter("set" + capitalizeFirstLetter(ctField.getName()), ctField);
    }

    public static CtMethod getterMethod(CtField ctField) throws CannotCompileException {

        return CtNewMethod.setter("get" + capitalizeFirstLetter(ctField.getName()), ctField);
    }
}
