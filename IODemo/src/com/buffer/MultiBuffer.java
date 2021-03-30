package com.buffer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Ping.Dai
 */
public class MultiBuffer {
    public static void main(String[] args) throws Exception {
        //定义一个文件输入流
        FileInputStream fileInputStream = new FileInputStream("D:\\test.txt");
        FileChannel fi = fileInputStream.getChannel();
        //定义一个文件输出流
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\test1.txt");
        FileChannel fo = fileOutputStream.getChannel();
        //定义多个缓冲区
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(1024);
        ByteBuffer byteBuffer2 = ByteBuffer.allocate(1024);
        ByteBuffer[] bytes = {byteBuffer1,byteBuffer2};
        //把数据读入缓冲区集合
        fi.read(bytes);

        for(ByteBuffer bb:bytes){
            bb.flip();
            System.out.println(new String(bb.array(),0,bb.remaining()));
        }

        fo.write(bytes);

        fi.close();
        fo.close();

    }
}
