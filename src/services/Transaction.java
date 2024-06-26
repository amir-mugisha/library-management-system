package src.services;

import java.time.LocalDate;

public class Transaction {
    private int id;
    private int book_id;
    private int user_id;
    private String status;
    private LocalDate dueDate;
    private LocalDate created_at;
    private LocalDate updated_at;

    public Transaction() {
    }

    public Transaction(int id, int book_id, int patron_id, String status, LocalDate dueDate) {
        this.id = id;
        this.book_id = book_id;
        this.user_id = patron_id;
        this.status = status;
        this.dueDate = dueDate;
        this.created_at = LocalDate.now();
        this.updated_at = LocalDate.now();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(LocalDate updated_at) {
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public int getBook_id() {
        return book_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public LocalDate getUpdated_at() {
        return updated_at;
    }
}
