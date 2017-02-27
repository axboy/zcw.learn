package local.zcw

import grails.plugin.springsecurity.userdetails.GormUserDetailsService
import grails.transaction.Transactional
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException

@Transactional
class UserService extends GormUserDetailsService {

    @Override
    UserDetails loadUserByUsername(String username, boolean loadRoles) throws UsernameNotFoundException {
        return super.loadUserByUsername(username, loadRoles)
    }

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
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
