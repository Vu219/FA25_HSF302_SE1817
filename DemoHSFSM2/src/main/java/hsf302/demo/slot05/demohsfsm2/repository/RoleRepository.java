package hsf302.demo.slot05.demohsfsm2.repository;

import hsf302.demo.slot05.demohsfsm2.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    public Role findByRoleName(String roleName);
}
