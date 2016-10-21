<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">

 <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
   <fo:layout-master-set>
     <fo:simple-page-master master-name="skeleton">
       <fo:region-body margin="1in"/>
     </fo:simple-page-master>
   </fo:layout-master-set>

   <fo:page-sequence master-reference="skeleton">
     <fo:flow flow-name="xsl-region-body">
       <fo:block font-size="24pt" font-weight="bold">Book rental</fo:block>
	   <fo:block space-before="18pt" font-size="18pt" font-weight="bold">Person details</fo:block>
	   
	   <xsl:for-each select="rental/person">
	   <fo:block space-before="12pt" font-size="12" font-weight="bold">Name: 
	   <fo:inline font-weight="normal"> <xsl:value-of select="firstName" /> &#160; <xsl:value-of select="lastName" /> </fo:inline>
	   </fo:block>  
	   <fo:block font-size="12" font-weight="bold">Email: 
	   <fo:inline font-weight="normal"> <xsl:value-of select="email" /> </fo:inline>
	   </fo:block>
	   <fo:block font-size="12" font-weight="bold">Address: 
	   <fo:block font-weight="normal"> 
		<xsl:for-each select="address">
			<fo:block margin-left = "50pt"> <xsl:value-of select="street" />, &#160; <xsl:value-of select="postalCode" /> </fo:block> 
			<fo:block margin-left = "50pt"> <xsl:value-of select="city" /> </fo:block> 
			<fo:block margin-left = "50pt"> <xsl:value-of select="country" /> </fo:block> 
		</xsl:for-each>
	   </fo:block>
	   </fo:block>
	   </xsl:for-each>
     
	  <fo:block space-before="18pt" font-size="18pt" font-weight="bold">Books details</fo:block>
	  <fo:block font-size="10pt">
		<fo:table border="solid" border-collapse="collapse">
            <fo:table-header background-color="#9acd32" border="inherit">
                <fo:table-row space-after="10px" border="inherit">
                    <fo:table-cell border="inherit">
                        <fo:block font-weight="bold">Title</fo:block>
                    </fo:table-cell>
                    <fo:table-cell border="inherit">
                        <fo:block font-weight="bold">Date from</fo:block>
                    </fo:table-cell>
					<fo:table-cell border="inherit">
                        <fo:block font-weight="bold">Date to</fo:block>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-header>
            <fo:table-body border="inherit">
				<xsl:for-each select="rental/book">
					<fo:table-row border="inherit">
						<fo:table-cell border="inherit">
							<fo:block><xsl:value-of select="title"/></fo:block>
						</fo:table-cell>
						<fo:table-cell border="inherit">
							<fo:block><xsl:value-of select="dateFrom"/></fo:block>
						</fo:table-cell>
					    <fo:table-cell border="inherit">
							<fo:block><xsl:value-of select="dateTo"/></fo:block>
						</fo:table-cell>
					</fo:table-row>
				</xsl:for-each>
            </fo:table-body>

        </fo:table>
    </fo:block>

	  
	 </fo:flow>
   </fo:page-sequence>
 </fo:root>

</xsl:template>
</xsl:stylesheet>