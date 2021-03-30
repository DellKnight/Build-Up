package com.two;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

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
            PrintStream ps = new PrintStream(os);
            Scanner sc = new Scanner(System.in);
            while(true){
                System.out.print("请输入:");
                String nextLine = sc.nextLine();
                //包装成打印流
                ps.println(nextLine);
                ps.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
