package com.example.geektext;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

//AUTO IMPLEMENTED by spring into a bean called Respository
//CRUD refers to Create, Read, Update, Delete
public interface BookRepository extends CrudRepository <Book, String>
{
    //finding book via genre
    List<Book> findBybookGenre (String genre);

    // finding book by ISBN
    List<Book> findBybookIsbn (String bookIsbn);
}
