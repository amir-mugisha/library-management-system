package services.dao;

import services.Book;

import java.util.List;

public interface BookDao {
    void addBook(Book book);
    Book getSingleBook(long ISBN);
    List<Book> getAllBooks();
    void updateBook(Book book);
    void deleteBook(long isbn);
}
