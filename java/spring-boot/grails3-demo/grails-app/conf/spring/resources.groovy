import local.zcw.MyAuthenticationProvider
import local.zcw.UserDetailsService

// Place your Spring DSL code here
beans = {
    userDetailsService(UserDetailsService)
    authenticationProvider(MyAuthenticationProvider)
}
