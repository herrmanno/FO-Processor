package com.github.herrmanno.folang;

enum Type {
	$ROOT("root"),
	$$("text"),
	$BLOCK("block"),
	$CODE("code"),

	$H1("h1"),
	$H2("h2"),
	$H3("h3"),
	
	$TABLE("table"),
	$ROW("row"),
	$CELL("cell"),
	
	$LIST("list"),
	$ITEM("listitem"),
	
	$TITLEPAGE("titlepage"),
	$HEADER("header"),
	$FOOTER("footer"),
	
	$FOOTNOTE("footnote"),
	
	$PAGENUMBER("pagenumber"),
	$PAGENUMBERLAST("pagenumber-last"),
	$PBR("pagebreak");
	
	final String tag;
	
	private Type() {
		this(null);
	}
	
	private Type(String tag) {
		this.tag = tag;
	}
}