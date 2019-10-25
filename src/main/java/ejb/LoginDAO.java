package ejb;

import entities.User;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.faces.FacesException;
import javax.persistence.*;

@Stateless
public class LoginDAO {

    @PersistenceContext(unitName = "test")
    private EntityManager em;

    public boolean validate(String uname, String pwd) {
        // TypedQuery<User> query = em.createQuery("SELECT u FROM users u WHERE u.password = passord and u.username = malas", User.class);
        User user = null;
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.password = ?1 and u.Username = ?2", User.class)
                .setParameter(1, pwd).setParameter(2, uname);
        try {
            user = query.getSingleResult();
        } catch (EJBException e) {return false;}
        catch (NoResultException n) {
            return false;
        }
        if(user != null) {
            return true;
        } else {
            System.out.println("Login error, wrong username or password");
            return false;
        }

    }
}
