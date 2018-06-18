package webservice;

import database.OrderRepository;
import model.Order;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("order/{orderId}")
public class OrderController {

    @EJB
    private OrderRepository orderRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrder(@PathParam("orderId") String orderId) {
        try {
            int id = Integer.parseInt(orderId);
            Order order = orderRepository.getById(id);
            if (order == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.status(Response.Status.OK).entity(order).build();
        } catch (NumberFormatException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(String.format("Cannot parse %s into a Number", orderId)).build();
        }
    }
}
