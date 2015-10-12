package com.uninet.xiaoyou.net;

import java.io.InputStream;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 解析从服务器获取的xml，读取版本更新信息
 * @author chenggong
 *
 */
public class ParseXmlService {

	// 版本总数
	int version_count;

	/**
	 * 解析xml，将内容存放到一个HashMap中
	 * 
	 * @param inStream
	 * @return hashMap
	 * @throws Exception
	 */
	public HashMap<String, String> parseXml(InputStream inStream)
			throws Exception {
		HashMap<String, String> hashMap = new HashMap<String, String>();

		// 实例化一个文档构建器工厂
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// 通过文档构建器工厂获取一个文档构建器
		DocumentBuilder builder = factory.newDocumentBuilder();
		// 通过文档通过文档构建器构建一个文档实例
		Document document = builder.parse(inStream);
		// 获取XML文件根节点
		Element root = document.getDocumentElement();
		// 获得所有子节点
		NodeList childNodes = root.getChildNodes();
		for (int j = 0; j < childNodes.getLength(); j++) {
			// 遍历子节点
			Node childNode = (Node) childNodes.item(j);
			if (childNode.getNodeType() == Node.ELEMENT_NODE) {
				Element childElement = (Element) childNode;
				// 版本总数
				if (childElement.getNodeName().equals("version_count")) {
					version_count = Integer.valueOf(childElement
							.getFirstChild().getNodeValue());
					hashMap.put("version_count", childElement.getFirstChild()
							.getNodeValue());
				}
				for (int m = 0; m <= version_count; m++) {
					NodeList list = childElement.getChildNodes();
					// 版本序号
					if (childElement.getNodeName().equals("version" + m)) {
						for(int n = 0; n < list.getLength(); n++) {
							Node node = (Node) list.item(n);
							// 版本号
							if(node.getNodeName().equals("version_code")){
								hashMap.put("version_code"+m, node.getFirstChild().getNodeValue());
							}
							// 下载路径
							if(node.getNodeName().equals("path")){
								hashMap.put("path"+m, node.getFirstChild().getNodeValue());
							}
						}
					}
				}
			}
		}
		return hashMap;
	}
}
