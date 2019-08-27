package com.steng.concurrent.eg01;

import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;

/**
 * @author sunteng
 * @create 2019-08-27 下午 8:34
 **/

public class MyThread extends Thread {
    private static final Logger LOGGER = Logger.getLogger(MyThread.class);


    /**
     * 重写thread类的构造器，给线程命名
     * 不需要重新定义变量name,使用父类的
     * @param name
     */
    public MyThread(String name){
        super(name);
    }

    /**
     * 业务方法写在run ,无返回值
     *
     */
    @Override
    public void run(){
        Integer interval = RandomUtils.nextInt(100,500);
        LOGGER.info("线程[" + super.getName() + "] 正在运行，预计运行" + interval + "。。。");
        try {
            Thread.sleep(interval);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LOGGER.info("....线程[" + super.getName() + "] 运行结束" );
        }
    }


    public static void main(String[] args) {
        for(int i = 0; i < 5; i++){
            //通过new 创建一个线程
            Thread thread = new MyThread("ForLoopThread-" + i);
            //通过start() 启动线程
            thread.start();

        }
    }

}
