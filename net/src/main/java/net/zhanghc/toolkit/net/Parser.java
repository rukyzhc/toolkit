package net.zhanghc.toolkit.net;

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
		parser.parse(new InputSource(new ByteArrayInputStream(page.getContent())));
		return parser.getDocument();
	}

}
