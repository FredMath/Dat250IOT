package ejb;

import entities.Device;
import entities.Subscription;
import entities.User;

import javax.ejb.Stateless;
import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;

@Stateless
public class SubscriptionDao {

    @PersistenceUnit(unitName = "test")
    private EntityManager em;

    public void persist(Subscription subscription) throws NamingException, JMSException {
        em.persist(subscription);
    }

    @SuppressWarnings("unchecked")
    public void Subscribe(Device device, User user) {

    }

    @SuppressWarnings("unchecked")
    public void Unsubscribe(Device device, User user) {

    }

}
