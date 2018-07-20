package Main;

import Main.dao.ContactDao;
import Main.dao.plain.PlainContactDao;
import Main.domain.Contact;

import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class MainPlain {
    private static ContactDao contactDao = new PlainContactDao();
    public static void main(String... args) {
        System.out.println("Listing initial contact data:");
        listAllContacts();

        System.out.println("listing contact data after new contact createt:");
        Contact contact = new Contact("Jacky", "Chan", new Date(
                (new GregorianCalendar(2001, 10, 1)).getTimeInMillis()));
        contactDao.insert(contact);
        System.out.println("Listing contact data after new contact created:");
        listAllContacts();;
        System.out.println();
        System.out.println("Deleting the previos created contact");
        contactDao.delete(contact.getId());
        System.out.println("Listing contact data affter new contact");
        listAllContacts();
    }
    private static void listAllContacts() {
        List<Contact> contacts = contactDao.findAll();
        for (Contact contact:contacts) {
            System.out.println(contact);
        }
    }
}
