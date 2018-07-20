package Main.dao;

import Main.domain.Contact;

import java.util.List;

public interface ContactDao {
    public List<Contact> findAll();
    public void insert(Contact contact);
    public void update(Contact contact);
    public void delete(long contactId);
}
