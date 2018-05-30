package zhm.icbc.threadpool.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zhm.icbc.threadpool.FailHandler;


/**
 * 当队列满，异步任务无法提交给线程池执行时，输出一条错误日志记录处理失败的任务信息。
 * 
 */
public class LogErrorFailHandler<T> implements FailHandler<T> {

    private static Logger logger = LoggerFactory.getLogger(LogErrorFailHandler.class);  
    
    /**
     * 处理无法提交线程池执行的异步任务。
     * 
     * @param task 无法提交线程池执行的异步任务
     * @return null
     */
    @Override
    public void execute(T task) {
        logger.error("queue is full, a task cannot be submit to threadpool, task information:{}", task);
    }

}
