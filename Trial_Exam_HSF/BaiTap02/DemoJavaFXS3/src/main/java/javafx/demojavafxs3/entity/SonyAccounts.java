package javafx.demojavafxs3.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SonyAccounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SonyAccounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AccountID", nullable = false)
    private int accountID;

    @Column(name="Phone", nullable = false, length = 13)
    private String phone;

    @Column(name="Password", nullable = false, length = 10)
    private String password;

    @Column(name="RoleID", nullable = false)
    private int roleID;
}
