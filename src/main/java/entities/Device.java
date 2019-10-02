package entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name="Device")
@XmlRootElement
@NamedQuery(name="Device.findAll", query="SELECT d FROM Device d")
public class Device implements Serializable {
private static final long serialVersionUID = 1;

    @TableGenerator(
            name = "yourTableGenerator",
            allocationSize = 1,
            initialValue = 1)
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE,generator="yourTableGenerator")
    private int id;

    private String deviceName;

    private String pictureURL;

    private String deviceURL;

    private boolean power;

    private boolean status;

    private int numberOfSubscriptions;

    public static final String FIND_ALL = "Device.findAll";


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }


    public String getDeviceURL() {
        return deviceURL;
    }

    public void setDeviceURL(String deviceURL) {
        this.deviceURL = deviceURL;
    }

    public boolean isPower() {
        return power;
    }

    public void setPower(boolean power) {
        this.power = power;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getNumberOfSubscriptions() {
        return numberOfSubscriptions;
    }

    public void setNumberOfSubscriptions(int numberOfSubscriptions) {
        this.numberOfSubscriptions = numberOfSubscriptions;
    }

    @Override
    public String toString() {
        return "IOTDevice{" +
                "id=" + id +
                ", deviceName='" + deviceName + '\'' +
                ", pictureURL='" + pictureURL + '\'' +
                ", deviceURL='" + deviceURL + '\'' +
                ", power=" + power +
                ", status=" + status +
                ", numberOfSubscriptions=" + numberOfSubscriptions +
                '}';
    }

    @ManyToOne(optional = false)
    private entities.User User;

    public entities.User getUser() {
        return User;
    }

    public void setUser(entities.User user) {
        User = user;
    }

    @OneToMany(mappedBy = "Device")
    private Collection<entities.Feedback> Feedback;

    public Collection<entities.Feedback> getFeedback() {
        return Feedback;
    }

    public void setFeedback(Collection<entities.Feedback> feedback) {
        Feedback = feedback;
    }

    @OneToMany(mappedBy = "Device")
    private Collection<Subscription> Subcriptions;

    public Collection<Subscription> getSubcriptions() {
        return Subcriptions;
    }

    public void setSubcriptions(Collection<Subscription> subcriptions) {
        Subcriptions = subcriptions;
    }

    @OneToMany(mappedBy = "Device")
    private Collection<Tag> Tags;

    public Collection<Tag> getTags() {
        return Tags;
    }

    public void setTags(Collection<Tag> tags) {
        Tags = tags;
    }
}
