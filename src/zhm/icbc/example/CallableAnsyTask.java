package zhm.icbc.example;

import java.util.concurrent.Callable;

/** 
 * 需要返回值的异步任务。
 */
public class CallableAnsyTask implements Callable<Long> {

    private int[] arrayInt; 
    
    public CallableAnsyTask(int[] arr) {
    	arrayInt = arr;
    }
    
    @Override
    public Long call() throws Exception {
        long result = 0;
        for (int i = 0; i < arrayInt.length; i++) {
            result += arrayInt[i];
        }
        
        return result;
    }

}
