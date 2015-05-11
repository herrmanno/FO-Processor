<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo">

	<xsl:template match="root">
		<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
	  		<fo:layout-master-set>
	    		<fo:simple-page-master master-name="A4">
	      			<fo:region-body margin="1in"/>
	    		</fo:simple-page-master>
	  		</fo:layout-master-set>
	
		 	<fo:page-sequence master-reference="A4">
		    	<fo:flow flow-name="xsl-region-body">
		    		<fo:block >
			    		<xsl:apply-templates/>
		    		</fo:block>
		    	</fo:flow>
		  	</fo:page-sequence>
		</fo:root>
	</xsl:template>
	
	<xsl:template match="table">
    	<fo:table border="1pt solid black">
    		<fo:table-body>
    			<xsl:apply-templates />
    		</fo:table-body>
    	</fo:table>
	</xsl:template>
	
	<xsl:template match="row">
    	<fo:table-row border="1pt solid black">
    		<xsl:apply-templates />
    	</fo:table-row>
	</xsl:template>
	
	<xsl:template match="cell">
    	<fo:table-cell border="1pt solid black" >
	    	<xsl:call-template name="attr" />
   			 <fo:block linefeed-treatment="preserve">
    			<xsl:value-of select="." />
   			 </fo:block>
    	</fo:table-cell>
	</xsl:template>
	
	<xsl:attribute-set name="colors">
		<xsl:attribute name="background-color">
   			blue
		</xsl:attribute>
		<xsl:attribute name="color">
   			red
		</xsl:attribute>
	</xsl:attribute-set>
	
	<xsl:template name="attr">
		<xsl:for-each select="@*">
		   <xsl:attribute name="{name()}">
		   		<xsl:value-of select="." />
		   </xsl:attribute>
		</xsl:for-each>
	</xsl:template>
	
	
	
</xsl:stylesheet>