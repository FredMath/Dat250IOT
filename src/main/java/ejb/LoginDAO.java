package ejb;

import entities.User;

import javax.ejb.Stateless;
import javax.persistence.*;

@Stateless
public class LoginDAO {

    @PersistenceContext(unitName = "test")
    private EntityManager em;

    public boolean validate(String uname, String pwd) {
        Query query = em.createQuery("SELECT u FROM users u WHERE u.password = passord and u.username = malas");

        // TypedQuery<User> query = em.createQuery("SELECT u FROM users u WHERE u.password = ?1 and u.username = ?2")
       // em.createQuery("select user from users where user.password = " + pwd + " and user.username = " + uname, User.class);
                //.setParameter(1, pwd).setParameter(2, uname);
        User user = (User) query.getSingleResult();
        if(user != null) {
            return true;
        } else {
            System.out.println("Login error, wrong username or password");
            return false;
        }

    }
}
