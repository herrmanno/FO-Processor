<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo">
	
	<xsl:template match="block">
    	<fo:block>
    	<xsl:call-template name="attr" />
    		<xsl:apply-templates />
		</fo:block>
	</xsl:template>
	
	<xsl:template match="text">
    	<fo:inline>
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