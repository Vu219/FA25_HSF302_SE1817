package hsf302.demo.slot05.demohsfsm2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BookID")
    private int BookID;

    @Column(name = "Title", nullable = false, length = 100)
    private String Title;

    @Column(name = "ISBN", nullable = false, length = 100)
    private String ISBN;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "Book_Categories",
        joinColumns = @JoinColumn(name = "BookID"),
        inverseJoinColumns = @JoinColumn(name = "CategoryID")
    )
    private List<Category> categories = new ArrayList<Category>();

}
