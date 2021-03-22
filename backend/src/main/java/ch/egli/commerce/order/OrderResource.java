package ch.egli.commerce.order;


import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import ch.egli.commerce.order.dto.OrderCreationDTO;
import ch.egli.commerce.order.dto.OrderDisplayDTO;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@RequestScoped
@Transactional
@Path("/order/v1")
public class OrderResource {

  private OrderService orderService;

  public OrderResource() {
    // nope
  }

  @Inject
  OrderResource(OrderService orderService) {
    this.orderService = orderService;
  }

  @POST
  @Path("")
  @Produces(APPLICATION_JSON)
  @PermitAll
  public Response post(@NotNull @Valid OrderCreationDTO orderCreationDTO) {
    this.orderService.post(orderCreationDTO);
    return Response.ok().build();
  }

  @GET
  @Path("/{userId}")
  @Produces(APPLICATION_JSON)
  @PermitAll
  public List<OrderDisplayDTO> getOrdersOfUser(@PathParam("userId") @NotNull @Valid String userId) {
    return orderService.getByUser(userId)
      .stream()
      .map(o -> new OrderDisplayDTO().fromEntity(o))
      .collect(Collectors.toList());
  }

}
