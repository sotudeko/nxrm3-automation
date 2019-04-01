import groovy.json.JsonOutput
import org.sonatype.nexus.selector.*

def selectorManager = container.lookup(SelectorManager.class.name)

def cs = selectorManager.browse()

