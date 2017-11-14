package test;

import java.net.URL;
import java.net.URLClassLoader;

public class LoaderTest {

    public static void main(String[] args) {
        try {
            URLClassLoader a =(URLClassLoader) ClassLoader.getSystemClassLoader();
            URL[] urLs = a.getURLs();
            System.out.println(ClassLoader.getSystemClassLoader());
            System.out.println(ClassLoader.getSystemClassLoader().getParent());
            System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
