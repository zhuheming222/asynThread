/**
 * ReflectionUtil.java
 * reflaction
 * 2018��5��24������9:40:16
 *
 */
package zhm.icbc.common.reflaction;

import java.lang.reflect.Constructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhuheming
 * ReflectionUtil
 * 2018年5月24日下午10:05:01
 */
public class ReflectionUtil {
	private static final Logger logger = LoggerFactory.getLogger(ReflectionUtil.class);

	public static <T> T createInstance(String className) {
		if (null == className) {
			return null;
		}

		Object listener = null;
		try {
			Class listenerClass = Class.forName(className);
			Constructor constructor = listenerClass.getDeclaredConstructor(new Class[0]);
			constructor.setAccessible(true);
			listener = constructor.newInstance(new Object[0]);
		} catch (ClassNotFoundException e) {
			logger.error(String.format("could not found class:%s", new Object[] { className }), e);
		} catch (Exception e) {
			logger.error(String.format("create instance for class:%s fail", new Object[] { className }), e);
		}

		return (T) listener;
	}

	public static <T> T createInstance(String className, Class<?>[] parameterTypes, Object[] initargs) {
		if (null == className) {
			return null;
		}

		Object listener = null;
		try {
			Class listenerClass = Class.forName(className);
			Constructor constructor = listenerClass.getDeclaredConstructor(parameterTypes);
			constructor.setAccessible(true);
			listener = constructor.newInstance(initargs);
		} catch (ClassNotFoundException e) {
			logger.error(String.format("could not found class:%s", new Object[] { className }), e);
		} catch (Exception e) {
			logger.error(String.format("create instance for class:%s fail", new Object[] { className }), e);
		}

		return (T) listener;
	}
}
