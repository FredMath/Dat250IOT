package ejb;

import Utils.Status;
import entities.Device;
import entities.Subscription;
import entities.User;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.*;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

@Stateless
public class SubscriptionDao {

    @PersistenceContext(unitName = "test")
    private EntityManager em;

    @Inject
    @JMSConnectionFactory("jms/dat250/ConnectionFactory")
    @JMSSessionMode(JMSContext.AUTO_ACKNOWLEDGE)
    private JMSContext context;

    @Resource(lookup = "jms/dat250/Topic")
    private Topic topic;

    public void persist(Subscription subscription) throws NamingException, JMSException {
        em.persist(subscription);
        context.createProducer().setProperty("topicSubscription", "dweet").send(topic, subscription);
    }

    @SuppressWarnings("unchecked") //tror persist funker for subscribe?
    public void Subscribe(Device device, User user) {
        //Query query = em.createQuery("UPDATE ")
    }

    @SuppressWarnings("unchecked")
    public void Unsubscribe(Device device, User user) {
        Query query = em.createQuery("DELETE FROM Subscription t WHERE t.Device.id LIKE ?1 AND t.User.Username LIKE ?2")
                .setParameter(1, device.getId()).setParameter(2, user.getUsername());
    }

    public void changeStatus(Subscription subscription, int status){
        Query query = em.createQuery("UPDATE Subscription t SET t.status = ?1 WHERE t.id LIKE ?2")
                .setParameter(1, status).setParameter(2, subscription.getId());
    }
}
