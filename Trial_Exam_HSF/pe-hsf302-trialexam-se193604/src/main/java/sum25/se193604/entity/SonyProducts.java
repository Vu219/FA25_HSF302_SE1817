package sum25.se193604.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "SonyProducts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SonyProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductID", nullable = false)
    private long  productID;

    @NotBlank(message = "Product name cannot be blank")
    @Size(min = 5, max = 50, message = "Product name should be between 5 and 50 characters")
    @Column(name = "ProductName", nullable = false, length = 50)
    private String productName;

    @NotNull(message = "Price cannot be null")
    @Min(value = 100, message = "Price should be greater than or equal to 100")
    @Column(name = "Price", nullable = false)
    private Integer price;

    @NotNull(message = "Stock cannot be null")
    @Min(value = 0, message = "Stock should be greater than or equal to 0")
    @Max(value = 1000, message = "Stock should be less than or equal to 1000")
    @Column(name = "Stock", nullable = false)
    private Integer stock;

    @Column(name="CreatedAt", nullable = false, columnDefinition = "datetime")
    private LocalDateTime createdAt;

    @NotNull(message = "Category is required")
    @ManyToOne
    @JoinColumn(name = "CateID", nullable = false)
    @ToString.Exclude
    private SonyCategories category;

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
}
