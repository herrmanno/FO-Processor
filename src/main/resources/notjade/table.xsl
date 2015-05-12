<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo">
	
	<xsl:template match="table">
    	<fo:table border="1pt solid black">
    		<xsl:call-template name="attr" />
    		<fo:table-body>
    			<xsl:apply-templates />
    		</fo:table-body>
    	</fo:table>
	</xsl:template>
	
	<xsl:template match="row">
    	<fo:table-row border="1pt solid black">
    		<xsl:call-template name="attr" />
    		<xsl:apply-templates />
    	</fo:table-row>
	</xsl:template>
	
	<xsl:template match="cell">
    	<fo:table-cell border="1pt solid black" >
	    	<xsl:call-template name="attr" />
   			 <fo:block linefeed-treatment="preserve">
    			<xsl:apply-templates />
   			 </fo:block>
    	</fo:table-cell>
	</xsl:template>
	
	
</xsl:stylesheet>