package ch.egli.commerce.order;


import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import ch.egli.commerce.order.dto.OrderCreationDTO;
import ch.egli.commerce.order.dto.OrderDisplayDTO;
import ch.egli.commerce.security.Principal;
import ch.egli.commerce.user.UserService;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@RequestScoped
@Transactional
@Path("/order/v1")
public class OrderResource {

  private OrderService orderService;

  private UserService userService;

  public OrderResource() {
    // nope
  }

  @Inject
  OrderResource(OrderService orderService, UserService userService) {
    this.orderService = orderService;
    this.userService = userService;
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
  @Path("/single")
  @Produces(APPLICATION_JSON)
  @PermitAll
  public List<OrderDisplayDTO> getOrdersOfUser(
    @Context HttpServletRequest request) {

    var user = Principal.getInstance().caller(request.getHeader("auth-id"));
    // FIXME use principal for id and/or AuthorizerCheck
    return orderService.getByUser(user.getId())
      .stream()
      .map(o -> new OrderDisplayDTO().fromEntity(o))
      .collect(Collectors.toList());
  }

  @GET
  @Path("")
  @Produces(APPLICATION_JSON)
  @RolesAllowed("ROLE_ADMIN")
  public List<OrderDisplayDTO> getOrders() {
    // TODO implement pagination for future releases
    return orderService.get()
      .stream()
      .map(o -> new OrderDisplayDTO().fromEntity(o))
      .collect(Collectors.toList());
  }

}
