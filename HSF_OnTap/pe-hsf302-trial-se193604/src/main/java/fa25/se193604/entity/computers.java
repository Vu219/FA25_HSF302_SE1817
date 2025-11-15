package fa25.se193604.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "computers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class computers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "computer_id")
    private int computerId;

    @Column(name = "computer_model", nullable = false, length = 100)
    private String computerModel;

    @Column(name = "type", nullable = false, length = 50)
    private String type;

    @Column(name = "production_year")
    private int productionYear;

    @Column(name = "price", columnDefinition = "DECIMAL(10, 2)")
    private double price;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id", nullable = false)
    private manufacturers manufacturer;
}
