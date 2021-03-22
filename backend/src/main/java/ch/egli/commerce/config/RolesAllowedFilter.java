package ch.egli.commerce.config;

import java.io.IOException;
import java.util.Arrays;
import javax.annotation.Priority;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 * Implementation for https://github.com/eclipse-ee4j/jaxrs-api/issues/563
 */
@Provider
@Priority(Priorities.AUTHENTICATION)
public class RolesAllowedFilter implements ContainerRequestFilter {

  @Context
  private ResourceInfo resourceInfo;

  @Inject
  private SecurityContext securityContext;

  @Override
  public void filter(ContainerRequestContext requestContext) throws IOException {
    PermitAll permitAll = resourceInfo.getResourceClass().getAnnotation(PermitAll.class);
    if (resourceInfo.getResourceMethod().getAnnotation(PermitAll.class) != null) {
      permitAll = resourceInfo.getResourceMethod().getAnnotation(PermitAll.class);
    }
    if (permitAll != null) {
      return;
    }

    RolesAllowed rolesAllowed = resourceInfo.getResourceClass().getAnnotation(RolesAllowed.class);
    if (resourceInfo.getResourceMethod().getAnnotation(RolesAllowed.class) != null) {
      rolesAllowed = resourceInfo.getResourceMethod().getAnnotation(RolesAllowed.class);
    }

    if (rolesAllowed != null && Arrays.stream(rolesAllowed.value())
      .noneMatch(s -> securityContext.isCallerInRole(s))) {
      requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
    }
  }
}
