package sys;

//测试：生产者消费者问题，利用缓冲区解决（管程法）

//生产者，消费者，产品，缓冲区
public class TestPC {
    public static void main(String[] args) {
        synContainer container=new synContainer();
        new Producer(container).start();
        new Consumer(container).start();
    }
}

class Producer extends Thread{
    synContainer container;
    public Producer(synContainer container){
        this.container=container;
    }
    //生产
    public void run(){
        for (int i = 0; i < 100; i++) {
            container.push(new Chicken(i));
            System.out.println("生产了"+i+"几只鸡");
        }
    }
}

class Consumer extends Thread{
    synContainer container;
    public Consumer(synContainer container){
        this.container=container;
    }
    //消费
    public void run(){
        for (int i = 0; i < 100; i++) {
            System.out.println("消费了-->"+container.pop().id+"只鸡");
        }
    }
}

class Chicken{
    int id;
    public Chicken(int id) {
        this.id = id;
    }
}

class synContainer{
    //需要一个容器大小
    Chicken[] chickens=new Chicken[10];
    //容器计数器
    int count=0;
    //生产者放入产品
    public synchronized void push(Chicken chicken){
        //如果容器满了，就需要等待消费者消费
        if(count==chickens.length){
            //通知消费者消费，生产等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //如果没有满，丢入产品
        chickens[count]=chicken;
        count++;
        //可以通知消费者消费了
        this.notifyAll();
    }

    public synchronized Chicken pop(){
        if(count==0){
            //等待生产者生产，消费者等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //如果可以消费
        count--;
        Chicken chicken=chickens[count];

        //吃完了，通知生产者生产
        this.notifyAll();
        return chicken;
    }
}

