package local.zcw.rest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.util.Date;

/**
 * Created by zcw on 2/8/17.
 */
@RestController
public class AuthController {

    @RequestMapping("/")
    @ResponseBody
    //@PreAuthorize("permitAll")
    //@PermitAll
    //@RolesAllowed({"USER", "ADMIN"})
    public String index(Principal curUser) {
        String userName = "unLogin";
        System.out.print(curUser);
        if (curUser != null) {
            userName = curUser.getName();
        }
        return "Hello! =============" + userName;
    }
}
