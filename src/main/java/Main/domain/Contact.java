package Main.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class Contact implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDate;

    public List<ContactTelDetail> getContactTelDetails() {
        return contactTelDetails;
    }

    public void setContactTelDetails(List<ContactTelDetail> contactTelDetails) {
        this.contactTelDetails = contactTelDetails;
    }

    private List<ContactTelDetail> contactTelDetails;

    public Contact(){}

    public Contact(Long id, String firstName, String lastName, Date birthDate) {
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.id = id;
        this.lastName = lastName;
    }

    public Contact(String firstName, String lastName, Date birthDate) {
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.lastName = lastName;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Contact = [ id = "+ id +", firstName = " + firstName + ", lastName = "
                + lastName + ", birth_date = " + birthDate + "]";

    }

}
