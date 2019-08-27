package com.steng.concurrent.eg01;

import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;

import java.util.concurrent.*;

/**
 * @author sunteng
 * @create 2019-08-27 下午 9:06
 **/
//注意，callable 施工方泛型接口
public class MyCallableImpl implements Callable<Integer> {

    private static final Logger LOGGER = Logger.getLogger(MyCallableImpl.class);


    /**
     * 实现callable需要重写call方法，此方法有返回值
     * @return
     * @throws Exception
     */
    @Override
    public Integer call() throws Exception {
        Integer interval = RandomUtils.nextInt(1000,5000);
        LOGGER.info("interval=" + interval);
        Thread.sleep(interval);
        return interval;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException{
        //创建线程池，
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        //向线程池提交任务
        Future<Integer> future2 = executorService.submit(new MyCallableImpl());
        Long begin2 = System.currentTimeMillis();

        try{
            LOGGER.info("future 开始执行任务...当前时间：" + begin2);
            LOGGER.info("通过future.get(long,TimeUnit)获取任务的计算结果（5秒之后再获取结果）：" + future2.get(5000, TimeUnit.MILLISECONDS) );
            LOGGER.info("future结束执行任务...共计用时：" + (System.currentTimeMillis() - begin2) + "ms....\n");

        } catch (TimeoutException e){
            LOGGER.info("在限定时间内没有等到查询结果，不在等待");
            //关闭任务
            LOGGER.info("当前任务状态：future2.isDone() = " + future2.isDone());
            LOGGER.info("当前任务状态：future2.isCancelled()= " + future2.isCancelled());
            LOGGER.info("通过future.cancel() 取消任务：");
            future2.cancel(true);
            LOGGER.info("当前任务状态：futures.isDone()=" +future2.isCancelled());
            LOGGER.info("当前任务状态：future2.isCancelled()" + future2.isCancelled());

            //关闭线程池
            executorService.shutdown();


        }

    }
}
