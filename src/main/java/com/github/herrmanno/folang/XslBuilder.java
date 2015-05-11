package com.github.herrmanno.folang;



public class XslBuilder {

	private StringBuilder sb = new StringBuilder();
	
	public String build(Token root) {
		if(root.type != Type.$ROOT)
			throw new IllegalArgumentException();
		
		sb = new StringBuilder();
		
		//root = wrap(root);
		write(root);
		return sb.toString();
	}
	
	/*
	private Token wrap(Token t) {
		Map<String, String> ssMap = new HashMap<String, String>();
		ssMap.put("xmlns:xsl", "http://www.w3.org/1999/XSL/Transform");
		ssMap.put("xmlns:fo", "http://www.w3.org/1999/XSL/Format");
		ssMap.put("exclude-result-prefixes", "fo");
		Token ss = new Token(Type.$_STYLESHEET, -1, ssMap, null);
		
		Map<String, String> rootMap = new HashMap<String, String>();
		ssMap.put("xmlns:fo", "http://www.w3.org/1999/XSL/Format");
		Token root = new Token(Type.$_ROOT, -1, rootMap, null);
		
		Token layoutMasterSet = new Token(Type.$_LAYOUT_MASTER_SET, -1, null, null);
		
		Map<String, String> simplePageMasterMap = new HashMap<String, String>();
		ssMap.put("master-name", "master_1");
		Token simplePageMaster = new Token(Type.$_SIMPLE_PAGE_MASTER, -1, simplePageMasterMap, null);
		
		Map<String, String> regionBodyMap = new HashMap<String, String>();
		ssMap.put("margin", "1in");
		Token regionBody = new Token(Type.$_REGION_BODY, -1, regionBodyMap, null);
		
		
		return ss;
	}
	*/
	

	private void write(Token t) {
		writeOpenTag(t);
		writeContent(t);
		writeCloseTag(t);
		
	}

	private void writeOpenTag(Token t) {
		sb.append(String.format("<%s %s>", t.type.tag, t.getParams()));
	}

	private void writeCloseTag(Token t) {
		sb.append(String.format("</%s>", t.type.tag));
	}

	private void writeContent(Token t) {
		if(t.children.size() > 0) {
			for(Token child : t.children)
				write(child);
		} else {
			sb.append(t.data);
		}
	}
	
}
