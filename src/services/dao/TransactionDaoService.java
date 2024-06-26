package src.services.dao;

import src.services.Book;
import src.services.Transaction;
import src.services.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TransactionDaoService implements TransactionDao{
    private static Connection conn;

    public TransactionDaoService() {
        conn = DBUtil.getConnection();
    }

    @Override
    public void addTransaction(Transaction transaction) {

    }

    @Override
    public Transaction getTransactionById(int id) {
        return null;
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

    }

    @Override
    public void deleteTransaction(int id) {

    }
}
