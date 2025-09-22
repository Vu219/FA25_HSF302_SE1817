package hsf302.demo.slot03.service;

import hsf302.demo.slot03.model.RoleEntity;
import hsf302.demo.slot03.repository.RoleRepository;
import hsf302.demo.slot03.repository.RoleRepositoryImpl;

public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    public  RoleServiceImpl(String jpaName) {
        this.roleRepository = new RoleRepositoryImpl(jpaName);
    }

    @Override
    public boolean addRole(RoleEntity role) {
        return roleRepository.addRole(role);
    }

    @Override
    public RoleEntity getRoleById(Long roleId) {
        return roleRepository.getRoleById(roleId);
    }
}
