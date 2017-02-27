package local.zcw

class User {

    transient springSecurityService

    static constraints = {
    }

    static mapping = {
        id generator: 'uuid.hex'
    }

    String id;

    String username;

    String password;

    Set roles = [];

    static hasMany = [
            roles: Role
    ]

    Set<Role> getAuthorities() {
        this.roles as Set
    }

    def beforeInsert() {
        encodePassword()
    }

    def beforeUpdate() {
        if (isDirty('password')) {
            encodePassword()
        }
    }

    protected void encodePassword() {
        password = springSecurityService.encodePassword(password)
    }
}
