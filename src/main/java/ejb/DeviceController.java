package ejb;

import Utils.SessionUtils;
import entities.Device;
import entities.Feedback;

import javax.ejb.EJB;
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
    private UserDao userDao;
    private TagsDao tagsDao;
    private FeedbackDao feedbackDao;

    public List<Device> getDevices() {
        List<Device> reverseDeviceList = new ArrayList<Device>();
        reverseDeviceList.addAll(this.deviceDao.getAllDevices());
        Collections.reverse(reverseDeviceList);
        return reverseDeviceList;
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

    public List<Device> getDevicesByTags(String tag) {
        List<Device> reverseDeviceList = new ArrayList<Device>();
        reverseDeviceList.addAll(this.tagsDao.getDevicesByTags(tag));
        return reverseDeviceList;
    }

    public List<Feedback> getFeedbackForDevice(Device device) {
        List<Feedback> reverseDeviceList = new ArrayList<Feedback>();
        reverseDeviceList.addAll(this.feedbackDao.getFeedbackForDevice(device));
        return reverseDeviceList;
    }

    public void deleteDevice(Device device){
        deviceDao.deleteDevice(device);
    }

    public void changePower(Device device, boolean power){
        deviceDao.changePower(device, power);
    }
}
