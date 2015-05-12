<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo">
	
	<xsl:template match="titlepage">
   		<fo:block>
   			<xsl:call-template name="attr" />
    		<xsl:apply-templates />
   		</fo:block>
	</xsl:template>
	
	<xsl:template match="header">
   		<fo:block>
   			<xsl:call-template name="attr" />
    		<xsl:apply-templates />
   		</fo:block>
	</xsl:template>
	
	<xsl:template match="footer">
   		<fo:block>
   			<xsl:call-template name="attr" />
    		<xsl:apply-templates />
   		</fo:block>
	</xsl:template>
	
	<xsl:template match="pagenumber">
    	<fo:page-number />
	</xsl:template>
	
	<xsl:template match="pagenumber-last">
    	<fo:page-number-citation-last ref-id="page-sequence"/>
	</xsl:template>
	
	<xsl:template match="pagebreak">
    	<fo:block break-after="page" />
	</xsl:template>
	
	
</xsl:stylesheet>