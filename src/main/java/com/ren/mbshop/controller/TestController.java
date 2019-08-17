package com.ren.mbshop.controller;

import com.ren.mbshop.common.response.Response;
import com.ren.mbshop.common.utils.FileUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Random;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @ApiOperation("下载测试")
    @GetMapping("/download")
    public Response downloadAssetFile(HttpServletRequest request,
                                      HttpServletResponse response) {
//        List<String> filePaths = new ArrayList<>();
//
//        filePaths.add("http://localhost:8800/demo.txt");
//        filePaths.add("http://localhost:8800/test.sql");

        String fileName = "下载测试";

        //压缩包
        File zipDir = null;

        try {
            zipDir = File.createTempFile(String.valueOf(new Random().nextInt(10000)), ".zip");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //根目录
        File rootDir = FileUtil.createDir(fileName, null);

        //创建txt文件夹
        File txtDir = FileUtil.createDir("文本文件", rootDir);
        //创建sql文件夹
        File sqlDir = FileUtil.createDir("sql文件", rootDir);

        FileUtil.write("new1.txt", txtDir, "http://localhost:8800/demo.txt");
        FileUtil.write("new2.sql", sqlDir, "http://localhost:8800/test.sql");
        //压缩的文件暂放在临时目录
        try {
            FileUtil.toZip(rootDir.getPath(), new FileOutputStream(zipDir), true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(zipDir));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            String fileName1 = fileName + ".zip";
            String fileName2 = new String(fileName1.getBytes(), "ISO-8859-1");
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName2);
            response.addHeader("Content-Length", "" + zipDir.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (Exception e) {
            zipDir.delete();
            log.error("compress zip fail:", e);
        } finally {
            zipDir.delete();
            FileUtil.deleteDir(rootDir);
        }
        return Response.ok("下载成功");
    }
}
