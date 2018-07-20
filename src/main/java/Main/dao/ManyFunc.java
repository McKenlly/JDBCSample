package Main.dao;

import Main.domain.Contact;

import java.util.List;

public interface ManyFunc {
    public String findFirstNameById(long id);
    public String findLastNameById(long id);
    public List<Contact> findContactsById(long id);
}
