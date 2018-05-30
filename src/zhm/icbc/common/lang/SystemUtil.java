/**
 * SystemUtil.java
 * lang
 * 2018��5��24������9:42:13
 *
 */
package zhm.icbc.common.lang;


/**
 * @author zhuheming
 * SystemUtil
 * 2018年5月24日下午10:04:35
 */
public class SystemUtil {
	public static String getEndLine() {
		return System.getProperty("line.separator");
	}

	public static int getProcessorCount() {
		return Runtime.getRuntime().availableProcessors();
	}
}
