package hsf302.demo.slot05.demohsfsm2.service;

import hsf302.demo.slot05.demohsfsm2.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    public boolean addRole(Role role);
    public Role findByRoleName(String roleName);
    public List<Role> getAllRoles();
}
