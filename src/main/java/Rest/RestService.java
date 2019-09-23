package Rest;

import entities.Device;
import entities.Devices;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/devices")
@Stateless
public class RestService {

    private EntityManager em;
    @GET
    public Response getDevices() {
        TypedQuery<Device> query = em.createQuery(Device.FIND_ALL, Device.class);
        Devices devices = new Devices(query.getResultList());
        return Response.ok(devices).build();
    }

    @GET
    @Path("{id}")
    public Response getDevice(@PathParam("id") String id) {
        return null;
    }

    @GET
    @Path("{id}/registrations")
    public Response getDeviceRegistrations(@PathParam("id") String id) {
        return null;
    }

    @GET
    @Path("{id}/registrations/{rid}")
    public Response getDeviceRegistration(@PathParam("id") String id, @PathParam("rid") String regId) {
        return null;
    }

    @POST
    @Path("{id}")
    public Response setDevice(@PathParam("id") String id) {
        return null;
    }



}
