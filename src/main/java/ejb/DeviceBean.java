package ejb;

import entities.Device;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;

@ManagedBean(name = "devices")
@RequestScoped
public class DeviceBean {
    private List<Device> devices;

    public DeviceBean() {
    }

    public void setDevices(List<Device> deviceList) {
        this.devices = deviceList;
    }

    public List<Device> getDevices() {
        return devices;
    }
}
