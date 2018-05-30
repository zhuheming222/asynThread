package zhm.icbc.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import zhm.icbc.threadpool.ThreadPool;
import zhm.icbc.threadpool.ThreadPoolManager;
import zhm.icbc.threadpool.handler.DiscardFailHandler;
import zhm.icbc.threadpool.handler.LogErrorFailHandler;

/**
 * 多线程池调用代码示例。
 * 
 */
public class ThreadPoolExample {
    
    public static void main(String[] args) throws Exception {
        ThreadPoolManager tpm = ThreadPoolManager.getSingleton();
        tpm.init(); // 在应用启动时调用
        
        ThreadPool threadPool = tpm.getThreadPool();
        executeRunnableAnsyTask(threadPool);
        executeCallableAnsyTask(threadPool);
        parallelExecuteAnsyTask(threadPool);
        handleSubmitFail(threadPool);
        
        tpm.destroy(); // 在应用关闭时调用
    }

    /**
     * 执行不需要返回值的异步任务。
     */
    private static void executeRunnableAnsyTask(ThreadPool threadPool)
            throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            threadPool.submit(new RunnableAsynTask());
            threadPool.submit(new RunnableAsynTask(), "other");
            
            Thread.sleep(50);
        }
    }
    
    /**
     * 执行需要返回值的异步任务。
     */
    private static void executeCallableAnsyTask(ThreadPool threadPool)
            throws InterruptedException, ExecutionException {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        CallableAnsyTask task = new CallableAnsyTask(arr);
        Future<Long> future = threadPool.submit(task);
        System.out.println("异步任务在线程池default的执行结果为:"+future.get());
        threadPool.submit(task, "other");
        System.out.println("异步任务在线程池other的执行结果为:"+future.get());
    }

    /**
     * 并行调用多个异步任务。
     */
    private static void parallelExecuteAnsyTask(ThreadPool threadPool) 
            throws InterruptedException, ExecutionException {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        List<Callable<Long>> tasks = new ArrayList<Callable<Long>>();
        tasks.add(new CallableAnsyTask(arr));
        tasks.add(new CallableAnsyTask(arr));
        tasks.add(new CallableAnsyTask(arr));
        
        List<Future<Long>> futures = threadPool.invokeAll(tasks, 1, TimeUnit.SECONDS);
        for (Future<Long> future : futures) {
            Long result = future.get();   // 如果某个任务执行超时，调用该任务对应的future.get时抛出CancellationException异常
            System.out.println("并行调用，其中一个任务的执行结果为:"+result);
        }
        
    }

    private static void handleSubmitFail(ThreadPool threadPool) {
        // 队列满时，提交失败的任务直接丢弃
        threadPool.submit(new RunnableAsynTask(), "default", new DiscardFailHandler<Runnable>());
        
        // 队列满时，提交失败的任务信息会输出ERROR日志
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        threadPool.submit(new CallableAnsyTask(arr), "other", new LogErrorFailHandler<Callable<Long>>());
    }
    
}
