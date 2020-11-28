package ch.egli.commerce.product;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.WILDCARD;

import ch.egli.commerce.persistence.Product;
import ch.egli.commerce.product.dto.ProductCreationDTO;
import ch.egli.commerce.product.dto.ProductDTO;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@RequestScoped
@Transactional
@Path("/product/v1")
public class ProductResource {

  private ProductService productService;

  public ProductResource() {
    // nope
  }

  @Inject
  ProductResource(ProductService productService) {
    this.productService = productService;
  }

  @GET
  @Path("")
  @Produces(APPLICATION_JSON)
  @PermitAll
  public List<ProductDTO> getProducts() {
    // TODO in any later version: performance -> pagination
    //  we should never select *, rather pass limits from client
    return productService.getAll()
      .stream()
      .map(p -> new ProductDTO().fromEntity(p))
      .collect(Collectors.toList());
  }

  @GET
  @Path("/{id}")
  @Produces(APPLICATION_JSON)
  public ProductDTO get(
    @PathParam("id") @NotNull @Valid UUID id
  ) {
    return new ProductDTO().fromEntity(productService.find(id));
  }

  @POST
  @Path("")
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public Response post(
    @NotNull @Valid ProductCreationDTO productCreationDTO
  ) {
    productService.post(productCreationDTO);
    return Response.ok().build();
  }

  @PUT
  @Path("")
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public ProductDTO put(
    @NotNull @Valid ProductDTO productDTO
  ) {
    Product merged = productService.update(productDTO);
    return new ProductDTO().fromEntity(merged);
  }

  @DELETE
  @Path("/{id}")
  @Consumes(APPLICATION_JSON)
  @Produces(WILDCARD)
  public Response delete(
    @PathParam("id") @NotNull @Valid UUID id
  ) {
    productService.delete(id);
    return Response.ok().build();
  }

}
