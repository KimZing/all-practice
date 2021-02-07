package com.kimzing.generator.split;

import com.kimzing.utils.file.FileUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.kimzing.generator.param.Parameters.*;

/**
 * 拆分包符合当前项目结构.
 *
 * @author KimZing - kimzing@163.com
 * @since 2020/7/6 22:15
 */
public class SplitOperation {

    private static String src = generaPath + File.separator + "src";
    private static String entry = generaPath + File.separator + "entry" + File.separator + "src";
    private static String api = generaPath + File.separator + "api" + File.separator + "src";
    private static String service = generaPath + File.separator + "service" + File.separator + "src";
    private static String common = File.separator + "main" + File.separator +
            "java" + File.separator + "com" + File.separator+ "kimzing" + File.separator+ moduleName;
    private static String resources = File.separator + "main" + File.separator + "resources";

    public static void split() throws IOException {
        deleteIfExistAndCreate();
        copySrc();
        splitEntry();
        splitApi();
        splitService();
        FileUtil.deleteDir(new File(src));
    }

    public static void copySrc() {
        FileUtil.copyDir(src, entry);
        FileUtil.copyDir(src, api);
        FileUtil.copyDir(src, service);
    }

    public static void deleteIfExistAndCreate() throws IOException {
        FileUtil.deleteDir(new File(entry));
        FileUtil.deleteDir(new File(api));
        FileUtil.deleteDir(new File(service));
        Files.createDirectories(Paths.get(entry));
        Files.createDirectories(Paths.get(api));
        Files.createDirectories(Paths.get(service));
    }

    public static void splitEntry() throws IOException {
        FileUtil.deleteDir(new File(entry + common + File.separator + "domain"));
        FileUtil.deleteDir(new File(entry + common + File.separator + "repository"));
        FileUtil.deleteDir(new File(entry + common + File.separator + "service"));
        FileUtil.deleteDir(new File(entry + resources));
    }

    public static void splitApi() throws IOException {
        FileUtil.deleteDir(new File(api + common + File.separator + "controller"));
        FileUtil.deleteDir(new File(api + common + File.separator + "service" + File.separator + packageName + File.separator + "impl"));
        FileUtil.deleteDir(new File(api + common + File.separator + "repository"));
        Files.walk(Paths.get(api + common + File.separator + "domain" + File.separator + packageName))
                .forEach(p -> {
                    if (p.getFileName().toString().endsWith("PO.java")) {
                        FileUtil.deleteFile(p.toFile());
                    }
                });
        FileUtil.deleteDir(new File(api + resources));
    }

    public static void splitService() throws IOException {
        FileUtil.deleteDir(new File(service + common + File.separator + "controller"));
        FileUtil.deleteDir(new File(service + common + File.separator + "domain"));
        Files.walk(Paths.get(service + common + File.separator + "service" + File.separator + packageName))
                .forEach(p -> {
                    if (p.getFileName().toString().endsWith("Service.java")) {
                        FileUtil.deleteFile(p.toFile());
                    }
                });
    }

}
