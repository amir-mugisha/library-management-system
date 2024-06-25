package src.services.dao;

import src.services.Transaction;

import java.util.List;

public interface TransactionDao {
    void addTransaction(Transaction transaction);
    Transaction getTransactionById(int id);
    List<Transaction> getAllTransactions();
    void updateTransaction(Transaction transaction);
    void deleteTransaction(int id);
}
