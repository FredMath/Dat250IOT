import entities.Device;
import entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {



    public static void main(final String[] args) throws Exception {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("test"); //name of persistence unit here
        EntityManager entityManager = factory.createEntityManager();
        User user = new User();
        user.setDevices(null);
        user.setFirstName("Malik");
        user.setLastName("Aasen");
        user.setPassword("passord");
        user.setUsername("malas");

        //entityManager.getTransaction().begin();
        //entityManager.persist(user);
        //entityManager.getTransaction().commit();
    }
}
