package ampath.or.ke.spot.services;

import ampath.or.ke.spot.models.Role;
import ampath.or.ke.spot.repositories.RoleRepository;
import ampath.or.ke.spot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("roleService")
public class RoleService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    Date nowDate = new Date();
    @Autowired
    public RoleService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    public List<Role> getAllRoles(){return  roleRepository.findAll();}
    public Role getRole( String rolename){
        return  roleRepository.findByRole(rolename);
    }

    public Role saveRole(Role role) {
        //  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //User logggeduser = findUserByEmail(auth.getName());
        // user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        //role.setActive(1);
        // role.setCreated_by(1);
        //role.setModified_by(1);
        // role.setModified_on(nowDate);
        // role.setCreated_on(nowDate);


        return roleRepository.save(role);
    }
}

