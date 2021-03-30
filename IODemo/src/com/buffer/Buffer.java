package com.buffer;

import java.nio.ByteBuffer;

/**
 * @author Ping.Dai
 */
public class Buffer {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(100);
        //往缓冲区添加数据
        buffer.put("daiping".getBytes());
        System.out.println(buffer.limit());
        System.out.println(buffer.position());
        buffer.flip();
        System.out.println("=======================");

        byte[] m = new byte[3];
        buffer.get(m);
        String s = new String(m);
        System.out.println(s);
        buffer.mark();
        System.out.println("=======================");

        byte[] mm = new byte[3];
        buffer.get(mm);
        String ss = new String(mm);
        System.out.println(ss);
        buffer.reset();
        System.out.println(buffer.remaining());
        System.out.println("=======================");

        //创建一个直接内存
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
        System.out.println(byteBuffer.isDirect());
    }
}
