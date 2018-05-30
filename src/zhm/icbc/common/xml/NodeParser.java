/**
 * NodeParser.java
 * zhu.icbc.common.xml
 * 2018��5��24������9:31:36
 *
 */
package zhm.icbc.common.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author zhuheming
 * NodeParser
 * 2018年5月24日下午10:02:57
 */
public class NodeParser {

	private Node node;
	private Map<String, String> attrMap;
	private List<Node> childNodes;

	public NodeParser(Node node) {
		this.node = node;
	}

	public int getAttributeCount() {
		initAttrMap();
		return this.attrMap.size();
	}

	public String getAttributeValue(String attrName) {
		initAttrMap();
		return ((String) this.attrMap.get(attrName));
	}

	public Node getChildNode(String nodeName) {
		if (null == nodeName) {
			return null;
		}

		initChildNodeList();
		for (Node node : this.childNodes) {
			if (nodeName.equals(node.getNodeName())) {
				return node;
			}
		}

		return null;
	}

	public int getChildNodeCount() {
		initChildNodeList();
		return this.childNodes.size();
	}

	public List<Node> getChildNodes() {
		initChildNodeList();
		return this.childNodes;
	}

	public String getChildNodeValue(String nodeName) {
		Node node = getChildNode(nodeName);
		if (null == node) {
			return null;
		}

		return node.getTextContent();
	}

	public String getName() {
		return this.node.getNodeName();
	}

	public String getValue() {
		return this.node.getTextContent();
	}

	private void initAttrMap() {
		if (null != this.attrMap) {
			return;
		}

		this.attrMap = new HashMap();
		NamedNodeMap nodeMap = this.node.getAttributes();
		if (null == nodeMap) {
			return;
		}
		for (int i = 0; i < nodeMap.getLength(); ++i) {
			Node attr = nodeMap.item(i);
			this.attrMap.put(attr.getNodeName(), attr.getNodeValue());
		}
	}

	private void initChildNodeList() {
		if (null != this.childNodes) {
			return;
		}

		this.childNodes = new ArrayList();
		NodeList nodeList = this.node.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); ++i) {
			Node node = nodeList.item(i);
			if (1 != node.getNodeType()) {
				continue;
			}
			this.childNodes.add(node);
		}
	}
}
