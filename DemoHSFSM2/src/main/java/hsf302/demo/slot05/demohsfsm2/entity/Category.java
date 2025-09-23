package hsf302.demo.slot05.demohsfsm2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryID")
    private int CategoryID;

    @Column(name = "Category_Name", nullable = false, length = 100)
    private String Category_Name;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<Book>();
}
