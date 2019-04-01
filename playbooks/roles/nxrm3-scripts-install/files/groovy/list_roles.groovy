import groovy.json.JsonOutput

allRoles = security.getSecuritySystem().listRoles()

return JsonOutput.toJson(allRoles)

