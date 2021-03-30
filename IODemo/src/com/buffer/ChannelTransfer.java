package com.buffer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;

/**
 * @author Ping.Dai
 */
public class ChannelTransfer {
    public static void main(String[] args) throws Exception {
        //定义一个文件输入流
        FileInputStream fileInputStream = new FileInputStream("D:\\test1.txt");
        FileChannel fi = fileInputStream.getChannel();
        //定义一个文件输出流
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\test.txt");
        FileChannel fo = fileOutputStream.getChannel();
        //从fi通道读取文件到fo通道
        fo.transferFrom(fi,fi.position(),fi.size());

        fi.close();
        fo.close();
    }
}
