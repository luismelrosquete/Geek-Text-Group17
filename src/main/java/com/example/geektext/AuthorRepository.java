package com.example.geektext;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface AuthorRepository extends CrudRepository<Book, Integer>
{ }
