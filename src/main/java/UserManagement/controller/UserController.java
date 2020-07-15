package UserManagement.controller;

import UserManagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import UserManagement.service.UserService;

@Controller
@RequestMapping("/")
public class UserController {

    private UserService service;

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @GetMapping
    public String allUsers(Model model) {
        model.addAttribute("users", service.getAllUsers());
        return "users";
    }

    @GetMapping("add")
    public String addUserForm() {
        return "add";
    }

    @PostMapping("add")
    public String addUser(@ModelAttribute User user) {
        service.addUser(user);
        return "redirect:/";
    }

    @GetMapping("edit")
    public String editUserForm(@RequestParam Long id, Model model) {
        model.addAttribute("user", service.getUserById(id));
        return "edit";
    }

    @PostMapping("edit")
    public String editUser(@ModelAttribute User user) {
        service.editUser(user);
        return "redirect:/";
    }

    @GetMapping("delete")
    public String deleteUser(@RequestParam Long id) {
        service.deleteUser(id);
        return "redirect:/";
    }
}
