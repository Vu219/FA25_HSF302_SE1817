package sum25.se193604.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SonyCategories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SonyCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CateID", nullable = false)
    private Integer cateID;

    @Column(name="CateName", nullable = false, length = 50)
    private String cateName;

    @Column(name="Status", nullable = false, length = 10)
    private String Status;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<SonyProducts> products = new ArrayList<>();
}
