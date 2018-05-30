package zhm.icbc.threadpool;

import zhm.icbc.common.ILifeCycle;

/**
 * 线程池实例管理。
 * 
 */
public class ThreadPoolManager implements ILifeCycle {

    private ILifeCycle threadPool = new ThreadPoolImpl(); 
    
    private static Object lock = new Object();
    private boolean initStatus = false;
    private boolean destroyStatus = false;
    
    private static ThreadPoolManager instance = new ThreadPoolManager();
    public static ThreadPoolManager getSingleton() {
        return instance;
    }

    public ThreadPool getThreadPool() {
        return (ThreadPool) threadPool;
    }
    
    // 用于单元测试和子类扩展
    protected void setThreadPool(ThreadPool threadPool) {
        this.threadPool = (ILifeCycle) threadPool;
    }
    
    @Override
    public void init() {
        synchronized (lock) {
            if (initStatus) {
                return;
            }
            threadPool.init();
            initStatus = true;
        }
    }

    @Override
    public void destroy() {
        synchronized (lock) {
            if (destroyStatus) {
                return;
            }
            threadPool.destroy();
            destroyStatus = true;
        }
    }

}
