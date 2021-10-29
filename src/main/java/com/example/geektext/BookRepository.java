package com.example.geektext;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

//AUTO IMPLEMENTED by spring into a bean called Respository
//CRUD refers to Create, Read, Update, Delete
public interface BookRepository extends PagingAndSortingRepository<Book, String>
{
    //finding book via genre
    List<Book> findBybookGenre (String genre);

    // finding book by ISBN
    List<Book> findBybookIsbn (String bookIsbn);

    // finding book by author
    List<Book> findByBookAuthor (String bookAuthors);

    //finding top sellers (top 10 books that have most copies sold)
    List<Book> findTop10ByOrderByBookCopiesSoldDesc ();

    //finding books by specified rating and higher
    List<Book> findByBookAvgRatingGreaterThanEqual (Double minRating);
}
