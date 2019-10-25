package Rest;

import Utils.Status;
import ejb.DeviceDao;
import ejb.SubscriptionDao;
import ejb.UserDao;
import entities.Device;
import entities.Devices;
import entities.Subscription;
import entities.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Path("/devices")
//@Produces({MediaType.APPLICATION_XML})
public class RestService {

//    @PersistenceUnit(unitName = "test")
//    private EntityManager em;

    @EJB
    private DeviceDao deviceDao;
    @EJB
    private UserDao userDao;
    @EJB
    private SubscriptionDao subscriptionDao;

    private UriInfo uriInfo;
    private Logger logger = Logger.getLogger(getClass().getName());


    @GET
    //  @Produces(MediaType.APPLICATION_JSON)
    public List<Device> getDevices() {
        //TypedQuery<Device> query = em.createNamedQuery(Device.FIND_ALL, Device.class);
        //Devices devices = new Devices(query.getResultList());
        //return Response.ok(deviceDao.getAllDevices()).build();
        List<Device> devices = new ArrayList<Device>();
        devices.addAll(this.deviceDao.getAllDevices());
        // return Response.ok(devices).build();
        return devices;
    }

    @GET
    //  @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getDevice(@PathParam("id") String id) {
        int deviceId = Integer.parseInt(id);
        Device device = deviceDao.getDevice(deviceId);
        if(device == null) {
            throw new NotFoundException();
        }
        return Response.ok(device).build();
    }

    /*@GET
    @Path("/{id}/registrations")
    public Response getDeviceRegistrations(@PathParam("id") String id) {
        int deviceId = Integer.parseInt(id);
        Device device = em.find(Device.class, deviceId);
        if(device.getSubcriptions() == null) {
            throw new NotFoundException();
        }
        return Response.ok(device.getSubcriptions()).build();
    }

    @GET
    @Path("/{id}/registrations/{rid}")
    public Response getDeviceRegistration(@PathParam("id") String id, @PathParam("rid") String regId) {
        int deviceId = Integer.parseInt(id);
        logger.info(id);
        int registrationId = Integer.parseInt(regId);
        Device device = em.find(Device.class, deviceId);
        Subscription subscription = null;
        for(Subscription sub : device.getSubcriptions()) {
            if (sub.getId() == registrationId) {
                subscription = sub;
            }
        }
        if(subscription == null) {
            throw new NotFoundException();
        }
        return Response.ok(subscription).build();
    }

*/

    @POST
    @Path("/{id}")
    @Transactional
    public Response setDevice(@PathParam("id") String id, @HeaderParam("name") String name) throws NamingException, JMSException {
        int deviceId = Integer.parseInt(id);
//        Device device = em.find(Device.class, deviceId);
//
//        Subscription subscription = new Subscription();
//        Date date = new Date(System.currentTimeMillis());
//        subscription.setDevice(device);
//        subscription.setDate(date);
//        subscription.setStatus(Status.PENDING);
//        em.persist(subscription);
//        URI subURI = uriInfo.getAbsolutePathBuilder().path(Integer.toString(subscription.getId())).build();

        Device device = deviceDao.getDevice(deviceId);
        User user = userDao.getUserByUsername(name);

        Subscription subscription = new Subscription();
        Date date = new Date(System.currentTimeMillis());
        subscription.setDevice(device);
        subscription.setDate(date);
        subscription.setStatus(Status.PENDING);
        subscription.setUser(user);
        subscriptionDao.persist(subscription);
        //     URI subURI = uriInfo.getAbsolutePathBuilder().path(Integer.toString(subscription.getId())).build();
        //       URI uri = uriInfo.getAbsolutePath();
        return Response.ok().build();
        //       return Response.created(uri).build();
        //    return Response.created(subURI).build();
    }



}
