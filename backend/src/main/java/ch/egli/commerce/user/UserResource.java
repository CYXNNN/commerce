package ch.egli.commerce.user;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import ch.egli.commerce.product.dto.ProductCreationDTO;
import ch.egli.commerce.security.Token;
import ch.egli.commerce.user.dto.LoginDTO;
import ch.egli.commerce.user.dto.UserDTO;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@RequestScoped
@Transactional
@Path("/user/v1")
public class UserResource {

  UserService userService;

  public UserResource() {
    // nop
  }

  @Inject
  public UserResource(UserService userService) {
    this.userService = userService;
  }


  @POST
  @Path("/logout")
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public Response logout() {
    // TODO
    return Response.ok().build();
  }

  @POST
  @PermitAll
  @Path("")
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public Token login(
    @NotNull @Valid LoginDTO loginDto,
    @Context HttpServletRequest request
  ) {
    var token = userService.auth(loginDto);
    if (token != null) {
      request.getSession().setAttribute(token.PARAM_AUTH_ID, token.getAuthId());
      request.getSession().setAttribute(token.PARAM_AUTH_TOKEN, token.getAuthToken());
    }
    return token;
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
