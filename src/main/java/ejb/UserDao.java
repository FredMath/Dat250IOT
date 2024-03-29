package ejb;

import entities.Device;
import entities.User;

import javax.ejb.Stateless;
import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

@Stateless
public class UserDao {

    @PersistenceContext(unitName = "test")
    private EntityManager em;

    public void persist(User user) throws NamingException, JMSException {
        em.persist(user);
    }

    public User getUserByUsername(String username){
        User user = em.find(User.class, username);
        return user;
    }
}
