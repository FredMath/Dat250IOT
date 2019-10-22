package ejb;

import entities.User;

import javax.ejb.Stateless;
import javax.persistence.*;

@Stateless
public class LoginDAO {

    @PersistenceContext(unitName = "test")
    private EntityManager em;

    public boolean validate(String uname, String pwd) {
        Query query =  em.createNativeQuery("select user from users where password = " + pwd + " and username = " + uname, User.class);
        User user = (User) query.getSingleResult();
        if(user != null) {
            return true;
        } else {
            System.out.println("Login error, wrong username or password");
            return false;
        }

    }
}
