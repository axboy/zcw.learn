```
grails install-plugin spring-security-core:3.1.1
grails install-plugin spring-security-oauth
grails uninstall-plugin acegi       //todo

def user = User.get(springSecurityService.principal.id)
```