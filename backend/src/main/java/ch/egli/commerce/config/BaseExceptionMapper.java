package ch.egli.commerce.config;

import ch.egli.commerce.exceptions.BaseException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BaseExceptionMapper implements ExceptionMapper<BaseException> {

  @Override
  public Response toResponse(BaseException ex) {
    return Response.status(400)
      .entity(ex.getMessage())
      .build();
  }
}
