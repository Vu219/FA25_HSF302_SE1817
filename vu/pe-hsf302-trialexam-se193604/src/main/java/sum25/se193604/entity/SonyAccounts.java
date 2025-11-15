package sum25.se193604.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Table(name = "SonyAccounts")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SonyAccounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AccountID")
    private int accountID;

    @Column(name="Phone", nullable = false, columnDefinition = "nvarchar(13)")
    private String phone;

    @Column(name="Password", nullable = false, columnDefinition = "nvarchar(50)")
    private String password;

    @Column(name="RoleID", nullable = false)
    private int roleID;

    public SonyAccounts(String phone, String password, int roleID) {
        this.phone = phone;
        this.password = password;
        this.roleID = roleID;
    }
}
