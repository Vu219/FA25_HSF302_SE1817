package hsf302.demo.slot03.service;

import hsf302.demo.slot03.model.RoleEntity;

public interface RoleService {
    public boolean addRole(RoleEntity role);
    public RoleEntity getRoleById(Long roleId);
}
