package com.lm.study.hutool;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.io.watch.WatchMonitor;
import cn.hutool.core.io.watch.Watcher;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.CharsetUtil;
import org.junit.Test;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

import static com.lm.study.hutool.PrintUtil.pl;

/**
 * @author chenxin
 * @date 2019/7/15 13:47
 * <p>
 * IoUtil 流操作工具类
 * FileUtil 文件读写和操作的工具类。
 * FileTypeUtil 文件类型判断工具类
 * WatchMonitor 目录、文件监听，封装了JDK1.7中的WatchService
 * ClassPathResource针对ClassPath中资源的访问封装
 * FileReader 封装文件读取
 * FileWriter 封装文件写入
 */
public class IOTest {
    @Test
    public void IoUtilTest() {
        BufferedInputStream in = FileUtil.getInputStream("D:\\dgg项目资料\\canal.properties");
        BufferedOutputStream out = FileUtil.getOutputStream("D:\\dgg项目资料\\canal_1.properties");
        long copySize = IoUtil.copy(in, out, IoUtil.DEFAULT_BUFFER_SIZE);
        pl(copySize);
        IoUtil.getReader(in, CharsetUtil.CHARSET_UTF_8);
        IoUtil.close(in);
        IoUtil.close(out);

    }

    @Test
    public void FileUtilTest() {
        FileUtil fileUtil = new FileUtil();

    }

    @Test
    public void FileTypeUtil() {
        File file = FileUtil.file("D:\\dgg项目资料\\canal_1.properties");
        String type = FileTypeUtil.getType(file);
        pl(type);

    }

    @Test
    public void FileReader() {
        FileReader fileReader = new FileReader("D:\\dgg项目资料\\canal_1.properties");
        String result = fileReader.readString();
        System.out.println(result);

    }

    @Test
    public void FileWriter() {
        FileWriter fileWriter = new FileWriter("D:\\dgg项目资料\\canal_2.properties");
        fileWriter.write("test",true);
        PrintWriter printWriter = fileWriter.getPrintWriter(true);
        printWriter.print("test333");
        IoUtil.close(printWriter);
        FileReader fileReader = new FileReader("D:\\dgg项目资料\\canal_2.properties");
        pl(fileReader.readString());

    }

    @Test
    public void WatchMonitor() throws InterruptedException {
        File file = FileUtil.file("D:\\dgg项目资料\\canal_2.properties");
        WatchMonitor watchMonitor = WatchMonitor.create(file,WatchMonitor.ENTRY_MODIFY);
        final CountDownLatch countDownLatch = new CountDownLatch(3);

        watchMonitor.setWatcher(new Watcher() {
            @Override
            public void onCreate(WatchEvent<?> event, Path currentPath) {
                countDownLatch.countDown();
                Object obj = event.context();
                Console.log("创建：{}-> {}", currentPath, obj);
            }

            @Override
            public void onModify(WatchEvent<?> event, Path currentPath) {
                countDownLatch.countDown();
                Object obj = event.context();
                Console.log("修改：{}-> {}", currentPath, obj);
            }

            @Override
            public void onDelete(WatchEvent<?> event, Path currentPath) {
                countDownLatch.countDown();
                Object obj = event.context();
                Console.log("删除：{}-> {}", currentPath, obj);
            }

            @Override
            public void onOverflow(WatchEvent<?> event, Path currentPath) {
                countDownLatch.countDown();
                Object obj = event.context();
                Console.log("Overflow：{}-> {}", currentPath, obj);
            }
        });
        watchMonitor.setMaxDepth(3);
        watchMonitor.start();

        countDownLatch.await();
    }

    @Test
    public void ClassPathResource() throws IOException {
        ClassPathResource resource = new ClassPathResource("canal_2.properties");
        Properties properties = new Properties();
        properties.load(resource.getStream());
        System.out.println(properties);
        StringWriter stringWriter = new StringWriter();
        properties.store(stringWriter,"comment");
        System.out.println(stringWriter);


    }


    public static class FileCopyTest {


        public static void main(String[] args) throws Exception {
            String sourcePath = "D:\\dgg项目资料\\安装部署.zip";
            String destPath1 = "D:\\dgg项目资料\\安装部署1.zip";
            String destPath2 = "D:\\dgg项目资料\\安装部署2.zip";
            String destPath3 = "D:\\dgg项目资料\\安装部署3.zip";

            long t1 = System.currentTimeMillis();
            traditionalCopy(sourcePath, destPath1);
            long t2 = System.currentTimeMillis();
            System.out.println("传统IO方法实现文件拷贝耗时:" + (t2 - t1) + "ms");

            nioCopy(sourcePath, destPath2);
            long t3 = System.currentTimeMillis();
            System.out.println("利用NIO文件通道方法实现文件拷贝耗时:" + (t3 - t2) + "ms");


            nioCopy2(sourcePath, destPath3);
            long t4 = System.currentTimeMillis();
            System.out.println("利用NIO文件内存映射及文件通道实现文件拷贝耗时:" + (t4 - t3) + "ms");



        }


        private static void nioCopy2(String sourcePath, String destPath) throws Exception {
            File source = new File(sourcePath);
            File dest = new File(destPath);
            if (!dest.exists()) {
                dest.createNewFile();
            }

            FileInputStream fis = new FileInputStream(source);
            FileOutputStream fos = new FileOutputStream(dest);
            FileChannel sourceCh = fis.getChannel();
            FileChannel destCh = fos.getChannel();
            MappedByteBuffer mbb = sourceCh.map(FileChannel.MapMode.READ_ONLY, 0, sourceCh.size());
            destCh.write(mbb);
            sourceCh.close();
            destCh.close();

        }


        private static void traditionalCopy(String sourcePath, String destPath) throws Exception {
            File source = new File(sourcePath);
            File dest = new File(destPath);
            if (!dest.exists()) {
                dest.createNewFile();
            }

            FileInputStream fis = new FileInputStream(source);
            FileOutputStream fos = new FileOutputStream(dest);
            byte[] buf = new byte[512];
            int len = 0;
            while ((len = fis.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }

            fis.close();
            fos.close();
        }


        private static void nioCopy(String sourcePath, String destPath) throws Exception {
            File source = new File(sourcePath);
            File dest = new File(destPath);
            if (!dest.exists()) {
                dest.createNewFile();
            }

            FileInputStream fis = new FileInputStream(source);
            FileOutputStream fos = new FileOutputStream(dest);
            FileChannel sourceCh = fis.getChannel();
            FileChannel destCh = fos.getChannel();
            destCh.transferFrom(sourceCh, 0, sourceCh.size());
            sourceCh.close();
            destCh.close();

        }

    }


}
