module library.management.system.src {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;


    exports views;
    exports services;
    exports services.dao;
    exports services.utils;
}