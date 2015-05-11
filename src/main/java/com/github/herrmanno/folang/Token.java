package com.github.herrmanno.folang;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class Token {
	final Type type;
	String data;
	final Map<String, String> params;
	final int indent;
	
	Token parent = null;
	final LinkedList<Token> children = new LinkedList<Token>();

	Token(Type type, int indent, Map<String, String> params, String data) {
		this.type = type;
		this.data = data;
		this.params = params != null ? params : new HashMap<String, String>();
		this.indent = indent;
	}
	
	public Token getParent() {
		return parent;
	}
	
	public void addChild(Token t) {
		children.add(t);
		t.parent = this;
	}
	
	@Override
	public String toString() {
		return String.format("%s :: %s", type.name(), data);
	}

	public String getParams() {
		String p = "";
		for(String key : params.keySet()) {
			p += key + "=" + "\"" + params.get(key) + "\"" + " ";
		}
		
		return p;
	}
}