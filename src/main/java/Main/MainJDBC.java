package Main;

import Main.dao.jdbc.JdbcContactDao;
import Main.domain.Contact;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class MainJDBC {
    private static Scanner mSanner = new Scanner(System.in);
    public static void main(String... args) throws ParseException {
        System.out.println("Hello, you can add param to database sql");
        ApplicationContext context = new ClassPathXmlApplicationContext("config-data-source.xml");
        JdbcContactDao contactDao = (JdbcContactDao) context.getBean("contactDao");
        help();
        int choise = 1;
        while (choise!=0) {
            choise = mSanner.nextInt();
            switch (choise) {
                case 0: {
                    System.out.println("GoodBue!");
                    break;
                }
                case 1: {
                    contactDao.insert(getContactFromInputStream(mSanner));
                    break;
                }
                case 2: {
                    List<Contact> contactList = contactDao.findAll();
                    for (Contact contact : contactList) {
                        System.out.println(contact);
                    }
                    break;
                }
                case 3: {
                    System.out.print("ID: ");
                    int id = mSanner.nextInt();
                    contactDao.delete(id);
                    System.out.println("Ok, success!");
                    break;
                }
                case 4: {
                    System.out.print("ID: ");
                    int id = mSanner.nextInt();
                    List<Contact> contacts = contactDao.findContactsById(id);
                    for (Contact contact: contacts) {
                        System.out.println(contact);
                    }
                    break;
                }
                default: {
                    System.out.println("Sorry. I'm not uderstand you!");
                    help();
                    break;
                }
            }
        }

    }
    public static void help() {
        System.out.println("What your choise?");
        System.out.println("0) Exit");
        System.out.println("1) add Contact");
        System.out.println("2) show all Contacts");
        System.out.println("3) delete contact by ID");
        System.out.println("4) show contact by ID");
    }
    public static Contact getContactFromInputStream(Scanner scanner) throws ParseException {

        System.out.print("Name:\t");
        String name = scanner.next();
        System.out.print("Surname:\t");
        String surname= scanner.next();
        System.out.print("Birth Day(dd.mm.yyyy):\t");
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date(format.parse(scanner.next()).getTime());
        return new Contact(name, surname, date);
    }
}
