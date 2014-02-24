package net.zhanghc.toolkit.net.core;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import net.zhanghc.toolkit.net.data.Page;

import org.cyberneko.html.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Parser {

	public Document parse(Page page) throws SAXException, IOException {
		DOMParser parser = new DOMParser();
		parser.setProperty("http://cyberneko.org/html/properties/default-encoding", "utf-8");
		parser.setFeature("http://xml.org/sax/features/namespaces", false);
		parser.parse(new InputSource(new ByteArrayInputStream(page.getContent())));
		return parser.getDocument();
	}
	
	public Document parse(Page page, String charset, boolean ns) throws SAXException, IOException {
		DOMParser parser = new DOMParser();
		parser.setProperty("http://cyberneko.org/html/properties/default-encoding", charset);
		parser.setFeature("http://xml.org/sax/features/namespaces", ns);
		parser.parse(new InputSource(new ByteArrayInputStream(page.getContent())));
		return parser.getDocument();
	}

}
