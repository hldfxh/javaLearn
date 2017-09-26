package test;

import com.sun.corba.se.impl.orbutil.ObjectWriter;

import javax.sound.midi.Soundbank;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

/**
 * Created by fanxuehui on 2017/7/27.
 */
public class ReflectionTest {

    public static void main(String[] args) {
        String name;
        if (args.length > 0) {
            name = args[0];
        } else {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter class name(e.g. java.util.Date):");
            name = in.next();
        }
        name="java.lang.Double";

        try {
            Class cl = Class.forName(name);
            Class supercl = cl.getSuperclass();
            String modifiers = Modifier.toString(cl.getModifiers());
            if (modifiers.length()>0) {
                System.out.printf(modifiers+" ");
            }
            System.out.printf("class "+name);
            if (supercl!=null && supercl != Object.class) {
                System.out.printf(" extends "+supercl.getName());
            }
            System.out.printf("\n{\n");
            printConstructors(cl);
        } catch (Exception e) {

        }
        System.exit(0);
    }

    private static void printConstructors(Class cl) {
        Constructor[] constructors = cl.getDeclaredConstructors();

        for (Constructor constructor : constructors) {
            String name = constructor.getName();
            System.out.printf("   ");
            String modifiers = Modifier.toString(constructor.getModifiers());
            if (modifiers.length()>0) {
                System.out.printf(modifiers+" ");
            }
            System.out.printf(name+"(");

            Class[] paramTypes = constructor.getExceptionTypes();
            for (int i = 0; i < paramTypes.length ; i++) {
                if (i>0) {
                    System.out.printf(", ");
                }
                System.out.printf(paramTypes[i].getName());
            }
            System.out.println(");");

        }
    }

    private static void printMethods(Class cl) {
        Method[] methods = cl.getMethods();

        for (Method method:methods) {
            Class retType = method.getReturnType();
            String name = method.getName();
            System.out.printf("  ");

            String modifiers = Modifier.toString(method.getModifiers());
            if (modifiers.length()>0) {
                System.out.printf(modifiers+" ");
            }
            System.out.printf("");

        }
    }

}
