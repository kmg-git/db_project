package com.example.db_project.Repository;

import com.example.db_project.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    Page<Book> findByCategoryId(Long categoryId, Pageable pageable);

    List<Book> findByTitleContaining(String keyword);

    List<Book> findByStockLessThan(int stock);

    @Query("SELECT b from Book b " +
            "where b.stock <= :i and b.category = :s " +
            "order by b.price desc")
    List<Book> findAllbyCustom(@Param("i") int i,@Param("s") String s);


}
