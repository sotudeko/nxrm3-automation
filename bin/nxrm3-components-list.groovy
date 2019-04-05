import groovy.json.JsonSlurper
import groovy.json.JsonOutput;

class NXRM3Components {

   static void main(String[] args) {

      def dockerRepoConnector    
      def repositoryUrl = args[0]
      def repositoryName = args[1]

      if (args.length > 2){
         dockerRepoConnector = args[2]
      }

      def endpoint = repositoryUrl + '/service/rest/v1/components'
      def query = '?repository=' + repositoryName
      def repoAddress = endpoint + query

      def continueToken = 'start'
      int numberOfComponents = 0

      while (continueToken != null){

         def url = new URL(repoAddress)
         def connection = url.openConnection()
         connection.requestMethod = 'GET'

         if (connection.responseCode == 200) {
            def content = connection.content.text

            def jsonSlurper = new JsonSlurper()
            def jsonObject = jsonSlurper.parseText(content)

            jsonObject.items.each { 

               switch(it.format){
                  case 'maven2': printMaven(it); break
                  case 'npm': printNpm(it); break
                  case 'nuget': printList(it); break
                  case 'docker': printDocker(it, dockerRepoConnector); break
                  default: break
               }

               numberOfComponents++
            }

            continueToken = jsonObject.continuationToken

            if (continueToken != null){
               repoAddress = endpoint + query + '&continuationToken=' + continueToken
            } 
         }
      }

      // println 'Number of components: ' + numberOfComponents
   }

   static def printRaw(it){
      def pretty = JsonOutput.toJson(it)
      println pretty
   }

   static printList(it){

      def listing

      if (it.format == 'maven2'){
         listing = it.id + ' (' + it.group + '.' + it.name + ':' + it.version + ')'
      }
      else {
         listing = it.id + ' (' + it.name + ':' + it.version + ')'
      }

      println listing
      println ' - Assets'
      for (asset in it.assets){
         println '  -- ' + asset.id + ' ' + asset.downloadUrl
      }
   }

   static def printMaven(it){
      println '  <dependency>' 
      println '    <groupId>' + it.group + '</group>'
      println '    <artifactId>' + it.name + '</artifactId>'
      println '    <version>' + it.version + '</version>'
      println '  </dependency>'
   }

   static def printNpm(it){
      println '  "' + it.name + '" : "' + it.version + '"'
   }

   static def printDocker(it, dockerRepoConnector){
      println dockerRepoConnector + '/' + it.name + ':' + it.version 
   }

}





