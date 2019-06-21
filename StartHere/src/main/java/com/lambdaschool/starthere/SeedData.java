package com.lambdaschool.starthere;

import com.lambdaschool.starthere.models.Role;
import com.lambdaschool.starthere.models.User;
import com.lambdaschool.starthere.models.UserRoles;
import com.lambdaschool.starthere.services.RoleService;
import com.lambdaschool.starthere.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;


    @Override
    public void run(String[] args) throws Exception
    {
        Role roleAdmin = new Role("admin");
        Role roleData = new Role("data");
        Role roleUser = new Role("user");

        roleService.save(roleAdmin);
        roleService.save(roleData);
        roleService.save(roleUser);

        // , data, user
        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), roleAdmin));
        admins.add(new UserRoles(new User(), roleData));
        admins.add(new UserRoles(new User(), roleUser));
        User u1 = new User("admin", "password", admins);
        userService.save(u1);

        // data, user
        ArrayList<UserRoles> datas = new ArrayList<>();
        datas.add(new UserRoles(new User(), roleData));
        datas.add(new UserRoles(new User(), roleUser));
        User u2 = new User("cinnamon", "1234567", datas);
        userService.save(u2);

        // user
        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(), roleData));
        User u3 = new User("barnbarn", "ILuvM4th!", users);
        userService.save(u3);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), roleData));
        User u4 = new User("Bob", "password", users);
        userService.save(u4);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), roleUser));
        User u5 = new User("Jane", "password", users);
        userService.save(u5);
    }
}