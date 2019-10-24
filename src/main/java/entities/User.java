package entities;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Collection;

@Entity
@Table(name="Users")
@XmlRootElement
public class User {
    @TableGenerator(
            name = "yourTableGenerator",
            allocationSize = 1,
            initialValue = 1)
    @Id
    private String Username;

    private String password;

    private String firstName;

    private String lastName;

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @JsonbTransient
    @OneToMany(mappedBy = "User", fetch = FetchType.LAZY)
    private Collection<Device> Devices;

    public Collection<Device> getDevices() {
        return Devices;
    }

    public void setDevices(Collection<Device> devices) {
        Devices = devices;
    }

    @JsonbTransient
    @OneToMany(mappedBy = "User", fetch = FetchType.EAGER)
    private Collection<Subscription> Subscription;

    public Collection<Subscription> getSubscriptions(){return Subscription;}

    public void setSubscription(Collection<Subscription> subscription) {Subscription = subscription;}
}
