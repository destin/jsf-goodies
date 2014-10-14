jsf-xsd
=======

XSD files for JSF tags. XSD files were generated from *.taglib.xml files using generateXSD.groovy script and 
TaglibToXSD.xslt transform.

*.taglib.xml files were extracted from:

 * jsf-impl-2.0.11-01.jar
 * jsf-impl-2.2.8-02.jar

TaglibToXSD.xslt transform was originally developed by Mark A. Ziesemer

I updated the transform to match 2.x taglib.xml files. I also added some additional checks.

Because of namespace changes the transform is parametrized with namespace uri. In order to use it replace $tlibNamespace
with either:

 * http://java.sun.com/xml/ns/javaee - for JSF < 2.2
 * http://xmlns.jcp.org/xml/ns/javaee - for JSF 2.2

or use generateXSD.groovy script.
