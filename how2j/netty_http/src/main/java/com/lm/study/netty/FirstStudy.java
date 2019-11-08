package com.lm.study.netty;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.TimeUnit;

/**
 * @author chenxin
 * @date 2019/7/28 10:52
 */
public class FirstStudy {

    public static void main(String[] args) throws IOException, InterruptedException {
       // new FirstStudy().selector();
        asynchronousFileChannel();
        Thread.currentThread().join();
        Thread.currentThread().setDaemon(true);
    }
    public void selector() throws IOException, InterruptedException {
       // boolean flag = SelectionKey.OP_ACCEPT & SelectionKey.OP_CONNECT;
    /*    SelectionKey selectionKey = null;
        selectionKey.interestOps();
        selectionKey.isAcceptable();
        selectionKey.readyOps();
        selectionKey.attach("aaa");
        selectionKey.attachment();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress("",80));
*/
        Pipe pipe = Pipe.open();
        Pipe.SinkChannel sinkChannel = pipe.sink();
        String newDate = "new String to write";
        ByteBuffer buf = ByteBuffer.allocate(40);
        buf.clear();
        buf.put(newDate.getBytes());
        buf.flip();
        Pipe.SourceChannel sourceChannel = pipe.source();
        ByteBuffer byteBuffer = ByteBuffer.allocate(48);
        Thread thread = new Thread(){
            @Override
            public void run() {
                do {
                    int byteRead = 0;
                    try {
                        byteRead = sourceChannel.read(byteBuffer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if(byteRead != 0){
                        System.out.println("number: " +byteRead);
                        System.out.println(new String(byteBuffer.array()));
                        byteBuffer.clear();
                    }
                }while (true);

            }
        };
        thread.start();
        while (buf.hasRemaining()){
            sinkChannel.write(buf);
        }
        buf.clear();
        buf.put("gjqagjoegjeogje".getBytes());
        buf.flip();
        TimeUnit.SECONDS.sleep(1);

        sinkChannel.write(buf);






    }


    public static void asynchronousFileChannel() throws IOException {
        Path path = Paths.get("filecopy.xml");
        AsynchronousFileChannel fileChannel =
                AsynchronousFileChannel.open(path, StandardOpenOption.READ);

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        long position = 0;
        System.out.println(Thread.currentThread().toString());
        fileChannel.read(buffer, position, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println("result = "+result);
                System.out.println(Thread.currentThread().toString());
                attachment.flip();
                System.out.println(new String(attachment.array()));
                attachment.clear();
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {

            }
        });

    }




















































}
