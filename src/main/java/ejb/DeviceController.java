package ejb;

import entities.Device;

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

    private Device device;

    public List<Device> getDevices() {
        List<Device> reverseDeviceList = new ArrayList<Device>();
        reverseDeviceList.addAll(this.deviceDao.getAllDevices());
        Collections.reverse(reverseDeviceList);
        return reverseDeviceList;

    }

    public void saveDevice(Device device) throws NamingException, JMSException {
        this.deviceDao.persist(this.device);
    }

    public Device getTweet() {
        if (this.device == null) {
            device = new Device();
        }
        return device;

    }

}
