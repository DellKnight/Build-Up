package com.buffer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelTest {
    public static void main(String[] args) throws FileNotFoundException {
        try {
            //創建输出流
            FileInputStream fos = new FileInputStream("D:\\test.txt");
            //获取通道
            FileChannel fc = fos.getChannel();
            //分配缓冲区
            ByteBuffer bb = ByteBuffer.allocate(1024);
            //读取数据到缓冲区
            fc.read(bb);
            bb.flip();
            System.out.println(new String(bb.array(),0,bb.remaining()).length());
            fc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
