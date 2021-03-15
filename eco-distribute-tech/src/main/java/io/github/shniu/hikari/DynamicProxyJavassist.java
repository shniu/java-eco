package io.github.shniu.hikari;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.bytecode.AccessFlag;
import javassist.bytecode.ClassFile;
import javassist.bytecode.FieldInfo;

import java.lang.reflect.Field;

/**
 * https://www.baeldung.com/javassist
 */
public class DynamicProxyJavassist {

    public static void main(String[] args) throws CannotCompileException {
        ClassFile classFile = new ClassFile(false,
                "io.github.shniu.hikari.GenerateClass01", null);
        classFile.setInterfaces(new String[]{"java.lang.Cloneable"});

        FieldInfo field = new FieldInfo(classFile.getConstPool(), "id", "I");
        field.setAccessFlags(AccessFlag.PUBLIC);
        classFile.addField(field);

        ClassPool classPool = ClassPool.getDefault();
        Field[] fields = classPool.makeClass(classFile).toClass().getFields();

        assert fields[0].getName().equals("id");
    }
}
