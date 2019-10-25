package ejb;

import entities.Device;
import entities.Tag;
import entities.User;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSSessionMode;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class DeviceDao {

    @PersistenceContext(unitName = "test")
    private EntityManager em;


    public void persist(Device device) throws NamingException, JMSException {
        em.persist(device);
    }

    public Device getDevice(int id) {
        Device device = em.find(Device.class, id);
        if (device == null)
            throw new NotFoundException();
        return device;
    }

    @SuppressWarnings("unchecked")
    public List<Device> getAllDevices() {
        Query query = em.createQuery("SELECT t FROM Device t");
        List<Device> devices = new ArrayList<Device>();
        devices = query.getResultList();
        return devices;
    }

    @SuppressWarnings("unchecked")
    public List<Device> getUsersDevices(User user) {
        Query query = em.createQuery("SELECT t FROM Device t WHERE t.User.Username LIKE :userName")
                .setParameter("userName", user.getUsername());
        List<Device> devices = new ArrayList<Device>();
        devices = query.getResultList();
        return devices;
    }

    public void deleteDevice(Device device) {
        Query query = em.createQuery("DELETE FROM Device t WHERE t.id LIKE ?1")
                .setParameter(1, device.getId());
    }

    public void changePower(int deviceid, boolean newPower){
        Query query = em.createQuery("UPDATE Device t SET t.power = ?1 WHERE t.id LIKE ?2")
                .setParameter(1, newPower).setParameter(2, deviceid);
        query.executeUpdate();
    }
}
