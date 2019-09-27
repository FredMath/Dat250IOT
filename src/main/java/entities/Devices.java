package entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@XmlRootElement
@XmlSeeAlso(Device.class)
public class Devices extends ArrayList<Device> {

    private static final long serialVersionUID = 1L;

    public Devices() {
        super();
    }

    public Devices(Collection<? extends Device> c) {
        super(c);
    }

    @XmlElement(name = "device")
    public List<Device> getDevices() {
        return this;
    }

    public void setDevices(List<Device> devices) { this.addAll(devices); }

}
