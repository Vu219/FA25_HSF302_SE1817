package fa25.se193604.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "suppliers")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Suppliers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id")
    int supplierId;

    @Column(name="supplier_name", unique = true, nullable = false, columnDefinition = "varchar(50)")
    String supplierName;

    @Column(name="contact_phone", columnDefinition = "varchar(15)")
    String contactPhone;

    @Column(name="email", nullable = false, columnDefinition = "varchar(50)")
    String email;

    @OneToMany(mappedBy = "suppliers", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Rices> rices = new ArrayList<>();

    public Suppliers(String supplierName, String contactPhone, String email) {
        this.supplierName = supplierName;
        this.contactPhone = contactPhone;
        this.email = email;
    }
}
