package hao.Lesson1;

//静态代理模式：
//真实对象和代理对象都要实现同一个接口！
//代理对象要代理真实对象

//好处：
//代理对象可以做很多其他的事情
//真实对象专注于自己的事情

public class StaticProxy {
    public static void main(String[] args) {
        You you=new You();
        Proxy proxy=new Proxy(you);
        proxy.Happymarry();
    }
}

//首先定义一个接口
interface Marry{
    void Happymarry();
}

class You implements Marry{
    @Override
    public void Happymarry() {
        System.out.println("刘先生和漂亮的女朋友要结婚了！");
    }
}

class Proxy implements Marry{
    private Marry real;
    public Proxy(Marry real){
        this.real=real;
    }
    @Override
    public void Happymarry() {
        before();
        this.real.Happymarry();
        after();

    }
    private void after() {
        System.out.println("结婚之后收拾场地");
    }
    private void before() {
        System.out.println("结婚之前收钱");
    }
}


