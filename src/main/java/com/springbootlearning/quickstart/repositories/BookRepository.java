package com.springbootlearning.quickstart.repositories;

import com.springbootlearning.quickstart.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, String> {

}
