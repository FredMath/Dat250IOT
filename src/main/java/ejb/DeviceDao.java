package ejb;

import entities.Device;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSSessionMode;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class DeviceDao {

    @PersistenceContext(unitName = "test")
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

}
