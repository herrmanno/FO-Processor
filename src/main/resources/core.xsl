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
	
	<xsl:template match="footer/table">
    	<fo:table>
    		<xsl:call-template name="attr" />
    		<fo:table-body>
    			<xsl:apply-templates />
    		</fo:table-body>
    	</fo:table>
	</xsl:template>
	
	<xsl:template match="footer/table/row">
    	<fo:table-row>
    		<xsl:call-template name="attr" />
    		<xsl:apply-templates />
    	</fo:table-row>
	</xsl:template>
	
	<xsl:template match="footer/table/row/cell">
    	<fo:table-cell >
	    	<xsl:call-template name="attr" />
   			 <fo:block>
    			<xsl:apply-templates />
   			 </fo:block>
    	</fo:table-cell>
	</xsl:template>
	
	<xsl:template match="footnote">
		 <fo:footnote>
      		<fo:inline font-size="70%" baseline-shift="super">      			
      			<xsl:number level="any" count="footnote" format="1"/>
    		</fo:inline>
		      <fo:footnote-body>                                                      (3)
		         <fo:block font-size="90%">                        
		            <fo:inline font-size="70%" baseline-shift="super">1</fo:inline>
		              <xsl:apply-templates />                        
		         </fo:block>
		      </fo:footnote-body>
   		</fo:footnote>   
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