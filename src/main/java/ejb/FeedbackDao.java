package ejb;

import entities.Device;
import entities.Feedback;
import entities.Tag;
import entities.User;

import javax.ejb.Stateless;
import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class FeedbackDao {

    @PersistenceUnit(unitName = "test")
    private EntityManager em;

    public void persist(Feedback feedback) throws NamingException, JMSException {
        em.persist(feedback);
    }

    public List<Feedback> getFeedbackForDevice(Device device){
        Query query = em.createQuery("SELECT t FROM Feedback t WHERE t.device_id LIKE :deviceid")
                .setParameter("deviceid", device.getId());
        List<Feedback> feedbackList = new ArrayList<Feedback>();
        feedbackList = query.getResultList();

        return feedbackList;
    }
}
