package UserManagement.controller;

import UserManagement.model.Role;
import UserManagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import UserManagement.service.UserService;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/")
public class UserController {

    private UserService service;

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @GetMapping
    public String getStartPage(){
        return "index";
    }

    @GetMapping("login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("user")
    public String getUserPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("admin")
    public String getAllUsers(Model model) {
        model.addAttribute("users", service.getAllUsers());
        return "users";
    }

    @GetMapping("admin/add")
    public String getAddUserPage(Model model) {
        model.addAttribute("roles", service.getAllRoles());
        return "addUser";
    }

    @PostMapping("admin/add")
    public String addUser(@ModelAttribute User user, @RequestParam("usrRoles") Set<Role> roles) {
        Set<Role> roleSet = new HashSet<>();
        for (Role role : roles) {
            roleSet.add(service.getRoleByName(role.getRole()));
        }
        user.setRoles(roleSet);
        service.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("admin/edit")
    public String getEditUserPage(@RequestParam Long id, Model model) {
        model.addAttribute("user", service.getUserById(id));
        model.addAttribute("roles", service.getAllRoles());
        return "editUser";
    }

    @PostMapping("admin/edit")
    public String editUser(@ModelAttribute User user, @RequestParam("usrRoles") Set<Role> roles) {
        Set<Role> roleSet = new HashSet<>();
        for (Role role : roles) {
            roleSet.add(service.getRoleByName(role.getRole()));
        }
        user.setRoles(roleSet);
        service.editUser(user);
        return "redirect:/admin";
    }

    @GetMapping("admin/delete")
    public String deleteUser(@RequestParam Long id) {
        service.deleteUser(id);
        return "redirect:/admin";
    }
}
