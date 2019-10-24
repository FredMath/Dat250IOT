package ejb;

import entities.Device;
import entities.Tag;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class TagsDao {

    @PersistenceContext(unitName = "test")
    private EntityManager em;

    @SuppressWarnings("unchecked")
    public List<Device> getDevicesByTags(String tagString) {
        Query query = em.createQuery("SELECT t.Device FROM Tag t WHERE t.name LIKE :tagName")
                .setParameter("tagName", tagString);
        List<Device> device_id = new ArrayList<Device>();
        device_id = query.getResultList();

//        List<Device> devices = new ArrayList<Device>();
//        for(int i = 0; i < device_id.size(); i++){
//            Device d = em.find(Device.class, device_id.get(i));
//            devices.add(d);
//        }
//
//        return devices;
        return device_id;
    }
}
