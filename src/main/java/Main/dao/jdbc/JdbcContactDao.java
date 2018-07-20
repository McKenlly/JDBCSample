package Main.dao.jdbc;


import Main.dao.ContactDao;
import Main.dao.ManyFunc;
import Main.domain.Contact;
import Main.domain.ContactTelDetail;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class JdbcContactDao implements ContactDao, ManyFunc {

    private SimpleJdbcTemplate jdbcTemplate;

    private static final String SQL_INSERT_CONTACT =
            "INSERT INTO CONTACT (FIRST_NAME, LAST_NAME, BIRTH_DATE) values (?, ?, ?)";
    private static final String SQL_SELECT_CONTACT =
            "SELECT ID, FIRST_NAME, LAST_NAME, BIRTH_DATE FROM CONTACT";
    private static final String SQL_SELECT_CONTACT_BY_ID =
            "SELECT ID, FIRST_NAME, LAST_NAME, BIRTH_DATE FROM CONTACT where ID=?";
    private static final String SQL_SELECT_CONTACT_BY_FIRST_NAME =
            "SELECT FIRST_NAME FROM CONTACT where ID=?";
    private static final String SQL_SELECT_ALL_CONTACT =
            "SELECT t.* FROM database_contact.CONTACT t";
    private static final String SQL_DELETE_CONTACT_BY_ID =
            "DELETE FROM CONTACT WHERE ID=?";


    public void setJdbcTemplate(SimpleJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public SimpleJdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public List<Contact> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_CONTACT, new ContactMapper());
    }

    public List<Contact> findContactsById(long id) {
        return getJdbcTemplate().query(SQL_SELECT_CONTACT_BY_ID,
                new ParameterizedRowMapper<Contact>() {
                    public Contact mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        long id =  rs.getInt(1);
                        String name = rs.getString(2);
                        String surname = rs.getString(3);
                        Date date = rs.getDate(4);
                        return new Contact(id, name, surname, date);
                    }
                }, id);
    }

    public String findFirstNameById(long id) {
        return getJdbcTemplate().queryForObject(
                SQL_SELECT_CONTACT_BY_ID,
                new ParameterizedRowMapper<String>() {
                    public String mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        return rs.getString(1);
                    }
                }, id);
    }

    public String findLastNameById(long id) {
        return getJdbcTemplate().queryForObject(
                SQL_SELECT_CONTACT_BY_ID,
                new ParameterizedRowMapper<String>() {
                    public String mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        return rs.getString(2);
                    }
                }, id);
    }


    public void insert(Contact contact) {
        jdbcTemplate.update(SQL_INSERT_CONTACT,
                new Object[] {contact.getFirstName(),
                contact.getLastName(),
                contact.getBirthDate()});

    }

    public void update(Contact contact) {
        // TODO Auto-generated method stub

    }

    public void delete(long contactId) {
        jdbcTemplate.update(SQL_DELETE_CONTACT_BY_ID, contactId);
    }




    private static final class ContactMapper implements RowMapper<Contact> {

        public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {

            Contact contact = new Contact();
            contact.setId(rs.getLong("ID"));
            contact.setFirstName(rs.getString("FIRST_NAME"));
            contact.setLastName(rs.getString("LAST_NAME"));
            contact.setBirthDate(rs.getDate("BIRTH_DATE"));
            return contact;
        }

    }

}
