package ch.egli.commerce.user;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import ch.egli.commerce.product.dto.ProductCreationDTO;
import ch.egli.commerce.user.dto.UserDTO;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@RequestScoped
@Transactional
@Path("/user/v1")
// FIXME this is bullshit
public class UserResource {

  @POST
  @Path("/signout")
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public Response signout(
    @NotNull @Valid ProductCreationDTO productCreationDTO
  ) {
    return Response.ok().build();
  }

  @POST
  @Path("/signin")
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public Response signin(
    @NotNull @Valid ProductCreationDTO productCreationDTO
  ) {
    return Response.ok().build();
  }

  @GET
  @Path("")
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public List<UserDTO> get(
    @NotNull @Valid ProductCreationDTO productCreationDTO
  ) {
    return null;
  }

}
