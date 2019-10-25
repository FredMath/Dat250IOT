package ejb;

import Utils.SessionUtils;
import entities.Device;
import entities.Feedback;
import entities.Tag;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
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

    private List<Device> deviceList;

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public List<Device> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<Device> deviceList) {
        this.deviceList = deviceList;
    }

    public DeviceController() {
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
        reverseDeviceList.addAll(this.deviceDao.getUsersDevices(this.userDao.getUserByUsername(username)));
        return reverseDeviceList;
    }

    public void saveDevice(Device device) throws NamingException, JMSException {
        this.deviceDao.persist(device);
    }

    public List<Device> getDevicesByTags() {
        List<Device> reverseDeviceList = new ArrayList<Device>();
        reverseDeviceList.addAll(this.tagsDao.getDevicesByTags(this.tag));
        System.out.print(reverseDeviceList.size());
        System.out.print(this.tag);
        this.deviceList = reverseDeviceList;
        return reverseDeviceList;
    }

    public List<Feedback> getFeedbackForDevice(Device device) {
        List<Feedback> reverseDeviceList = new ArrayList<Feedback>();
        reverseDeviceList.addAll(this.feedbackDao.getFeedbackForDevice(device));

        return reverseDeviceList;
    }

    public void deleteDevice(Device device){
        this.deviceDao.deleteDevice(device);
    }

    public void changePower(Device device, boolean power){
        this.deviceDao.changePower(device, power);
    }
}
