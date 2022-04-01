package TCP.wenjian;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServerDemo02 {
    public static void main(String[] args) throws IOException {
        //1.创建服务
        ServerSocket serverSocket = new ServerSocket(9000);
        //2.监听客户端的连接
        Socket socket = serverSocket.accept();//阻塞式监听
        //3.获取输入流
        InputStream is = socket.getInputStream();
        //4.文件输出
        FileOutputStream fos = new FileOutputStream(new File("1234.jpg"));
        byte[] buffer=new byte[1024];
        int len;
        while((len=is.read(buffer))!=-1){//从输入流中读取一定数量的字节，并将其存储在缓冲区数组 b 中。以整数形式返回实际读取的字节数
            fos.write(buffer,0,len);//输出流写；输入流读
        }
        //5.关闭资源
        fos.close();
        is.close();
        socket.close();
        serverSocket.close();
    }
}
