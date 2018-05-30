/**
 * DomUtil.java
 * zhu.icbc.common.xml
 * 2018��5��24������9:30:50
 *
 */
package zhm.icbc.common.xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

/**
 * @author zhuheming DomUtil 2018年5月24日下午10:02:33
 */
public class DomUtil {
	private static Logger logger = LoggerFactory.getLogger(DomUtil.class);

	public static Document createDocument(String classPathXmlFile) {
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		Document document = null;
		try {
			DocumentBuilder dom = domFactory.newDocumentBuilder();
			document = dom.parse(DomUtil.class.getResourceAsStream(classPathXmlFile));
		} catch (Exception e) {
			logger.error(
					String.format("create Document of xml file[%s] occurs error", new Object[] { classPathXmlFile }),
					e);
		}

		return document;
	}
}
