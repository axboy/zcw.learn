```
grails install-plugin spring-security-core:3.1.1
grails install-plugin spring-security-oauth
grails uninstall-plugin acegi       //todo

def user = User.get(springSecurityService.principal.id)
```

[grails-spring-security-core/v3](http://grails-plugins.github.io/grails-spring-security-core/v3/index.html)