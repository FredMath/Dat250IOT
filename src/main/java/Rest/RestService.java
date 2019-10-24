package Rest;

import Utils.Status;
import ejb.DeviceDao;
import entities.Device;
import entities.Devices;
import entities.Subscription;
import entities.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.sql.Date;
import java.util.logging.Logger;

@Path("/devices")
//@Produces({MediaType.APPLICATION_XML})
public class RestService extends Application {

//    @PersistenceUnit(unitName = "test")
//    private EntityManager em;

    @EJB
    private DeviceDao deviceDao;

    //   private UriInfo uriInfo;
    private Logger logger = Logger.getLogger(getClass().getName());


    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response getDevices() {
        //TypedQuery<Device> query = em.createNamedQuery(Device.FIND_ALL, Device.class);
        //Devices devices = new Devices(query.getResultList());

        return Response.ok(deviceDao.getAllDevices()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Device getDevice(@PathParam("id") String id) {
        int deviceId = Integer.parseInt(id);
        Device device = deviceDao.getDevice(deviceId);
        if(device == null) {
            throw new NotFoundException();
        }
        return device;
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


    @POST
    @Path("/{id}")
    @Consumes({ "application/json", "application/xml" })
    public Response setDevice(@PathParam("id") String id, User user) {
        int deviceId = Integer.parseInt(id);
        Device device = em.find(Device.class, deviceId);

        Subscription subscription = new Subscription();
        Date date = new Date(System.currentTimeMillis());
        subscription.setDevice(device);
        subscription.setDate(date);
        subscription.setStatus(Status.PENDING);
        em.persist(subscription);
        URI subURI = uriInfo.getAbsolutePathBuilder().path(Integer.toString(subscription.getId())).build();

        return Response.created(subURI).build();
    }*/



}
