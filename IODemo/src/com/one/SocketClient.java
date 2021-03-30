package com.one;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

/**
 * @author Ping.Dai
 * 客户端发送消息
 */
public class SocketClient {
    public static void main(String[] args) {
        try {
            System.out.println("客户端启动");
            Socket socket = new Socket("127.0.0.1",9999);
            OutputStream os = socket.getOutputStream();
            //包装成打印流
            PrintStream ps = new PrintStream(os);
            ps.println("Hello World");
            ps.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
