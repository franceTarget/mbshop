package com.ren.mbshop.common.utils;

import java.io.*;

public class FileUtil {

    public static String readUrl(String path) throws IOException {
        // 1.创建文件对象
        File file = new File(path);
        // 2.创建字符输入流
        FileInputStream inputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String str = "";
        StringBuilder sb = new StringBuilder();
        while ((str = bufferedReader.readLine()) != null) {
            sb.append(str);
        }
        return sb.toString();
    }
}
