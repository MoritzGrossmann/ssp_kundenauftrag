package webservice;

import database.OrderItemRepository;
import database.OrderRepository;
import model.Order;
import model.OrderItem;
import webservice.model.Customer;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Resource für den RESTful Webservice zum Anzeigen und Ändern eines Kundenauftrages
 */
@Path("order/{orderId}")
public class OrderResource {

    @EJB
    private OrderRepository orderRepository;

    @EJB
    private OrderItemRepository orderItemRepository;

    /**
     * Gibt einen Kundenauftrag mit der Id orderId zurück
     * @param orderId Id des Kundenauftrages
     * @return
     * 200 + Kundenauftrag, wenn ok
     * 404, wenn Kundenauftrag mit Id oderId nicht existiert
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrder(@PathParam("orderId") String orderId) {
        try {
            int id = Integer.parseInt(orderId);
            Order order = orderRepository.getById(id);
            if (order == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            webservice.model.Order webOrder= new webservice.model.Order(order);
            return Response.status(Response.Status.OK).entity(webOrder).build();
        } catch (NumberFormatException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(String.format("Cannot parse %s into a Number", orderId)).build();
        }
    }

    /**
     * Gibt den Kunden zu einem Kundenauftrag zurück
     * @param orderId Id des Kundenauftrages
     * @return
     * 200 + Kunden, wenn ok
     * 404, wenn Kundenauftrag mit Id orderId nicht existiert
     */
    @Path("customer")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getCustomerFromOrder(@PathParam("orderId") String orderId) {
        try {
            int id = Integer.parseInt(orderId);
            Order order = orderRepository.getById(id);
            if (order == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.status(Response.Status.OK).entity(new Customer(order.getCustomer())).build();
        } catch (NumberFormatException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(String.format("Cannot parse %s into a Number", orderId)).build();
        }
    }

    /**
     * Löscht eine Bestellposition
     * @param orderId Id des Kundenauftrages indem sich die Bestellposition befindet
     * @param itemId Id der Bestellposition, welche gelöscht werden soll
     * @return
     * 200, wenn Bestellposition erfolgreich gelöscht wurde
     * 404, wenn Kundenauftrag mit Id orderId oder Bestellposition mit Id itemId nicht existiert
     * 400, wenn Bestellposition mit Id itemId nicht dem Kundenauftrag mit der Id orderId angehört
     */
    @Path("items/{itemId}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteOrderItem(@PathParam("orderId") String orderId, @PathParam("itemId") String itemId) {
        try {
            int oId = Integer.parseInt(orderId);
            int pId = Integer.parseInt(itemId);

            OrderItem orderItem = orderItemRepository.getById(pId);
            Order order = orderRepository.getById(oId);

            if (orderItem == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            if (orderItem.getOrder().getId() != oId) {
                return Response.status(Response.Status.BAD_REQUEST).entity(String.format("OrderItem %s is not Part of Customerorder %s", itemId, orderId)).build();
            }
            order.getOrderItems().remove(orderItem);
            orderRepository.update(order);

            return Response.status(Response.Status.OK).build();
        } catch (NumberFormatException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(String.format("Cannot parse %s into a Number", orderId)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    /**
     * Fügt dem Kundenauftrag mit der Id orderId eine neue Bestellposition hinzu
     * @param orderId Id des Kundenauftrages
     * @param orderItem Bestellposition, welche dem Kundenauftrag hinzugefügt werden soll
     * @return
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOrderItem(@PathParam("orderId") String orderId, OrderItem orderItem) {
        try {
            int id = Integer.parseInt(orderId);
            Order order = orderRepository.getById(id);

            if (order == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            orderItem.setOrder(order);

            orderItemRepository.insert(orderItem);

            webservice.model.OrderItem webOrderItem = new webservice.model.OrderItem(orderItem);

            return Response.status(Response.Status.OK).entity(webOrderItem).build();
        } catch (NumberFormatException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(String.format("Cannot parse %s into a Number", orderId)).build();
        }
    }
}
