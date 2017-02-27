import local.zcw.Role
import local.zcw.User

/**
 * Created by zcw on 2017/02/28.
 */

//////////////////////////////////spring security配置
grails.plugin.springsecurity.userLookup.userDomainClassName = User.class.name
grails.plugin.springsecurity.userLookup.authorityJoinClassName = Role.class.name
grails.plugin.springsecurity.securityConfigType = "Annotation"
grails.plugin.springsecurity.successHandler.defaultTargetUrl = '/'
grails.plugin.springsecurity.providerNames = [
        "myAuthenticationProvider",
        //"daoAuthenticationProvider",
        //"anonymousAuthenticationProvider",
        // "rememberMeAuthenticationProvider"
]

grails {
    plugins {
        springsecurity {
            active = true
            userLookup.userDomainClassName = "local.zcw.User"
            authority.className = "local.zcw.Role"
        }
    }
}
