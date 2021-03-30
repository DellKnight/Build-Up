package com.two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Ping.Dai
 * 服务端接收客户端从键盘敲入的信息
 */
public class SocketServer {
    public static void main(String[] args) {
        try {
            System.out.println("服务端启动");
            //创建serversocket连接
            ServerSocket ss = new ServerSocket(9999);
            //监听端口
            Socket socket = ss.accept();
            //得到一个字节输入流
            InputStream in = socket.getInputStream();
            //字节输入流包装缓冲字符输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while((line=br.readLine())!=null){
                System.err.println("line:"+line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
