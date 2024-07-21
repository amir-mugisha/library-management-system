package services;

import java.time.LocalDate;

public class Book {
private int id;
private String title;
private String author;
private String publisher;
private int publicationYear;
private int availableCopies;
private long ISBN;
private String synopsis;
private LocalDate created_at;
private  LocalDate updated_at;

public Book(){

}
public Book(String title, String author, String publisher, int publicationYear, int availableCopies, long isbn, String synopsis) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.availableCopies = availableCopies;
        ISBN = isbn;
        this.synopsis = synopsis;
        this.created_at = LocalDate.now();
        this.updated_at = LocalDate.now();
    }

    public int getId(){
    return id;
}

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor(){
    return  author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle(){
    return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher(){
    return  publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPublicationYear(){
    return  publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getAvailableCopies(){
    return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }
    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public LocalDate getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.created_at = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updated_at;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updated_at = updatedAt;
    }

    public long getISBN() {
        return ISBN;
    }

    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }
}
