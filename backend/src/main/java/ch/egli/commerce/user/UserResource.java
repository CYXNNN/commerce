package ch.egli.commerce.user;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import ch.egli.commerce.address.dto.AddressCreationDTO;
import ch.egli.commerce.address.dto.AddressDTO;
import ch.egli.commerce.security.Principal;
import ch.egli.commerce.security.Token;
import ch.egli.commerce.user.dto.LoginDTO;
import ch.egli.commerce.user.dto.RegisterDTO;
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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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


  @GET
  @Path("/logout")
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public Response logout(@Context HttpServletRequest request) {
    var token = request.getHeader("auth-token");
    var username = Principal.getInstance().caller(token);

    if (username != null) {
      Principal.getInstance().remove(token);
      userService.removeToken(username);
    }

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

  @POST
  @PermitAll
  @Path("/register")
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public Response register(
    @NotNull @Valid RegisterDTO registerDto
  ) {
    userService.register(registerDto);
    return Response.ok().build();
  }

  @GET
  @Path("/tokenvalidity/{authToken}/{authId}")
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public boolean isPrincipalPresent(@Context HttpServletRequest request,
    @PathParam("authToken") @NotNull @Valid String authToken,
    @PathParam("authId") @NotNull @Valid String authId) {

    var username = Principal.getInstance().caller(authToken);

    return username != null && username.equals(authId);
  }

  @GET
  @Path("/users")
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public List<UserDTO> get() {
    return null;
  }

  @GET
  @Path("")
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public AddressDTO getUserAddress(@Context HttpServletRequest request) {
    var username = Principal.getInstance().caller(request.getHeader("auth-token"));
    var address = userService.getAddress(username);

    if (address == null) {
      return new AddressDTO();
    }

    return new AddressDTO().fromEntity(address);

  }

  @PUT
  @Path("")
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public Response putUserAddress(@Context HttpServletRequest request,
    @NotNull @Valid AddressCreationDTO addressDto
  ) {

    var username = Principal.getInstance().caller(request.getHeader("auth-token"));

    userService.putAddress(username, addressDto);

    return Response.ok().build();
  }

}
