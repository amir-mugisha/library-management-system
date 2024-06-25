package src.services;

import src.services.dao.BookDaoService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String title;
        String author;
        String publisher;
        int publicationYear;
        int availableCopies;
        long isbn;
        String synopsis;
        Book book;
        BookDaoService BookService;
        try{

            System.out.println("Enter title: ");
            title = sc.nextLine();

            System.out.println("Enter author: ");
            author= sc.nextLine();

            System.out.println("Enter publisher: ");
            publisher = sc.nextLine();

            System.out.println("Enter publication year: ");
            publicationYear = sc.nextInt();

            System.out.println("Enter available copies");
            availableCopies = sc.nextInt();

            System.out.println("Enter isbn: ");
            isbn = sc.nextLong();
            sc.nextLine();

            System.out.println("Enter synopsis");
            synopsis = sc.nextLine();

            book = new Book(title,author,publisher,publicationYear,availableCopies,isbn,synopsis);
            BookService = new BookDaoService();
            BookService.addBook(book);
            System.out.println("Book added successfully");

            sc.close();
        }catch(Exception e){
            throw new RuntimeException("Server error: " + e.getMessage());
        }

    }
}


