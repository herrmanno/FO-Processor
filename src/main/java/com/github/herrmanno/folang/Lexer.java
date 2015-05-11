package com.github.herrmanno.folang;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Lexer {

	
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
		
		while(line.startsWith("\\t")) {
			ind++;
			line = line.substring(2);
		}
		
		while(line.startsWith("    ")) {
			ind++;
			line = line.substring(4);
		}
		
		for(Type t : Type.values()) {
			if(line.startsWith(t.name())) {
				type = t;
				line = line.substring(t.name().length());
				
				//------- parse Params
				if(line.startsWith("(")) {
					String[] split = line.split("\\)");
					String p = split[0].substring(1);
					for(String p1 : p.split(",")) {
						String[] parts = p1.trim().split("=");
						params.put(parts[0], parts[1]);
					}
					line = split.length > 1 ? split[1] : "";
				}
				
				data = line;
				break;
			}
		}
		
		if(data == null && ind > parent.indent) {
			parent.data += parent.data.isEmpty() ? "" : "\n";
			parent.data += line;
			return parent;
		}
		
		token = new Token(type, ind, params, data);
		if(token.indent <= parent.indent)
			while(parent.indent >= token.indent)
				parent = parent.getParent();
		
		parent.addChild(token);
		
		return token;
	}
}
