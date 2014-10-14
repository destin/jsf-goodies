import javax.xml.transform.TransformerFactory
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.stream.StreamSource

import groovy.text.*

def dir = new File('.')

def dirs = dir.listFiles(
        [accept: { d, f -> new File(d, f).isDirectory() && !"legal".equals(f) }] as FilenameFilter
)

dirs.each(this.&generateXsdsFor)

def generateXsdsFor(dir) {
    def taglibDir = new File(dir, 'taglibs')
    def taglibs = taglibDir.list(
            [accept: { d, f -> f ==~ /.*\.taglib.xml/ }] as FilenameFilter
    )
    taglibs.each { taglib -> generateXsd(taglibDir, taglib, dir) }
}

def generateXsd(taglibDir, taglib, outDir) {
    def taglibFile = new File(taglibDir, taglib)
    print "Processing $taglibFile ..."
    def factory = TransformerFactory.newInstance()
    def engine = new SimpleTemplateEngine()
    def taglibText = taglibFile.text
    def namespace = (taglibText =~ /xmlns="([^"]+)"/)[0][1]
    def transform = engine.createTemplate(new File('TaglibToXSD.xslt')).make([tlibNamespace : namespace]).toString()
    def transformer = factory.newTransformer(new StreamSource(new StringReader(transform)))
    def outFile = new File(outDir, taglib.replaceFirst("\\.taglib\\.xml", ".xsd"))
    transformer.transform(new StreamSource(new StringReader(taglibText)), new StreamResult(new FileWriter(outFile, false)))
}
