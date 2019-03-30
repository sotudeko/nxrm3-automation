import groovy.json.JsonSlurper

class NXRM3Components {

   static void main(String[] args) {

      def repositoryUrl = args[0]
      def repositoryName = args[1]

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

               print it.id 

               if (it.format == 'maven2'){
                  println ' (' + it.group + '.' + it.name + ':' + it.version + ')'
               }

               numberOfComponents++
            }

            continueToken = jsonObject.continuationToken

            if (continueToken != null){
               repoAddress = endpoint + query + '&continuationToken=' + continueToken
            } 
         }
      }

      println 'Number of components: ' + numberOfComponents
   }
}





