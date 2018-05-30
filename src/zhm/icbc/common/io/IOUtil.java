/**
 * IOUtil.java
 * io
 * 2018��5��24������9:43:02
 *
 */
package zhm.icbc.common.io;

import java.io.Closeable;
import java.io.IOException;


/**
 * @author zhuheming
 * IOUtil
 * 2018年5月24日下午10:01:44
 */
public class IOUtil {
	public static void closeQuietly(Closeable closeable) {
		if (null == closeable)
			return;
		try {
			closeable.close();
		} catch (IOException e) {
		}
	}
}
