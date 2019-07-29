package com.ren.mbshop.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Description //TODO  文件压缩，目录创建，删除工具类
 * @Author qch
 * @Date 11:19 2018/8/31
 * @Param
 * @return
 **/
@Slf4j
public class FileUtil {

    private static final int BUFFER_SIZE = 2 * 1024;

    /**
     * @return void
     * @Description //TODO 文件夹压缩成ZIP
     * @Author qch
     * @Date 12:09 2018/9/3
     * @Param [fileDir(压缩文件夹路径 ( 打包的文件)), outStream(压缩文件输出流(zip)), level（压缩级别1-9）, KeepDirStructure（是否保存目录结构）]
     * KeepDirStructure  true:保留目录结构;false:所有文件跑到压缩包根目录下
     **/
    public static void toZip(String fileDir, OutputStream outStream, int level, boolean KeepDirStructure) throws RuntimeException {
        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(outStream);
            //设置压缩级别 （1（压缩快，压缩比不高）- 9（压缩慢，压缩比高））
            if (level != 0) {
                zos.setLevel(level);
            }
            File sourceFile = new File(fileDir);
            //递归压缩文件
            compress(sourceFile, zos, sourceFile.getName(), KeepDirStructure);
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils", e);
        } finally {
            if (zos != null) {
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * @return void
     * @Description //TODO 文件夹压缩成ZIP（带压缩级别参数设置）
     * @Author qch
     * @Date 12:06 2018/9/3
     * @Param [fileDir(压缩文件夹路径 ( 打包的文件)), outStream(压缩文件输出流(zip)), KeepDirStructure（是否保存目录结构）]
     * KeepDirStructure  true:保留目录结构;false:所有文件跑到压缩包根目录下
     **/
    public static void toZip(String fileDir, OutputStream outStream, boolean KeepDirStructure) throws RuntimeException {
        toZip(fileDir, outStream, 0, KeepDirStructure);
    }


    /**
     * 递归压缩方法
     *
     * @param sourceFile       源文件
     * @param zos              zip输出流
     * @param name             压缩后的名称
     * @param KeepDirStructure 是否保留原来的目录结构,true:保留目录结构;
     *                         false:所有文件跑到压缩包根目录下
     * @throws Exception
     */
    private static void compress(File sourceFile, ZipOutputStream zos, String name, boolean KeepDirStructure) throws Exception {

        byte[] buf = new byte[BUFFER_SIZE];
        //判断是不是文件
        if (sourceFile.isFile()) {
            /**
             *将所有文件跑到压缩包根目录下,避免文件重复报错，给文件名加上时间戳
             *注意：不保留目录结构可能会出现同名文件,会压缩失败
             **/
            if (!KeepDirStructure) {
                name = getNewName(name);
            }
            // 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
            zos.putNextEntry(new ZipEntry(name));
            // copy文件到zip输出流中
            int len;
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(sourceFile));
            while ((len = in.read(buf)) != -1) {
                zos.write(buf, 0, len);
            }
            // Complete the entry
            zos.closeEntry();
            in.close();
        } else {
            File[] listFiles = sourceFile.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                // 需要保留原来的文件结构时,需要对空文件夹进行处理
                if (KeepDirStructure) {
                    // 空文件夹的处理
                    zos.putNextEntry(new ZipEntry(name + "/"));
                    // 没有文件，不需要文件的copy
                    zos.closeEntry();
                }
            } else {
                for (File file : listFiles) {
                    // 判断是否需要保留原来的文件结构
                    if (KeepDirStructure) {
                        // 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
                        // 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
                        compress(file, zos, name + "/" + file.getName(), KeepDirStructure);
                    } else {
                        compress(file, zos, file.getName(), KeepDirStructure);
                    }
                }
            }
        }
    }

    /**
     * @return java.lang.String
     * @Description //TODO 重新组装下文件名
     * @Author qch
     * @Date 12:17 2018/9/3
     * @Param [fileName]
     **/
    public static String getNewName(String fileName) {
        String name = fileName.substring(0, fileName.lastIndexOf("."));
        String type = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        fileName = name + System.currentTimeMillis() + type;
        return fileName;
    }

    /**
     * 方法描述:创建Zip文件 (不处理目录)
     * 压缩完成后将删除源文件
     *
     * @param fileList 源文件路径列表
     * @param zipPath  目标文件路径
     */
    public static void createZip(List fileList, String zipPath) throws IOException {
        //输出流
        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipPath)));
            writeZip(fileList, zos);
        } catch (Exception exp) {
            exp.printStackTrace();
        } finally {
            if (zos != null) {
                zos.close();
            }
        }
    }

    /**
     * 方法描述:创建Zip文件
     * 压缩完成后将删除源文件
     *
     * @param filePath 源文件路径
     * @param zipPath  目标文件路径
     */
    public static void createZip(String filePath, String zipPath) throws IOException {
        List fileList = new ArrayList();
        fileList.add(filePath);
        //输出流
        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipPath)));
            writeZip(fileList, zos);
        } catch (Exception exp) {
            exp.printStackTrace();
        } finally {
            try {
                if (zos != null) {
                    zos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void writeZip(List fileList, ZipOutputStream zos) {
        byte[] buf = new byte[1024];
        int length = 0;
        BufferedInputStream bis = null;

        for (int i = 0; i < fileList.size(); i++) {
            File file = new File(ObjectUtils.toString(fileList.get(i)));
            if (file.exists() && !file.isDirectory()) {
                try {
                    bis = new BufferedInputStream(new FileInputStream(file));
                    zos.putNextEntry(new ZipEntry(file.getName()));
                    while ((length = bis.read(buf)) > 0) {
                        zos.write(buf, 0, length);
                    }
                } catch (Exception exp) {
                    exp.printStackTrace();
                } finally {
                    try {
                        if (bis != null) {
                            bis.close();
                        }
                    } catch (Exception e) {
                    }
                }
                file.delete();
            }
        }
    }


    /**
     * @return java.io.File
     * @Description //TODO  递归创建文件夹
     * @Author qch
     * @Date 18:15 2018/8/31
     * @Param [dirName, CourrentDir]  CourrentDir为null时创建根文件夹，否则在当前文件夹内，创建子文件夹
     **/
    public static File createDir(String dirName, File CourrentDir) {
        File newTempDir;
        if (CourrentDir == null) {
            File temp = new File(System.getProperty("java.io.tmpdir"));
            newTempDir = new File(temp, dirName);
        } else {
            newTempDir = new File(CourrentDir, dirName);

        }
        newTempDir.mkdir();
        return newTempDir;

    }

    /**
     * @return boolean
     * @Description //TODO 递归删除目录下的所有文件及子目录下所有文件
     * @Author qch
     * @Date 20:19 2018/8/28
     * @Param [dir] 将要删除的文件目录
     **/
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }


    /**
     * 下载文件流写入临时目录
     **/
    public static void write(String fileName, File file, String uri) {
        if (StringUtils.isBlank(fileName) || file == null || StringUtils.isBlank(uri)) {
            return;
        }

        BufferedOutputStream fos = null;
        InputStream in = null;
        BufferedInputStream buf = null;
        try {
            File temp0 = createDifferentName(fileName, file);
            temp0.createNewFile();
            //用BufferedOutputStream处理
            fos = new BufferedOutputStream(new FileOutputStream(temp0));
            //拿到文件流
            URL url = new URL(uri);
            URLConnection conn = url.openConnection();
            in = conn.getInputStream();
            buf = new BufferedInputStream(in);
            int len = -1;
            byte[] b = new byte[1024];
            while ((len = in.read(b)) != -1) {
                fos.write(b, 0, len);
            }

        } catch (Exception e) {
            log.error("Failed to download file, uri is " + uri, e);

        } finally {
            if (buf != null) {
                try {
                    buf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }


    /**
     * @return java.io.File
     * @Description //TODO 处理文件名相同的文件
     * @Author qch
     * @Date 18:07 2019/3/15
     * @Param [fileName, path]
     **/
    public static File createDifferentName(String fileName, File path) {
        File file = new File(path, fileName);
        try {
            int i = 1;
            while (file.exists()) {
                String name = fileName.substring(0, fileName.indexOf("."));
                String suffix = fileName.substring(fileName.lastIndexOf("."));
                if (i == 1) {
                    fileName = name + "(" + i + ")" + suffix;
                } else {
                    name = name.substring(0, name.lastIndexOf("(" + (i - 1) + ")"));
                    fileName = name + "(" + i + ")" + suffix;
                }
                file = new File(path, fileName);
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }


    public static void main(String[] args) throws IOException {

        File file = new File("C:\\Users\\xyj88\\Desktop\\供方测试公司1");
        File file1 = new File("C:\\Users\\xyj88\\Desktop\\供方测试公司.zip");
        FileOutputStream out = new FileOutputStream(file1);

        FileUtil.toZip(file.getPath(), out, true);
    }
}
