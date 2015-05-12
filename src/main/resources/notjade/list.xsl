<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo">
	
	<xsl:template match="list">
    	<fo:list-block>
    		<xsl:call-template name="attr" />
    		<xsl:apply-templates />
		</fo:list-block>
	</xsl:template>
	
	<xsl:template match="listitem">
		<fo:list-item>
			<xsl:call-template name="attr" />
			<fo:list-item-label end-indent="label-end()">
				<fo:block>
					  <fo:inline font-family="Symbol">&#x2022;</fo:inline>
				</fo:block>
			</fo:list-item-label>
			<fo:list-item-body start-indent="body-start()">
				<fo:block>
					<xsl:apply-templates />
				</fo:block>
			</fo:list-item-body>
		</fo:list-item>
	</xsl:template>
	
	
</xsl:stylesheet>