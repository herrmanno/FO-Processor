<?xml version="1.0" encoding="iso-8859-1"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo">
	
	
	<?hard-pagebreak?>
	
	<!-- 
		ATTRIBUTE SETS
	 -->
	
	<xsl:attribute-set name="colors">
		<xsl:attribute name="background-color">
   			blue
		</xsl:attribute>
		<xsl:attribute name="color">
   			red
		</xsl:attribute>
	</xsl:attribute-set>
	
	<!-- 
		ATTRIBUTE-OVERRIDE TEMPLATE
	 -->
	
	<xsl:template name="attr">
		<xsl:for-each select="@*">
		   <xsl:attribute name="{name()}">
		   		<xsl:value-of select="." />
		   </xsl:attribute>
		</xsl:for-each>
	</xsl:template>
	
	
	<xsl:template match="root">
		<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
	  		<fo:layout-master-set>
	    		<fo:simple-page-master master-name="A4" margin-top=".5in" margin-bottom=".5in" margin-left="1in" margin-right="1in">
	      			<fo:region-body margin-top=".5in" margin-bottom=".5in"/>
	      			<fo:region-before region-name="xsl-region-before" extent=".5in"/>
	      			<fo:region-after region-name="xsl-region-after" extent=".5in"/>
	    		</fo:simple-page-master>
	  		</fo:layout-master-set>
	
			<xsl:for-each select="titlepage">
				<fo:page-sequence master-reference="A4">
			    	<fo:flow flow-name="xsl-region-body">
			    		<fo:block font-family="Times" text-align="center" margin="2in">
				    		<xsl:apply-templates />
			    		</fo:block>
			    	</fo:flow>
			  	</fo:page-sequence>
			</xsl:for-each>
	
		 	<fo:page-sequence master-reference="A4" id="page-sequence" initial-page-number="1">
		    	<fo:static-content flow-name="xsl-region-after">
		    		<fo:block font-family="Times">
			    		<xsl:apply-templates select="footer"/>
		    		</fo:block>
		    	</fo:static-content>
		    	<fo:static-content flow-name="xsl-region-before">
		    		<fo:block font-family="Times">
			    		<xsl:apply-templates select="header"/>
		    		</fo:block>
		    	</fo:static-content>
	    	 	<fo:static-content flow-name="xsl-footnote-separator">
      				<fo:block text-align-last="justify">                        
         				<fo:leader leader-length="50%" rule-thickness="0.5pt" leader-pattern="rule"/>               
      				</fo:block>
   				</fo:static-content>
		    	
		    	<fo:flow flow-name="xsl-region-body">
		    		<fo:block font-family="Times">
			    		<xsl:apply-templates select="*[not(self::footer)][not(self::header)][not(self::titlepage)]" />
		    		</fo:block>
		    	</fo:flow>
		  	</fo:page-sequence>
		</fo:root>
	</xsl:template>
	
	<xsl:template match="@* | node()">
	    <xsl:copy>
	        <xsl:apply-templates select="@* | node()"/>
	    </xsl:copy>
	</xsl:template>
	
	<xsl:include href="core.xsl" />
	<xsl:include href="text.xsl" />
	<xsl:include href="header.xsl" />
	<xsl:include href="table.xsl" />
	<xsl:include href="list.xsl" />
	
</xsl:stylesheet>