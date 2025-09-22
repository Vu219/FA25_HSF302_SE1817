package hsf302.demo.slot03.model;

import jakarta.persistence.*;

@Entity
@Table(name = "teachers")

public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //tang tu dong
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name ="email", length = 50, nullable = false, unique = true)
    private String email;

    @Column(name = "password", length = 20, nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity roleEntity;

    public MemberEntity() {
    }

    public MemberEntity(String email, String name, String password, RoleEntity roleEntity) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.roleEntity = roleEntity;
    }

    public MemberEntity(Long id, String name, String email, String password, RoleEntity roleEntity) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.roleEntity = roleEntity;
    }

    public MemberEntity(MemberEntity memberEntity) {
        this.name = memberEntity.getName();
        this.email = memberEntity.getEmail();
        this.password = memberEntity.getPassword();
        this.roleEntity = memberEntity.getRoleEntity();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleEntity getRoleEntity() {
        return roleEntity;
    }

    public void setRoleEntity(RoleEntity roleEntity) {
        this.roleEntity = roleEntity;
    }
}
