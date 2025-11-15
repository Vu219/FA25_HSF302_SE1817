package fa25.se193604.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "rices")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Rices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rice_id")
    int riceId;

    @NotBlank(message = "Rrice Name cannot be blank")

    @Column(name="rice_name", nullable = false, columnDefinition = "varchar(100)")
    @Size(min = 3, max = 100, message = "Rice name should be between 3 and 100 characters")
    String riceName;

    @NotBlank(message = "Rrice cannot be blank")
    @Min(value = 50, message = "Price should be greater than or equal to $50")
    @Column(name="price", columnDefinition = "DECIMAL(10,2)")
    String price;

    @Column(name="created_at", nullable = false, columnDefinition = "datetime")
    private LocalDateTime createdAt;

    @NotNull(message = "Supplier is required")
    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    @ToString.Exclude
    private Suppliers suppliers;

    public Rices(String riceName, String price, LocalDateTime createdAt, Suppliers suppliers) {
        this.riceName = riceName;
        this.price = price;
        this.createdAt = createdAt;
        this.suppliers = suppliers;
    }

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
}
