package services.dao;

import services.Book;
import services.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookDaoService implements BookDao{
    private  Connection conn;

    public BookDaoService(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void addBook(Book book){
        try {
            String sql = "INSERT INTO Book(title,author,publisher,publication_year,available_copies, synopsis,isbn) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3,book.getPublisher());
            ps.setInt(4,book.getPublicationYear());
            ps.setInt(5,book.getAvailableCopies());
            ps.setString(6, book.getSynopsis());
            ps.setDouble(7, book.getISBN());
            ps.executeUpdate();

        }catch (Exception e){
            throw new RuntimeException("Failed to add book " + e.getMessage());
        }
    }

    @Override
    public Book getSingleBook(long isbn){
        Book book = new Book();
        try {
            String sql = "SELECT * FROM Book WHERE ISBN=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1,isbn);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                book.setISBN(isbn);
                book.setTitle(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                book.setAvailableCopies(rs.getInt("available_copies"));
                book.setPublisher(rs.getString("publisher"));
                book.setPublicationYear(rs.getInt("publication_year"));
                book.setSynopsis(rs.getString("synopsis"));

            }
        }catch (Exception e){
            throw new RuntimeException("Failed to fetch book " + e.getMessage());
        }

        return book;
    }

    @Override
    public List<Book> getAllBooks(){
        List<Book> books = new ArrayList<Book>();
        try{
            String sql = "SELECT * FROM Book";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Book book = new Book();
                book.setISBN(rs.getLong("isbn"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setAvailableCopies(rs.getInt("available_copies"));
                book.setPublisher(rs.getString("publisher"));
                book.setPublicationYear(rs.getInt("publication_year"));
                book.setSynopsis(rs.getString("synopsis"));
                books.add(book);
            }
        }
        catch (Exception e){
            throw new RuntimeException("Failed to fetch all books " + e.getMessage());
        }

        return books;
    }

    @Override
    public void updateBook(Book book){
        try{
            String sql = "UPDATE Book SET title=?,author=?,publisher=?,publication_year=?,available_copies=?,synopsis=? WHERE ISBN=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getPublisher());
            ps.setInt(4, book.getPublicationYear());
            ps.setInt(5,book.getAvailableCopies());
            ps.setString(6,  book.getSynopsis());
            ps.setLong(7,book.getISBN() );
            ps.executeUpdate();
        }catch (Exception e){
            throw new RuntimeException("Failed to update book " + e.getMessage());
        }

    };

    @Override
    public void deleteBook(long isbn){
    try {
        String sql = "DELETE FROM Book WHERE ISBN=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, isbn);
        ps.executeUpdate();
    }catch (Exception e){
        throw new RuntimeException("Failed to update book " + e.getMessage());
    }
    };

}
