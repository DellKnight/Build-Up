package com.four;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Ping.Dai
 * 伪异步架构
 */
public class SocketClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",9999);
        PrintStream ps = new PrintStream(socket.getOutputStream());
        //使用循环给客户端发送消息
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.print("请输入:");
            String nextLine = sc.nextLine();
            //包装成打印流
            ps.println(nextLine);
            ps.flush();
        }
    }
}
