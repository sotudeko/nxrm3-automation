import groovy.json.JsonSlurper
import groovy.json.JsonOutput;

class NXRM3Components {

   static void main(String[] args) {

      def jsonFile = args[0]
      int numberOfItems = 0

      def jsonSlurper = new JsonSlurper()

      def reader = new BufferedReader(new InputStreamReader(new FileInputStream(jsonFile),"UTF-8"))
      def jsonData = jsonSlurper.parse(reader)  

      jsonData.each { 
         println it.name // privileges
         // println it.roleId // roles
         numberOfItems++
      }

      println 'number of items: ' + numberOfItems
   }
}





