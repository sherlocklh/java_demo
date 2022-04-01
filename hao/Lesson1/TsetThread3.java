package hao.Lesson1;

//抢票的例子
public class TsetThread3 implements Runnable {
    private int ticketNum=10;

    public void run(){
        while(true){
            if(ticketNum<=0){
                break;
            }
            //模拟延时
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName()+"拿到了第"+ticketNum--+"票");
        }
    }

    public static void main(String[] args) {
        TsetThread3 test3=new TsetThread3();
        new Thread(test3,"刘浩").start();
        new Thread(test3,"小明").start();
        new Thread(test3,"小强").start();
    }
}
