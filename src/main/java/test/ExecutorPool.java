/**
 * 
 */
package test;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
* @ClassName: ExecutorPool 
* @Description: 线程池工具
* @author wuzhenfang(wuzhenfang@daojia.com)
* @date 2016年9月9日 下午6:03:46 
* @version V1.0
*/
public class ExecutorPool {

/*
	private final static Logger logger = LoggerFactory.getLogger(ExecutorPool.class);
*/

	private static ExecutorPool executorPool;
	
	private static int threadCount = Runtime.getRuntime().availableProcessors() + 1;// 默认根据cpu核数+1，决定个数
	
	protected ExecutorService executorService;

	private ExecutorPool() {
		super();
		executorPool = this;
	}

	public static void initPool(){
		synchronized (ExecutorPool.class) {
			System.out.println("线程池初始化开始");

			new ExecutorPool();
			System.out.println("线程池初始化结束，线程池大小" + threadCount);

		}
	}
	
	public void destory() {
		if (executorService != null) {
			getExecutorService().shutdown();
		}

	}

	public void execute(Runnable runnable) {
		getExecutorService().submit(runnable);
	}

	public int getThreadCount() {
		return threadCount;
	}

	public void setThreadCount(int threadCounts) {
		if (threadCount > 0) {
			threadCount = threadCounts;
		}
	}

	public synchronized ExecutorService getExecutorService() {
		if (executorService == null) {
			executorService = Executors.newFixedThreadPool(getThreadCount());
		}
		return executorService;
	}

	public static void executeTask(Runnable runnable) {
		try {
			ExecutorPool.executorPool.execute(runnable);
		} catch (Exception e) {
			System.out.println("执行任务出错"+ e);
		} catch (Throwable e) {
			System.out.println("执行任务出错"+ e);
		}
	}
	
	public static void main(String[] args) {
		ExecutorPool.initPool();
		ExecutorPool.executeTask(new Runnable() {
			@Override
			public void run() {
				System.out.println("111111111111-start");
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("111111111111-end");
				
			}
		});
		ExecutorPool.executeTask(new Runnable() {
			@Override
			public void run() {
				System.out.println("222222222222-start");
				try {
					Thread.sleep(1000);
					System.out.println(ExecutorPool.executorPool.getThreadCount());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("222222222222-end");
				
			}
		});
	}
}
