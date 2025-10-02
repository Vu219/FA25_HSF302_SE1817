package hsf302.demo.slot05.demohsfsm2.repository;

import hsf302.demo.slot05.demohsfsm2.entity.Book;
import hsf302.demo.slot05.demohsfsm2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByUser(User user);
}