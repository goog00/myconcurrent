package com.steng.concurrent.eg01;

import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;

/**
 * 自定义线程 ： 实现Runnable 接口
 * @author sunteng
 * @create 2019-08-27 下午 8:59
 **/
public class MyRunnableImpl implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(MyRunnableImpl.class);

    //线程名
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyRunnableImpl(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        Integer interval = RandomUtils.nextInt(1000,5000);
        LOGGER.info("线程[" + this.getName() + "] 正在运行，预计运行" + interval + "....");
        try {
            Thread.sleep(interval);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LOGGER.info("...线程[" + this.getName() + "]运行结束");
        }
    }

    public static void main(String[] args) {
        for(int i = 0; i < 5; i++){
            Runnable runnable = new MyRunnableImpl("MyRunnableImpl-" + i);
            new Thread(runnable).start();
        }
    }
}
