package com.github.herrmanno.folang;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Lexer {

	enum Status {IDLE, PARAMS, VALUE, TEXT}
	
	private Status status = Status.IDLE;
	private Map<String, String> vars = new HashMap<String, String>();
	
	public Token Lex(InputStream is) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
	    String line;
	    
	    Token root = new Token(Type.$ROOT, -1, null, null);
	    Token curr = root;
	   
	    while ((line = in.readLine()) != null) {
	    	curr = processLine(line, curr);
	    }
	    
	    return root;
	}
	

	private Token processLine(String line, Token parent) {
		Token token = null;
		Type type = null;
		String data = null;
		Map<String, String> params = new HashMap<String, String>();
		int ind = 0;
		
		/*
		if(line.startsWith("''")) {
			if(status != Status.TEXT) {
				status = Status.TEXT;
				if(line.equals("''"))
					return parent;
					
				if(line.endsWith("''")) {
					status = Status.IDLE;
					parent.addData(line.substring(2, line.length()-2));
				} else {
					parent.addData(line);
				}
			} else {
				if(line.equals("''")) 
					return parent;
				
				if(line.endsWith("''")) {
					status = Status.IDLE;
					parent.addData(line.substring(2, line.length()-2));
				} else {
					parent.addData(line);
				}
			}
		}
		*/
		
		while(line.startsWith("\\t")) {
			ind++;
			line = line.substring(2);
		}
		
		while(line.startsWith("    ")) {
			ind++;
			line = line.substring(4);
		}
		
		if(line.isEmpty() || line.equals("\n") || line.startsWith("#"))
			return parent;
		
		if(line.startsWith("%")) {
			String[] split = line.split("=", 2);
			String name = split[0].trim().substring(1);
			String value = split[1];
			for (Map.Entry<String, String> entry : vars.entrySet()) {
		        value = value.replace("%" + entry.getKey(), entry.getValue());
		    }
			vars.put(name, value.trim());
			return parent;
		}
		
		for (Map.Entry<String, String> entry : vars.entrySet()) {
	        line = line.replace("%" + entry.getKey(), entry.getValue());
	    }
		
		if(line.startsWith("$")) {
			type = readType(line);
			line = line.substring(type.name().length());
		}
		
		while(line.startsWith(" ")) {
			line = line.substring(1);
		}
		
		if(status == Status.PARAMS && line.startsWith("]")) {
			line = line.substring(1);
			status = Status.IDLE;
		}
		
		if(line.startsWith("[") || status == Status.PARAMS) {
			params = readParams(line);
			if(status != Status.PARAMS)
				line = line.substring(line.indexOf("]")+1);
		}
		
			
		if(status != Status.PARAMS)
			data = line;
		
		if(type == null && status == Status.PARAMS) {
			parent.params.putAll(params);
			return parent;
		}
		
		
		if(type == null && status != Status.PARAMS) {
			Token p = parent;
			while(p.indent >= ind)
				p = p.parent;
			p.addData(data, ind-1);
			return parent;
		}
		
		token = new Token(type, ind, params, data);
		if(token.indent <= parent.indent)
			while(parent.indent >= token.indent)
				parent = parent.getParent();
		
		parent.addChild(token);
		
		return token;
	}


	private Map<String, String> readParams(String line) {
		if(line.startsWith("["))
			line = line.substring(1);
		Map<String, String> params = new HashMap<String, String>();
		
		String pString = line.lastIndexOf(']') != -1 ? line.split("]")[0] : line;
		String[] splits = pString.split(",");
		for(String split : splits) {
			String[] p = split.split("=");
			if(p.length == 0 || p[0].isEmpty()) break;
			params.put(p[0].trim(), p[1].trim());
		}
		
		line = line.substring(pString.length());
		
		if(line.isEmpty())
			status = Status.PARAMS;
		
		return params;
	}


	private Type readType(String line) {
		int splitPos = line.indexOf('[');
		if(splitPos == -1)
			splitPos = line.indexOf(' ');
		if(splitPos == -1)
			splitPos = line.length();
		String type = (String) line.subSequence(0, splitPos);
		
		return Type.valueOf(type.toUpperCase());
	}
}
