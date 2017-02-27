package local.zcw

//import org.springframework.security.access.prepost.PreAuthorize
//import grails.plugins.springsecurity.Secured

class MyAuthController {

    //@PreAuthorize("permitAll")
    def index() {
        render "hello world"
    }
}
