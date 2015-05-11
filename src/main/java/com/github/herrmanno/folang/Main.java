package com.github.herrmanno.folang;

import java.io.FileInputStream;

public class Main {

	static public void main(String[] args) {
		Lexer lexer = new Lexer();
		Generator generator = new Generator();
		try {
			Token root = lexer.Lex(new FileInputStream("C:/users/herrmanno/desktop/test.fo"));
			generator.generate(root, "C:/users/herrmanno/desktop/test.pdf");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
