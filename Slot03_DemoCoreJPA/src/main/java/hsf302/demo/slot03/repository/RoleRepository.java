package hsf302.demo.slot03.repository;

import hsf302.demo.slot03.model.RoleEntity;

public interface RoleRepository {
    public RoleEntity getRoleById(Long roleId);
    public boolean addRole(RoleEntity role);
}
