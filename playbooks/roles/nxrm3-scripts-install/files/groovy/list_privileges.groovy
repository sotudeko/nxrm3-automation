import groovy.json.JsonOutput

allPrivileges = security.getSecuritySystem().listPrivileges()

return JsonOutput.toJson(allPrivileges)

