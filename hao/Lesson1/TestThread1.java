package hao.Lesson1;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

//多线程实现同时下载几张图片
public class TestThread1 extends Thread{//继承Thread类
    private String url;
    private String name;

    public TestThread1(String url, String name){
        this.url=url;
        this.name=name;
    }

    //重写Thread的run方法
    public void run(){
        WebDownloader down=new WebDownloader();
        down.downloader(url,name);
        System.out.println("下载了文件名为："+name);
    }

    public static void main(String[] args) {
        TestThread1 s1=new TestThread1("https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/07/21/kuangstudy98c35b12-bc9b-4872-bcc0-71939df0829f.jpg","1.jpg");
        TestThread1 s2=new TestThread1("https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/07/21/kuangstudyb62b0ccb-55b5-4572-b067-347314beac15.jpg","2.jpg");
        TestThread1 s3=new TestThread1("https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/07/21/kuangstudy69ec9992-bed7-4eec-a550-5fe2aeb44737.jpg","3.jpg");

        s1.start();
        s2.start();
        s3.start();
    }

}

class WebDownloader{
    public void downloader(String url,String name){
        try {
            FileUtils.copyURLToFile(new URL(url),new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO异常，downloader方法出现问题");
        }
    }
}
