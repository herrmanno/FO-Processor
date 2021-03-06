<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo">
	
	<xsl:template match="div|p">
    	<fo:block>
    	<xsl:call-template name="attr" />
    		<xsl:apply-templates />
		</fo:block>
	</xsl:template>
	
	<xsl:template match="span">
    	<fo:inline>
    		<xsl:call-template name="attr" />
    		<xsl:apply-templates />
		</fo:inline>
	</xsl:template>
	
	<xsl:template match="i">
    	<fo:inline font-style="italic">
    	<xsl:call-template name="attr" />
    		<xsl:apply-templates />
		</fo:inline>
	</xsl:template>
	
	<xsl:template match="code">
   		<fo:block font-family="monospace" background-color="#eee"  white-space="pre" margin-left=".5in" margin-right=".5in">
   			<xsl:call-template name="attr" />
    		<xsl:apply-templates />
   		</fo:block>
	</xsl:template>
	
	
</xsl:stylesheet>