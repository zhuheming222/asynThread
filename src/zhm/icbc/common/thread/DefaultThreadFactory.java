/**
 * DefaultThreadFactory.java
 * zhu.icbc.common.thread
 * 2018��5��24������9:35:22
 *
 */
package zhm.icbc.common.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;


/**
 * @author zhuheming
 * DefaultThreadFactory
 * 2018年5月24日下午10:05:34
 */
public class DefaultThreadFactory implements ThreadFactory {

	private AtomicLong count;
	private static final String DEFAULT_THREAD_NAME_PRIFIX = "default-thread";
	private ThreadGroup group;
	private String threadNamePrefix;

	public DefaultThreadFactory() {
		this("default-thread");
	}

	public DefaultThreadFactory(String threadNamePrefix) {
		this.count = new AtomicLong(1L);

		this.threadNamePrefix = "default-thread";

		this.threadNamePrefix = threadNamePrefix;
		ThreadGroup root = getRootThreadGroup();
		this.group = new ThreadGroup(root, this.threadNamePrefix);
	}

	public Thread newThread(Runnable r) {
		Thread thread = new Thread(this.group, r);
		thread.setName(this.threadNamePrefix + "-" + this.count.getAndIncrement());
		if (thread.isDaemon()) {
			thread.setDaemon(false);
		}
		if (5 != thread.getPriority()) {
			thread.setPriority(5);
		}

		return thread;
	}

	private ThreadGroup getRootThreadGroup() {
		ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
		while (null != threadGroup.getParent()) {
			threadGroup = threadGroup.getParent();
		}

		return threadGroup;
	}

}
