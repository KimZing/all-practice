package com.kimzing.java.classloader;

import java.io.*;

/**
 * 自定义classloader.
 *
 * @author KimZing - kimzing@163.com
 * @since 2018-12-31 13:49
 */
public class MyClassLoader extends ClassLoader {

    private String path;

    private String name;

    public MyClassLoader(String path, String name) {
        this.path = path;
        this.name = name;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String classpath = path + name + ".class";
        InputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            in = new FileInputStream(classpath);
            out = new ByteArrayOutputStream();
            int i = -1;
            while ((i = in.read()) != -1) {
                out.write(i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return defineClass("Kim", out.toByteArray(), 0, out.size());
    }


}
