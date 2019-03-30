import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import org.sonatype.nexus.security.user.UserManager
import org.sonatype.nexus.security.role.NoSuchRoleException
import org.sonatype.nexus.selector.*

def selectorManager = container.lookup(SelectorManager.class.name)

List<Map<String, String>> actionDetails = []
Map scriptResults = [changed: false, error: false]
scriptResults.put('action_details', actionDetails)

parsed_args = new JsonSlurper().parseText(args)

authManager = security.getSecuritySystem().getAuthorizationManager(UserManager.DEFAULT_SOURCE)

Map<String, String> currentResult = [id: parsed_args.id, name: parsed_args.name, status: 'no change']   

try {
    def selectorConfig = new SelectorConfiguration(
            name: parsed_args.name,
            type: 'jexl',
            description: parsed_args.description,
            attributes: ['expression': parsed_args.expression]
    )

    if (selectorManager.browse().find { it -> it.name == selectorConfig.name } == null) {
        selectorManager.create(selectorConfig)
    }

    log.info("Content selector created: ", parsed_args.name)
    scriptResults.changed = true
}
catch (Exception e){
    log.error("Content selector could not be updated", )
    currentResult.status = 'error'
    currentResult.put('error_msg', e.toString())
    scriptResults.error = true
}

scriptResults['action_details'].add(currentResult)
return JsonOutput.toJson(scriptResults)