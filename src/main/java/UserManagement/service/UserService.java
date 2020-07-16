package UserManagement.service;

import UserManagement.model.Role;
import UserManagement.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long id);

    void addUser(User user);

    void deleteUser(Long id);

    void editUser(User user);

    List<Role> getAllRoles();

    Role getRoleByName(String name);
}
