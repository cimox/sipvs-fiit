# sipvs-fiit
SIPVS FIIT electronic signature agony
#### Assigment
_NullPointerException_

1. #####zakladne pojmy - 20%
	- rychly uvod do xml, xsd, xsl
	- navrh konkretneho formulara (xsd, xsl) - mozeme specifikovat co presne ma obsahovat, alebo to nechame studentov.
	- vystupom je xsd, xsl, pripadne sample xml zodpovedajuci xsd
	- implementacia jednoduchej aplikacie pre tipovanie poloziek formulara, overenie voci scheme a jej zobrazenie pomocou xsl
	- vystupom je funkcna aplikacia

2. #####vytvorenie elektronickeho podpisu - 20%
	- uvod do xml signature a XAdES + formy xadesu (minimalne EPES a T - s tymi sa bude pracovat)
	- uvod do certifikatov x509
	- rozsirenie aplikacie o integraciu podpisovaca DSigner XAdES
	- vystupom funkcna aplikacia integrujuca podpisovac a podpisane xml (XAdES-EPES) ulozene v subore

3. #####casova peciatka - 20%
	- uvod do casovej peciatky
	- implementacia aplikacie ziadajucej casovu peciatku a rozsirenie XAdES-EPES na XAdES-T
	- vystupom je rozsireny podpis o casovu peciatku - XAdES-T ulozeny v subore

4. #####overenie elektronickeho podpisu - 40%
	- uvod do problematiky overovania
	- uvod co crl a vztahu s certifikatmi
	- implementacia jednoducheho overovaca, ktory overi XAdES-T. implementacia bude pozostavat z vybranych kontrol (nie cela cert. cesta, ale iba podpisovy certifikat voci crl, atd...)
	- vystupom je aplikacia s informaciou o platnosti podpisu XAdES-T -> mozne testovat aj navzajom v ramci skupin

### Dictionary:
* *XML* Extensible Markup Language, XML is a very popular simple text based language to be used as a mode of communication between the applications. It is considered as a standard means to transport and store data. JAVA provides an execellent support and rich set of libraries to parse, modify or inquire XML documents. (Wiki)
* *XPath* is used to navigate through elements and attributes in an XML document. (W3schools)
* *XSL* stands for EXtensible Stylesheet Language, and is a style sheet language for XML documents. (W3schools)
* *XSLT* stands for XSL Transformations. In this tutorial you will learn how to use XSLT to transform XML documents into other formats, like XHTML. (W3schools)

### Sources:
* XML schema: http://www.w3schools.com/xml/schema_intro.asp
* XSL+XSLT: http://www.w3schools.com/xsl/
* Java XML tutorial with various parsers: http://www.mkyong.com/tutorials/java-xml-tutorials/
* Java XML transformations (XSLT): https://docs.oracle.com/javase/tutorial/jaxp/xslt/transformingXML.html
* XML Signature: there is enough information available, but first we need to know a bit more about assignment.

### Poznamky z cvik:
#### 1. cvicenie
email: major@ditec.sk; poklady na cviko:
* http://test.ditec.sk/fiit/temy.txt
* http://test.ditec.sk/fiit/cvicenie1.zip
##### Minimalne poziadavky na 1 cast zadania:
1. XSD, Minimalne poziadavky: atribut, 3 datove typy, opakovatelna sekcia (knihy, …, tlacidlo "+" v GUI). Cca 10 poloziek na vyplnenie.
2. GUI, mozme zobrat aj existujuci formular a prerobit ho/zjednodusit. malo by to pekne vyzerat.
3. Uloz tlacidlo, ulozi data z formularu do XML suboru a spyta sa kam na disk ho ma ulozit.
4. XSLT, transformacia do HTML/TXT. HTML _nesmie_ obsahovat externe zdroje (napr css, jquery, obrazky, ...)
5. Validuj tlacidlo, precita XML + XSD a zvaliduje XML (generovane aplikaciou) voci scheme XSD.
6. Transformuj tlacidlo, XML + XSLT => vystup zobrazi/subor. Pouzit to co ponuka Java (aj v pripade bodu 6.).
