package ejb;

import entities.User;

import javax.ejb.Stateless;
import javax.persistence.*;

@Stateless
public class LoginDAO {

    @PersistenceContext(unitName = "test")
    private EntityManager em;

    public boolean validate(String uname, String pwd) {
        Query query =  em.createNativeQuery("SELECT u FROM users u WHERE u.password = ?1 and u.username = ?2")
                .setParameter(1, pwd).setParameter(2, uname);
        User user = (User) query.getSingleResult();
        if(user != null) {
            return true;
        } else {
            System.out.println("Login error, wrong username or password");
            return false;
        }

    }
}
