package local.zcw

import grails.plugin.springsecurity.userdetails.GormUserDetailsService
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Component("userDetailsService")
class UserDetailsService extends GormUserDetailsService {

    @Override
    UserDetails loadUserByUsername(String username, boolean loadRoles) throws UsernameNotFoundException {
        println("1aaasdasdas")
        return super.loadUserByUsername(username, loadRoles)
    }

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        println("123123123")
        def user = User.findByUsername(username)
        if (!user) {
            throw new UsernameNotFoundException("用户名不存在")
        }
        println("aaaaa${user.username}")
        return super.loadUserByUsername(username)
    }

    @Override
    protected Collection<GrantedAuthority> loadAuthorities(Object user, String username, boolean loadRoles) {
        return super.loadAuthorities(user, username, loadRoles)
    }
}
