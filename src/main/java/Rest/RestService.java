package Rest;

import Helpers.Status;
import entities.Device;
import entities.Devices;
import entities.Subscription;
import entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.sql.Date;
import java.time.LocalDate;
import java.util.zip.DataFormatException;

@Path("/devices")
@Stateless
public class RestService {

    private EntityManager em;
    private UriInfo uriInfo;
    @PersistenceContext(unitName = "test")


    @GET
    @Produces({ "application/json", "application/xml" })
    public Response getDevices() {
        TypedQuery<Device> query = em.createQuery(Device.FIND_ALL, Device.class);
        Devices devices = new Devices(query.getResultList());
        //em.close();
        return Response.ok(devices).build();
    }

    @GET
    @Path("{id}")
    @Produces({ "application/json", "application/xml" })
    public Response getDevice(@PathParam("id") String id) {
        double deviceId = Integer.parseInt(id);
        Device device = em.find(Device.class, deviceId);
        if(device == null) {
            throw new NotFoundException();
        }
//        em.close();
        return Response.ok(device).build();
    }

    @GET
    @Path("{id}/registrations")
    @Produces({ "application/json", "application/xml" })
    public Response getDeviceRegistrations(@PathParam("id") String id) {
        int deviceId = Integer.parseInt(id);
        Device device = em.find(Device.class, deviceId);
        if(device.getSubcriptions() == null) {
            throw new NotFoundException();
        }
        em.close();
        return Response.ok(device.getSubcriptions()).build();
    }

    @GET
    @Path("{id}/registrations/{rid}")
    @Produces({ "application/json", "application/xml" })
    public Response getDeviceRegistration(@PathParam("id") String id, @PathParam("rid") String regId) {
        int deviceId = Integer.parseInt(id);
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
        em.close();
        return Response.ok(subscription).build();
    }


    @POST
    @Path("{id}")
    @Consumes({ "application/json", "application/xml" })
    @Produces({ "application/json", "application/xml" })
    public Response setDevice(@PathParam("id") String id, User user) {
        int deviceId = Integer.parseInt(id);
        Device device = em.find(Device.class, deviceId);
        Subscription subscription = new Subscription();
        Date date = new Date(System.currentTimeMillis());
        subscription.setDevice(device);
        subscription.setDate(date);
        subscription.setStatus(Status.PENDING);
        em.getTransaction().begin();
        em.persist(subscription);
        em.getTransaction().commit();
        em.close();
        URI subURI = uriInfo.getAbsolutePathBuilder().path(Integer.toString(subscription.getId())).build();

        return Response.created(subURI).build();
    }



}
