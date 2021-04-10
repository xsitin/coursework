<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format"
                version="1.0">
    <xsl:output method="xml"/>

    <xsl:template match="/">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
            <fo:layout-master-set>
                <fo:simple-page-master
                        master-name="simple"
                        page-height="29.7cm"
                        page-width="21cm"
                        margin-left="2.5cm"
                        margin-right="2.5cm">
                    <fo:region-body margin-top="3cm"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence
                    master-reference="simple">
                <fo:flow
                        flow-name="xsl-region-body">
                    <fo:block text-align="center" font-weight="bold">
                        table of students marks
                    </fo:block>
                    <xsl:apply-templates/>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>

    <xsl:template match="students">
        <fo:block>
            <fo:table>
                <fo:table-body>
                    <fo:table-row>
                        <fo:table-cell border-style="solid">
                            <fo:block>
                                student name
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell border-style="solid">
                            <fo:block>
                                mark
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>
                    <xsl:for-each select="student">
                        <fo:table-row>
                            <fo:table-cell border-style="solid">
                                <fo:block>
                                    <xsl:value-of select="name"/>
                                </fo:block>
                            </fo:table-cell>
                            <fo:table-cell border-style="solid">
                                <fo:block>
                                    <xsl:value-of select="mark"/>
                                </fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                    </xsl:for-each>
                </fo:table-body>
            </fo:table>
        </fo:block>
    </xsl:template>

</xsl:stylesheet>