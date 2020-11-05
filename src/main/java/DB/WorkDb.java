package DB;

import DB.DbDAO;

import java.sql.*;
import java.util.ArrayList;

public class WorkDb {
    private final Connection connection;

    public WorkDb() {
        this.connection = create();
    }

    public void addBook(String[] m) throws SQLException {
        DbDAO dbDAO = new DbDAO(connection);
        dbDAO.addBook(m);
    }

    public void deleteBook(String m, String n) throws SQLException {
        DbDAO dbDAO = new DbDAO(connection);
        dbDAO.deleteBook(m, n);

    }

    public ArrayList<String> findBook(String m, String n) throws SQLException {
        DbDAO dbDAO = new DbDAO(connection);
        return dbDAO.findBook(m, n);
    }

    public ArrayList<String> seeBook() throws SQLException {
        DbDAO dbDAO = new DbDAO(connection);
        return dbDAO.seeBook();
    }

    public static Connection create()  {
        try {
            String username = "root";
            String password = "1234";
            String connectionurl = "jdbc:mysql://localhost:3306/mydb";
            Connection connection = DriverManager.getConnection(connectionurl, username, password);
            return connection;
    } catch (SQLException e) {
        e.printStackTrace();
    }
        return null;
    }

    public void printConnectInfo() {
        try {
            System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
            System.out.println("Driver: " + connection.getMetaData().getDriverName());
            System.out.println("Autocommit: " + connection.getAutoCommit());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    }

