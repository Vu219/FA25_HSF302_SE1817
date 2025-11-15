package fa25.se193604.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "manufacturers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class manufacturers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manufacturer_id")
    private int manufacturerId;

    @Column(name = "manufacturer_name", nullable = false, length = 100)
    private String manufacturerName;

    @Column(name = "country", length = 100)
    private String country;

    @OneToMany(mappedBy = "manufacturer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<computers> computers = new ArrayList<>();
}
