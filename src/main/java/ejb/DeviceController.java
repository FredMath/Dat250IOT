package ejb;

import Utils.SessionUtils;
import entities.Device;
import entities.Feedback;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.jms.JMSException;
import javax.naming.NamingException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Named(value = "deviceController")
@SessionScoped
public class DeviceController implements Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private DeviceDao deviceDao;
    @EJB
    private UserDao userDao;
    @EJB
    private TagsDao tagsDao;
    @EJB
    private FeedbackDao feedbackDao;
    private String tag;
    private String deviceName;
    private boolean power;
    private int deviceId;

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public List<Device> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<Device> deviceList) {
        this.deviceList = deviceList;
    }

    private List<Device> deviceList;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public boolean isPower() {
        return power;
    }

    public void setPower(boolean power) {
        this.power = power;
    }

    public Device getBeanDevice() {
        return beanDevice;
    }

    public void setBeanDevice(Device beanDevice) {
        this.beanDevice = beanDevice;
    }

    private Device beanDevice;

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public List<Device> getDevices() {
        List<Device> reverseDeviceList = new ArrayList<Device>();
        reverseDeviceList.addAll(this.deviceDao.getAllDevices());
        Collections.reverse(reverseDeviceList);
        return reverseDeviceList;
    }

    public Device getDevice(int id) {
        Device device = deviceDao.getDevice(id);
        return device;
    }

    public List<Device> getUsersDevices() {
        List<Device> reverseDeviceList = new ArrayList<Device>();
        String username = SessionUtils.getUserName();
        reverseDeviceList.addAll(this.deviceDao.getUsersDevices(userDao.getUserByUsername(username)));
        return reverseDeviceList;
    }

    public void saveDevice(Device device) throws NamingException, JMSException {
        this.deviceDao.persist(device);
    }

    public List<Device> getDevicesByTags() {
        List<Device> reverseDeviceList = new ArrayList<Device>();
        reverseDeviceList.addAll(this.tagsDao.getDevicesByTags(this.tag));
        this.deviceList = reverseDeviceList;
        return reverseDeviceList;
    }

    public List<Feedback> getFeedbackForDevice(Device device) {
        List<Feedback> reverseDeviceList = new ArrayList<Feedback>();
        reverseDeviceList.addAll(this.feedbackDao.getFeedbackForDevice(device));

        return reverseDeviceList;
    }

    public void printDevices(List<Device> devices) {
        for (int i = 0; i < devices.size(); i++) {

        }
    }

    public void deleteDevice(){
        deviceDao.deleteDevice(beanDevice);
    }

    public void changePower(){
        deviceDao.changePower(this.deviceId, this.power);
    }
}
