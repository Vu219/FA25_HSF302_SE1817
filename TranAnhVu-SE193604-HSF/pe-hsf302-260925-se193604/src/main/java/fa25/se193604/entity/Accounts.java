package fa25.se193604.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Table(name = "accounts")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    int accountID;

    @Column(name="email", unique = true, nullable = false, columnDefinition = " varchar(50)")
    String email;

    @Column(name="password", nullable = false, columnDefinition = "varchar(20)")
    String password;

    @Column(name="role", nullable = false, columnDefinition = "varchar(10)")
    String role;

    public Accounts(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
