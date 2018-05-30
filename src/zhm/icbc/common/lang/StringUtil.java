/**
 * StringUtil.java
 * lang
 * 2018��5��24������9:41:43
 *
 */
package zhm.icbc.common.lang;


public class StringUtil {
	public static boolean isNull(String str) {
		return (null == str);
	}

	public static boolean isEmpty(String str) {
		return ((null == str) || (0 == str.length()));
	}

	public static boolean isBlank(String str) {
		if (isEmpty(str)) {
			return true;
		}

		for (int i = 0; i < str.length(); ++i) {
			if (!(Character.isWhitespace(str.charAt(i)))) {
				return false;
			}
		}

		return true;
	}
}
