package local.zcw

import grails.plugin.springsecurity.userdetails.GormUserDetailsService
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException

class UserDetailsService extends GormUserDetailsService {

    @Override
    UserDetails loadUserByUsername(String username, boolean loadRoles) throws UsernameNotFoundException {
        println("loadUserByUsername...${username}")
        return super.loadUserByUsername(username, loadRoles)
    }

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        println("loadUserByUsername1...${username}")
        def user = User.findByUsername(username)
        if (!user) {
            throw new UsernameNotFoundException("用户名不存在")
        }
        println("curUser:${user.username}")
        return super.loadUserByUsername(username)
    }

    @Override
    protected Collection<GrantedAuthority> loadAuthorities(Object user, String username, boolean loadRoles) {
        return super.loadAuthorities(user, username, loadRoles)
    }
}
