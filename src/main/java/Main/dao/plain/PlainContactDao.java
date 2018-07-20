package Main.dao.plain;

import Main.dao.ContactDao;
import Main.domain.Contact;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlainContactDao implements ContactDao {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    private Connection getConntection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/database_contact",
                "XXXXXXXXXX", "XXXXXXXXXX");

    }
    private void closeConnection(Connection connection) {
        if (connection == null) return;
        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public List<Contact> findAll() {
        List<Contact> result = new ArrayList<Contact>();
        Connection connection = null;
        try {
            connection = getConntection();
            PreparedStatement stateman = connection.prepareStatement("select * from CONTACT");
            ResultSet resultSet = stateman.executeQuery();
            while (resultSet.next()) {
                Contact contact = new Contact(resultSet.getLong("ID"),
                        resultSet.getString("FIRST_NAME"), resultSet.getString("LAST_NAME"), resultSet.getDate("BIRTH_DATE"));
                result.add(contact);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return result;
    }


    public void delete(long contactId) {
        Connection connection = null;
        try {
            connection = getConntection();
            PreparedStatement stateman = connection.prepareStatement("DELETE FROM CONTACT WHERE ID=?");
            stateman.setLong(1, 3);
            stateman.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    public void update(Contact contact) {

    }

    public void insert(Contact contact) {
        Connection connection = null;
        try {
            connection = getConntection();
            PreparedStatement stateman = connection.prepareStatement("INSERT INTO CONTACT (FIRST_NAME, LAST_NAME, BIRTH_DATE) values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stateman.setString(1, contact.getFirstName());
            stateman.setString(2, contact.getLastName());
            stateman.setDate(3, contact.getBirthDate());
            stateman.execute();
            ResultSet generatedKeys = stateman.getGeneratedKeys();
            if (generatedKeys.next()) {
                contact.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }
}
