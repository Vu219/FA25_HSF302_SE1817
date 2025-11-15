package sum25.se193604.entity;

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
@Table(name = "SonyCategories")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SonyCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CateID")
    private int cateID;

    @Column(name="CateName", nullable = false, columnDefinition = "nvarchar(50)")
    private String cateName;

    @Column(name="Status", nullable = false, columnDefinition = "nvarchar(10)")
    private String status;

    @OneToMany(mappedBy = "sonyCategories", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SonyProducts> sonyProducts = new ArrayList<>();

    public SonyCategories(String cateName, String status) {
        this.cateName = cateName;
        this.status = status;
    }
}
