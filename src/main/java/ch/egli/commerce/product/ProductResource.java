package ch.egli.commerce.product;


import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.WILDCARD;

import ch.egli.commerce.persistence.Product;
import ch.egli.commerce.product.dto.ProductDTO;
import java.util.Collection;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@RequestScoped
@Transactional
@Path("/product/v1")
public class ProductResource {

  private ProductService productService;

  @Inject
  ProductResource(ProductService productService) {
    this.productService = productService;
  }

  @GET
  @Path("")
  @Produces(APPLICATION_JSON)
  public Collection<ProductDTO> getProducts() {
    // TODO
    return null;
  }

  @POST
  @Path("")
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public ProductDTO createProduct(
    // TODO

  ) {
    return null;
  }

  @PUT
  @Path("")
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public ProductDTO updateProduct(
    // TODO

  ) {
    return null;
  }

  @DELETE
  @Path("")
  @Consumes(APPLICATION_JSON)
  @Produces(WILDCARD)
  public Response deleteProduct(

  ) {
    // TODO
    return Response.ok().build();
  }

}
