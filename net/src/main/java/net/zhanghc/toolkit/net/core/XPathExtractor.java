package net.zhanghc.toolkit.net.core;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

public class XPathExtractor {
	
	@SuppressWarnings("rawtypes")
	List<Unit> units = new ArrayList<Unit>();
	
	XPath xpath;
	
	public XPathExtractor() {
		xpath = XPathFactory.newInstance().newXPath();
	}
	
	public <T> void addRule(String expr, QName qname, Handler<T> handler) throws XPathExpressionException {
		Unit<T> u = new Unit<T>();
		u.expr = xpath.compile(expr);
		u.handler = handler;
		u.qname = qname;
		units.add(u);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void extract(Object doc) throws XPathExpressionException {
		for(Unit u : units) {
			Object res = u.expr.evaluate(doc, u.qname);
			u.handler.handle(res);
		}
	}
	
	public static interface Handler<T> {
		public void handle(T obj);
	}
	
	static class Unit<T> {
		XPathExpression expr;
		QName qname;
		Handler<T> handler;
	}
	
}
