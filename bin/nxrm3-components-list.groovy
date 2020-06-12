import groovy.json.JsonSlurper
import groovy.json.JsonOutput
import static groovy.json.JsonOutput.*
import groovy.cli.picocli.CliBuilder


class NXRM3Components {

   static void main(String[] args) {

      def cli = new CliBuilder()

      cli.url(type: String, 'url-arg')
      cli.name(type: String, 'name-arg')
      cli.raw(type: Boolean, 'raw-arg')

      def options = cli.parse(args)

      if (!options) {
         System.err << 'Error while parsing command-line options.\n'
         System.exit 1
      }

      def repositoryUrl = options.url 
      def repositoryName = options.name
      def raw = options.raw

      def endpoint = repositoryUrl + '/service/rest/v1/components'
      def query = '?repository=' + repositoryName
      def repoAddress = endpoint + query

      def continueToken = 'start'
      int numberOfComponents = 0

      while (continueToken != null){

         def url = new URL(repoAddress)
         def connection = url.openConnection()

         connection.requestMethod = 'GET'
         connection.setRequestProperty("Authorization", "Basic YWRtaW46YWRtaW4xMjM=")

         if (connection.responseCode == 200) {
            def content = connection.content.text

            def jsonSlurper = new JsonSlurper()
            def jsonObject = jsonSlurper.parseText(content)

            jsonObject.items.each { 

               if (raw){
                  printRaw(it)
               }
               else {
                  printList(it)          
               }
               
               numberOfComponents++
            }

            continueToken = jsonObject.continuationToken

            if (continueToken != null){
               repoAddress = endpoint + query + '&continuationToken=' + continueToken
            } 
         }
      }
       
      if (raw){
         println ''
         println  repositoryName + ' (' + repositoryUrl + ') - number of components: ' + numberOfComponents
         println ''
      }
   }

   static def printRaw(it){
      def pretty = JsonOutput.toJson(it)
      println prettyPrint(pretty)
   }

   static printList(it){
      for (asset in it.assets){
         String downloadUrl = asset.downloadUrl
         if (!downloadUrl.matches(".*(.pom|.md5|.sha1)")){
            println downloadUrl
         }
      }
   }
}





