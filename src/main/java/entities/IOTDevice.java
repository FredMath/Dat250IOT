package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="IOTDevices")
public class IOTDevice implements Serializable {
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
}
