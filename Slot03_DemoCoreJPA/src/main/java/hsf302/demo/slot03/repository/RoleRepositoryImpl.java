package hsf302.demo.slot03.repository;

import hsf302.demo.slot03.dao.RoleDAO;
import hsf302.demo.slot03.model.RoleEntity;

public class RoleRepositoryImpl implements RoleRepository {
    private RoleDAO roleDAO;

    public  RoleRepositoryImpl(String jpaName) {
        roleDAO = new RoleDAO(jpaName);
    }

    @Override
    public RoleEntity getRoleById(Long roleId) {
        return roleDAO.getRoleById(roleId);
    }

    @Override
    public boolean addRole(RoleEntity role) {
        return roleDAO.addRole(role);
    }
}
