import groovy.json.JsonSlurper

class NXRM3Components {

   static void main(String[] args) {

      def repositoryUrl = args[0]

      def repoAddress = repositoryUrl + '/service/rest/v1/script'

      def url = new URL(repoAddress)
      def connection = url.openConnection()
      connection.requestMethod = 'GET'

      if (connection.responseCode == 200) {
         def content = connection.content.text
         println content

         // def jsonSlurper = new JsonSlurper()
         // def jsonObject = jsonSlurper.parseText(content)

         // jsonObject.each { 
         //    println it
         // }
      }
   }
}

