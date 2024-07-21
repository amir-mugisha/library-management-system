package services.dao;

import services.Book;
import services.Transaction;
import services.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TransactionDaoService implements TransactionDao{
    private  Connection conn;

    public TransactionDaoService(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        try {
            String sql = "INSERT INTO Transaction (book_id, user_id, status, dueDate, created_at) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, transaction.getBook_id());
            ps.setInt(2, transaction.getUser_id());
            ps.setString(3, transaction.getStatus());
            ps.setDate(4, java.sql.Date.valueOf(transaction.getDueDate()));
            ps.setDate(5, java.sql.Date.valueOf(transaction.getCreated_at()));
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Failed to add transaction " + e.getMessage());
        }
    }

    @Override
    public Transaction getTransactionById(int id) {
        Transaction transaction = null;
        try {
            String sql = "SELECT * FROM Transaction WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                transaction = new Transaction();
                transaction.setBook_id(rs.getInt("book_id"));
                transaction.setUser_id(rs.getInt("user_id"));
                transaction.setStatus(rs.getString("status"));
                transaction.setDueDate(rs.getDate("dueDate").toLocalDate());
                transaction.setCreated_at(rs.getDate("created_at").toLocalDate());
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch transaction by ID " + e.getMessage());
        }
        return transaction;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<Transaction>();
        try{
            String sql = "SELECT * FROM Transaction";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Transaction transaction = new Transaction();
                transaction.setBook_id(rs.getInt("book_id"));
                transaction.setUser_id(rs.getInt("user_id"));
                transaction.setStatus(rs.getString("status"));
                transaction.setDueDate(rs.getDate("dueDate").toLocalDate());
                transaction.setCreated_at(rs.getDate("created_at").toLocalDate());

                transactions.add(transaction);
            }
        }
        catch (Exception e){
            throw new RuntimeException("Failed to fetch all books " + e.getMessage());
        }
        return transactions;
    }

    @Override
    public void updateTransaction(Transaction transaction) {
        try {
            String sql = "UPDATE Transaction SET book_id = ?, user_id = ?, status = ?, dueDate = ?, created_at = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, transaction.getBook_id());
            ps.setInt(2, transaction.getUser_id());
            ps.setString(3, transaction.getStatus());
            ps.setDate(4, java.sql.Date.valueOf(transaction.getDueDate()));
            ps.setDate(5, java.sql.Date.valueOf(transaction.getCreated_at()));
            ps.setInt(6, transaction.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Failed to update transaction " + e.getMessage());
        }
    }

    @Override
    public void deleteTransaction(int id) {
        try {
            String sql = "DELETE FROM Transaction WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete transaction " + e.getMessage());
        }
    }
}
