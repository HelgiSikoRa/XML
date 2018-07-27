<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <body>
                <h1>Company Details</h1>
                <table border="1">
                    <tr>
                        <th>EmpId</th>
                        <th>EmpName</th>
                        <th>Age</th>
                        <th>Salary</th>
                    </tr>
                    <xsl:for-each select="oldcard/postcard">
                        <tr>
                            <td>
                                <xsl:value-of select="theme" />
                            </td>
                            <td>
                                <xsl:value-of select="type" />
                            </td>
                            <td>
                                <xsl:value-of select="status" />
                            </td>
                            <td>
                                <xsl:value-of select="country" />
                            </td>
                            <td>
                                <xsl:value-of select="year" />
                            </td>
                            <td>
                                <xsl:value-of select="author" />
                            </td>
                            <td>
                                <xsl:value-of select="thematic" />
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>