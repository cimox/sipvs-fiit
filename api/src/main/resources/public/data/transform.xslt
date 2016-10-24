<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
  <html>
  <body>
    <h1>Book rental</h1>
    <h4>Person details</h4>
    <xsl:for-each select="rental/person">
      <b>Name: </b><xsl:value-of select="firstName" /><xsl:value-of select="lastName" /><br />
      <b>Email: </b><xsl:value-of select="email" /><br />
      <b>Address: </b><br />
      <xsl:for-each select="address">
        <xsl:value-of select="street" />&#160;<xsl:value-of select="streetNumber" /><br />
        <xsl:value-of select="postalCode" />&#160;<xsl:value-of select="city" /><br />
        <xsl:value-of select="country" />
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
      <xsl:for-each select="rental/book">
        <tr>
          <td><xsl:value-of select="title"/></td>
          <td><xsl:value-of select="dateFrom"/></td>
          <td><xsl:value-of select="dateTo"/></td>
          <td><xsl:value-of select="@isbn"/></td>
        </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>

</xsl:stylesheet>