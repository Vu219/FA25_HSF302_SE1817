package hsf302.demo.slot05.demohsfsm2.service;

import hsf302.demo.slot05.demohsfsm2.entity.Role;
import hsf302.demo.slot05.demohsfsm2.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public boolean addRole(Role role) {
        return roleRepository.save(role) != null;
    }

    @Override
    public Role findByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public List<Role> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles;
    }

    @Override
    public Optional<Role> getRoleById(int roleId) {
        return  roleRepository.findById(roleId);
    }

}
