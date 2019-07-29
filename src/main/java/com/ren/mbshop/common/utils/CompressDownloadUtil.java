package com.ren.mbshop.common.utils;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author: target
 * @date: 2019/7/26 12:29
 * @description:
 */

@Slf4j
public class CompressDownloadUtil {

    private CompressDownloadUtil() {
    }

    /**
     * 设置下载响应头
     */
    public static HttpServletResponse setDownloadResponse(HttpServletResponse response, String downloadName) {
        response.reset();
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;fileName*=UTF-8''" + downloadName);
        return response;
    }

    /**
     * 字符串转换为整型数组
     */
    public static Integer[] toIntegerArray(String param) {
        return Arrays.stream(param.split(","))
                .map(Integer::valueOf)
                .toArray(Integer[]::new);
    }

    /**
     * 将多个文件压缩到指定输出流中
     *
     * @param files        需要压缩的文件列表
     * @param outputStream 压缩到指定的输出流
     */
    public static void compressZip(List<File> files, OutputStream outputStream) {
        ZipOutputStream zipOutStream = null;
        try {
            //-- 包装成ZIP格式输出流
            zipOutStream = new ZipOutputStream(new BufferedOutputStream(outputStream));
            // -- 设置压缩方法
            zipOutStream.setMethod(ZipOutputStream.DEFLATED);
            //-- 将多文件循环写入压缩包
            for (int i = 0; i < files.size(); i++) {
                File file = files.get(i);
                FileInputStream filenputStream = new FileInputStream(file);
                byte[] data = new byte[(int) file.length()];
                filenputStream.read(data);
                //-- 添加ZipEntry，并ZipEntry中写入文件流，这里，加上i是防止要下载的文件有重名的导致下载失败
                zipOutStream.putNextEntry(new ZipEntry(i + file.getName()));
                zipOutStream.write(data);
                filenputStream.close();
                zipOutStream.closeEntry();
            }
        } catch (IOException e) {
            log.info("error msg:{}", e);
        } finally {
            try {
                if (Objects.nonNull(zipOutStream)) {
                    zipOutStream.flush();
                    zipOutStream.close();
                }
                if (Objects.nonNull(outputStream)) {
                    outputStream.close();
                }
            } catch (IOException e) {
                log.info("error msg:{}", e);
            }
        }
    }

    /**
     * 下载文件
     *
     * @param outputStream 下载输出流
     * @param zipFilePath  需要下载文件的路径
     */
    public static void downloadFile(OutputStream outputStream, String zipFilePath) {
        File zipFile = new File(zipFilePath);
        if (!zipFile.exists()) {
            //-- 需要下载压塑包文件不存在
            return;
        }
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(zipFile);
            byte[] data = new byte[(int) zipFile.length()];
            inputStream.read(data);
            outputStream.write(data);
            outputStream.flush();
        } catch (IOException e) {
            log.info("error msg:{}", e);
        } finally {
            try {
                if (Objects.nonNull(inputStream)) {
                    inputStream.close();
                }
                if (Objects.nonNull(outputStream)) {
                    outputStream.close();
                }
            } catch (IOException e) {
                log.info("error msg:{}", e);
            }
        }
    }

    /**
     * 删除指定路径的文件
     *
     * @param filepath
     */
    public static void deleteFile(String filepath) {
        File file = new File(filepath);
        deleteFile(file);
    }

    /**
     * 删除指定文件
     *
     * @param file
     */
    public static void deleteFile(File file) {
        //-- 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
        }
    }

}
