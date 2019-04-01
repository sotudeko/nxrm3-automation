import groovy.json.JsonOutput

allRoles = security.getSecuritySystem().listRoles()
allPrivileges = security.getSecuritySystem().listPrivileges()

List<Map<String, String>> roleDetails = []
Map roleResults = [changed: true, error: false]
roleResults.put('role_details', roleDetails)
        
numberOfRoles = allRoles.size()
log.info("Listing roles: total: $numberOfRoles")

for (r in allRoles) {

    Map<String, String> currentRole = [id: "$r.roleId", name: "$r.name", source: "$r.source", privileges: []] 
    
    for(p in r.privileges) {
        rolePrivilege = allPrivileges.find { it.id == (p) }
        if (rolePrivilege) {
            currentPrivilege = [id: "$rolePrivilege.id", name: "$rolePrivilege.name", description: "$rolePrivilege.description"]
            currentRole.privileges.add(currentPrivilege)
        }
    }

    roleResults['role_details'].add(currentRole)
}

return JsonOutput.toJson(roleResults)
