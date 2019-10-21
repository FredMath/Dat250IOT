package entities;

import Utils.Status;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;

@Entity
@Table(name = "subscriptions")
@XmlRootElement
public class Subscription {
    @TableGenerator(
            name = "yourTableGenerator",
            allocationSize = 1,
            initialValue = 1)
    @Id
    @GeneratedValue(strategy= GenerationType.TABLE,generator="yourTableGenerator")
    private int id;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @ManyToOne(optional = false)
    private entities.Device Device;

    public entities.Device getDevice() {
        return Device;
    }

    public void setDevice(entities.Device device) {
        Device = device;
    }

    @ManyToOne(optional = false)
    private entities.User User;

    public entities.User getUser() {return User;}

    public void setUser(entities.User user) {User = user;}

}
