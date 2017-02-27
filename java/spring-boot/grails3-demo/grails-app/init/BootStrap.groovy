import local.zcw.Role
import local.zcw.User

class BootStrap {

    def init = { servletContext ->
        def role = Role.findByName("ADMIN")
        if(!role){
            role = new Role(name: "ADMIN").save(flush: true)
        }
        //new Role(name: "USER").save(flush: true)
        if(!User.findByUsername("admin")){
            new User(username: "admin", password: "admin", roles: [role]).save(flush: true)
        }
    }
    def destroy = {
    }
}
