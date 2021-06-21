package au.com.user.access.rest;

import au.com.user.access.service.UserAccessService;
import au.com.user.access.service.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAccessController {

    @Autowired
    private UserAccessService service;

    @GetMapping("/user/{userId}")
    public UserBean getUserDetails(@PathVariable String userId) {
        try {
            return service.executePSScript("script.ps1", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
