package com.file;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.Socket;
import java.util.concurrent.ExecutionException;

/**
 * @author Ping.Dai
 * 上传任意类型文件给服务端
 */
public class Client {
    public static void main(String[] args) {
        try{
            Socket socket = new Socket("127.0.0.1",8888);
            //把字节输出流包装成数据输出流
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            //先上传文件后缀给服务端
            dos.writeUTF(".zip");
            InputStream is = new FileInputStream("C:\\Users\\sniperYin\\Desktop\\软考试题.zip");
            byte[] b = new byte[1024*1024];
            int len;
            while((len=is.read(b))>0){
                System.out.println("文件写出");
                dos.write(b,0,len);
            }
            dos.flush();
            socket.shutdownOutput();
        }catch (Exception e){

        }
    }
}
