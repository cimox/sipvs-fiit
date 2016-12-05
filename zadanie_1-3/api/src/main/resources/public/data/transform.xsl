<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:egonp="http://schemas.gov.sk/form/ED.DeliveryStatus/1.2"
                xmlns:ns="http://some.uri.org">
    <xsl:output method="html" xpath-default-namespace="http://www.w3.org/1999/xhtml" indent="yes"
                omit-xml-declaration="yes"/>
    <!--<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">-->
    <xsl:output method="html" version="1.0" indent="yes" omit-xml-declaration="yes" encoding="windows-1252"/>
    <xsl:template match="/ns:rental">
        <xsl:text disable-output-escaping='yes'>&lt;!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"&gt;</xsl:text>
        <html xsl:version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml"
              xml:lang="en">
            <body>
                <h1>Book rental</h1>
                <h4>Person details</h4>
                <xsl:for-each select="ns:person">
                    <b>Name:</b>
                    <xsl:value-of select="ns:firstName"/>
                    <xsl:value-of select="ns:lastName"/>
                    <br/>
                    <b>Email:</b>
                    <xsl:value-of select="ns:email"/>
                    <br/>
                    <b>Address:</b>
                    <br/>
                    <xsl:for-each select="ns:address">
                        <xsl:value-of select="ns:street"/>&#160;
                        <xsl:value-of select="ns:streetNumber"/>
                        <br/>
                        <xsl:value-of select="ns:postalCode"/>&#160;
                        <xsl:value-of select="ns:city"/>
                        <br/>
                        <xsl:value-of select="ns:country"/>
                    </xsl:for-each>
                </xsl:for-each>
                <h4>Books details</h4>
                <table border="1">
                    <tr bgcolor="#9acd32">
                        <th>Title</th>
                        <th>Date from</th>
                        <th>Date to</th>
                        <th>ISBN</th>
                    </tr>
                    <xsl:for-each select="ns:book">
                        <tr>
                            <td>
                                <xsl:value-of select="ns:title"/>
                            </td>
                            <td>
                                <xsl:value-of select="ns:dateFrom"/>
                            </td>
                            <td>
                                <xsl:value-of select="ns:dateTo"/>
                            </td>
                            <td>
                                <xsl:value-of select="@isbn"/>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>