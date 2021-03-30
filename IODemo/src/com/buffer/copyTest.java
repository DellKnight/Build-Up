package com.buffer;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class copyTest {
    public static void main(String[] args) throws IOException {
        //字节输入流
        File source = new File("D:\\test.txt");
        FileInputStream fps = new FileInputStream(source);
        //字节输出流
        File dest = new File("D:\\test1.txt");
        FileOutputStream fos = new FileOutputStream(dest);
        //定义一个缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //获取输入流的管道
        FileChannel fpsChannel = fps.getChannel();
        //获取输出流的管道
        FileChannel fosChannel = fos.getChannel();
        while(true){
            //写模式转换
            byteBuffer.clear();

            int flag = fpsChannel.read(byteBuffer);
            if(flag==-1){
                break;
            }
            //读模式转换
            byteBuffer.flip();
            //缓冲区向管道写入数据
            fosChannel.write(byteBuffer);
        }
        fpsChannel.close();
        fosChannel.close();
        System.out.println("文件复制完成");
    }
}
