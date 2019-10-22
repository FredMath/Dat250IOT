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
import java.util.ArrayList;
import java.util.List;

@Stateless
public class DeviceDao {

    @PersistenceUnit(unitName = "test")
    private EntityManager em;


    public void persist(Device device) throws NamingException, JMSException {
        em.persist(device);
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
        Query query = em.createQuery("SELECT t FROM Device t WHERE t.user_username LIKE :userName")
                .setParameter("userName", user.getUsername());
        List<Device> devices = new ArrayList<Device>();
        devices = query.getResultList();
        return devices;
    }

    public void deleteDevice(Device device) {
        Query query = em.createQuery("DELETE FROM Device t WHERE t.device.id LIKE ?1")
                .setParameter(1, device.getId());
    }
}
