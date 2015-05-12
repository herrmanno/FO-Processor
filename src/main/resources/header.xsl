<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo">
	
	<xsl:template match="h1">
    	<fo:block font-size="24pt" font-weight="bold" text-decoration="underline">
    	<xsl:call-template name="attr" />
    		<xsl:apply-templates />
		</fo:block>
	</xsl:template>
	
	<xsl:template match="h2">
    	<fo:block font-size="20pt" text-decoration="underline">
    	<xsl:call-template name="attr" />
    		<xsl:apply-templates />
		</fo:block>
	</xsl:template>
	
	<xsl:template match="h3">
    	<fo:block font-size="18pt">
    	<xsl:call-template name="attr" />
    		<xsl:apply-templates />
		</fo:block>
	</xsl:template>
	
	
</xsl:stylesheet>