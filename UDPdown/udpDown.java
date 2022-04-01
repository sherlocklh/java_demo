package UDPdown;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class udpDown {
    public static void main(String[] args) throws IOException {
        //1.下载地址
        URL url = new URL("https://m801.music.126.net/20210914232116/d785bf2af56340af63b2ddf1ddad8bdf/jdyyaac/5359/560f/0352/d3a0dadf42ae22e80fb048deaeb6b756.m4a");
        //2.连接到这个资源
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        InputStream inputStream = httpURLConnection.getInputStream();
        FileOutputStream fileOutputStream = new FileOutputStream("2.m4a");

        byte[] bytes = new byte[1024];
        int len;
        while((len=inputStream.read(bytes))!=-1){
            fileOutputStream.write(bytes,0,len);
        }

        fileOutputStream.close();
        inputStream.close();
        httpURLConnection.disconnect();
    }
}
