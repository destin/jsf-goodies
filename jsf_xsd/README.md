jsf-xsd
=======

XSD files for JSF tags. XSD files were generated from *.taglib.xml files using generateXSD.groovy script and 
TaglibToXSD.xslt transform.

*.taglib.xml files were extracted from:

 * jsf-impl-2.0.11-01.jar
 * jsf-impl-2.2.8-02.jar
 * primefaces-5.3.jar

Generating new XSD files / Regenerating existing XSDs
-----------------------------------------------------

 1. Extract `*.taglib.xml` files from JAR files. They are often placed in `META-INF` directory inside JAR archive.
 2. Place `*.taglib.xml` files for given library into `jsf_xsd/<library_name>/taglibs`, 
    e.g. put `primefaces-p.taglib.xml` and `primefaces-pm.taglib.xml` from primefaces-5.3.jar in `jsf_xsd/primefaces-5.3/taglibs/`
 3. Run `groovy generateXSD.groovy`. The script finds all `*.taglib.xml` in all `jsf_xsd/<library_name>/taglibs` directories and generates XSD files into `jsf_xsd/<library_name>/` directory.

Remarks
-------

`TaglibToXSD.xslt` transform was originally developed by Mark A. Ziesemer

I updated the transform to match 2.x taglib.xml files. I also added some additional checks.

Because of namespace changes the transform is parametrized with namespace uri. If you want to use XSLT directy make sure that you provide target namespace for XSD. For example

 * http://java.sun.com/xml/ns/javaee - for JSF < 2.2
 * http://xmlns.jcp.org/xml/ns/javaee - for JSF 2.2

You can also use `generateXSD.groovy` script to automate this.
