package webservice;

import database.OrderRepository;
import database.ProductionOrderRepository;
import model.Order;
import model.ProductionOrder;
import webservice.model.Customer;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("order/{orderId}")
public class OrderResource {

    @EJB
    private OrderRepository orderRepository;

    @EJB
    private ProductionOrderRepository productionOrderRepository;

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

    @Path("customer")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
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

    @Path("productionOrder/{productionOrderId}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteProductionOrder(@PathParam("orderId") String orderId, @PathParam("productionOrderId") String prodOrderId) {
        try {
            int oId = Integer.parseInt(orderId);
            int pId = Integer.parseInt(prodOrderId);

            ProductionOrder productionOrder = productionOrderRepository.getById(pId);
            Order order = orderRepository.getById(oId);

            if (productionOrder == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            if (productionOrder.getOrder().getId() != oId) {
                return Response.status(Response.Status.BAD_REQUEST).entity(String.format("Productionorder %s is not Part of Customerorder %s", prodOrderId, orderId)).build();
            }
            order.getProductionOrders().remove(productionOrder);
            orderRepository.update(order);

            return Response.status(Response.Status.OK).build();
        } catch (NumberFormatException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(String.format("Cannot parse %s into a Number", orderId)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProductionOrder(@PathParam("orderId") String orderId) {
        try {
            int id = Integer.parseInt(orderId);
            Order order = orderRepository.getById(id);
            if (order == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            ProductionOrder productionOrder = new ProductionOrder();

            order.addProductionOrder(productionOrder);

            order = orderRepository.update(order);

            return Response.status(Response.Status.OK).entity(order.getProductionOrders().toArray()[order.getProductionOrders().size()-1]).build();
        } catch (NumberFormatException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(String.format("Cannot parse %s into a Number", orderId)).build();
        }
    }
}
