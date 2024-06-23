package src.controllers.dao;

import src.controllers.Transaction;

import java.util.List;

public interface TransactionDao {
    void addTransaction(Transaction transaction);
    Transaction getTransactionById(int id);
    List<Transaction> getAllTransactions();
    void updateTransaction(Transaction transaction);
    void deleteTransaction(int id);
}
