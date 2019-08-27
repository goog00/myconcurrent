package com.steng.concurrent.eg03;

/**
 * @author sunteng
 * @create 2019-08-27 下午 9:50
 **/
public class ConcurrentJMMDemo {

    static class MyDemoThread extends Thread{
        //本地变量
        private Integer index = 110;

        //共享变量的引用

        private Long timeStamp;

        public MyDemoThread(String name,Long timeStamp){
            super(name);
            this.timeStamp = timeStamp;
        }

        @Override
        public void run(){
            System.out.println("Thread name : " + super.getName() + ", timestamp:" + timeStamp);
            super.run();
        }

        public static void main(String[] args) {
            //定义一个共享变量
            Long now = System.currentTimeMillis();
            //创建线程
            Thread thread = new MyDemoThread("张三",now);
            //启动线程
            thread.start();
        }
    }
}
