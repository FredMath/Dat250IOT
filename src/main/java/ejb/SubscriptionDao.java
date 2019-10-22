package ejb;

import Utils.Status;
import entities.Device;
import entities.Subscription;
import entities.User;

import javax.ejb.Stateless;
import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

@Stateless
public class SubscriptionDao {

    @PersistenceUnit(unitName = "test")
    private EntityManager em;

    public void persist(Subscription subscription) throws NamingException, JMSException {
        em.persist(subscription);
    }

    @SuppressWarnings("unchecked") //tror persist funker for subscribe?
    public void Subscribe(Device device, User user) {
       // Query query = em.createQuery("SELECT ")
    }

    @SuppressWarnings("unchecked")
    public void Unsubscribe(Device device, User user) {
        Query query = em.createQuery("DELETE FROM Subscriptions t WHERE t.device.id LIKE ?1 AND t.user_username LIKE ?2")
                .setParameter(1, device.getId()).setParameter(2, user.getUsername());
    }

    public void changeStatus(Subscription subscription, Status newStatus){
        Query query = em.createQuery("UPDATE Subscriptions t SET t.status = ?1 WHERE t.id LIKE ?2")
                .setParameter(1, newStatus).setParameter(2, subscription.getId());
    }

}
