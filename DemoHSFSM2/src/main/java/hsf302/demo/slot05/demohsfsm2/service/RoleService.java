package hsf302.demo.slot05.demohsfsm2.service;

import hsf302.demo.slot05.demohsfsm2.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RoleService {
    public boolean addRole(Role role);
    public Role findByRoleName(String roleName);
    public List<Role> getAllRoles();
    public Optional<Role> getRoleById(int roleId);
}
