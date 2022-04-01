package hao.Lesson1;

public class TestLambda1 {
    //3.静态内部类
    static class Like2 implements Ilike{
        @Override
        public void lambda() {
            System.out.println("I like You2!");
        }
    }
    public static void main(String[] args) {
        Ilike like = new Like();
        like.lambda();

        like=new Like2();
        like.lambda();

        //4.局部内部类
        class Like3 implements Ilike{
            @Override
            public void lambda() {
                System.out.println("I like You3!");
            }
        }
        like=new Like3();
        like.lambda();

        //5.匿名内部类,没有类的名称，必须借助接口或者父类
        Ilike like2=new Ilike() {
            @Override
            public void lambda() {
                System.out.println("I like You4!");
            }
        };
        like2.lambda();

        //6.Lambda表达式简化
        like=()->{
            System.out.println("I like You5!");
        };
        like.lambda();

        //6.1lambda的再简化
        like=()->System.out.println("I like You6!");
        like.lambda();
    }
}

//1.定义一个函数式接口:即只含有唯一一个抽象方法的接口
interface Ilike{
    void lambda();
}

//2.实现类
class Like implements Ilike{
    @Override
    public void lambda() {
        System.out.println("I like You!");
    }
}
