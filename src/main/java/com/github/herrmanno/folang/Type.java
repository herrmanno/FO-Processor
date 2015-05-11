package com.github.herrmanno.folang;

enum Type {
	$_STYLESHEET,
	$_ROOT,
	$_LAYOUT_MASTER_SET,
	$_SIMPLE_PAGE_MASTER,
	$_REGION_BODY,
	$_PAGE_SEQUENCE,
	
	$ROOT("root"),
	$table("table"),
	$row("row"),
	$cell("cell");
	
	final String tag;
	
	private Type() {
		this(null);
	}
	
	private Type(String tag) {
		this.tag = tag;
	}
}