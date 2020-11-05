package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbDAO {

    private final Connection connection;

    public DbDAO(Connection connection) {
        this.connection = connection;
    }

    public  void  addBook(String[] m) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into spisok_knig (Nazva_predmeta,Avtor,Klas) values (?,?,?)  ");
        preparedStatement.setString(1, String.valueOf(m[0]));
        preparedStatement.setString(2, String.valueOf(m[1]));
        preparedStatement.setString(3, String.valueOf(m[2]));
        preparedStatement.executeUpdate();
        preparedStatement.close();

    }

    public void deleteBook(String m, String n) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("delete from spisok_knig where " + n + "= ? ");
        preparedStatement.setString(1, m);
        preparedStatement.executeUpdate();
        preparedStatement.close();

    }

    public ArrayList<String> seeBook() throws SQLException {
        ArrayList<String> arrayList = new ArrayList();
        PreparedStatement preparedStatement = connection.prepareStatement("select* from spisok_knig ");
        ResultSet rs = preparedStatement.executeQuery();
       while (rs.next()) {
            arrayList.add(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " "
                    + rs.getString(4));
        }
        rs.close();
       preparedStatement.close();
       return arrayList;
    }

    public ArrayList<String> findBook(String m, String n) throws SQLException {
        ArrayList<String> arrayList = new ArrayList();
        PreparedStatement preparedStatement = connection.prepareStatement("select* from spisok_knig where " + n + "= ? ");
        preparedStatement.setString(1, m);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            arrayList.add(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " "
                    + rs.getString(4));
        }
        rs.close();
        preparedStatement.close();
        return arrayList;

    }

}
