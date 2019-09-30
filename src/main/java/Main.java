import entities.Device;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {



    public static void main(final String[] args) throws Exception {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("test"); //name of persistence unit here
        EntityManager entityManager = factory.createEntityManager();
        Device device = new Device();

    }
}
