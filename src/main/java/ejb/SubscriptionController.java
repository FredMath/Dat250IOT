package ejb;

import Utils.SessionUtils;
import Utils.Status;
import entities.Device;
import entities.Subscription;
import entities.User;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.jms.JMSException;
import javax.naming.NamingException;
import java.io.Serializable;

@Named(value = "subscriptionController")
@RequestScoped
public class SubscriptionController implements Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private DeviceDao deviceDao;
    @EJB
    private UserDao userDao;
    @EJB
    private TagsDao tagsDao;
    @EJB
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
        int status = 0;

        if (newStatus == Status.ACCEPTED) {
            status = 1;
        } else if (newStatus == Status.DENIED) {
            status = 2;
        }
        subscriptionDao.changeStatus(subscription, status);
    }
}
