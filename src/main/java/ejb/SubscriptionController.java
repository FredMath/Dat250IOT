package ejb;

import Utils.SessionUtils;
import Utils.Status;
import entities.Device;
import entities.Subscription;
import entities.User;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.jms.JMSException;
import javax.naming.NamingException;
import java.io.Serializable;

@Named(value = "subscriptionController")
@SessionScoped
public class SubscriptionController implements Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private DeviceDao deviceDao;
    private UserDao userDao;
    private TagsDao tagsDao;
    private SubscriptionDao subscriptionDao;
    private Device device;

    public void Subscribe(Subscription subscription) throws NamingException, JMSException {
        this.subscriptionDao.persist(subscription);
    }

    public void Unsubscribe(Device device){
        String username = SessionUtils.getUserName();
        User user = userDao.getUserByUsername(username);
        subscriptionDao.Unsubscribe(device, user);
    }

    public void changeStatus(Subscription subscription, Status newStatus){
        subscriptionDao.changeStatus(subscription, newStatus);
    }
}
