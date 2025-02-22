package ch.egli.commerce.product;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_OCTET_STREAM;
import static javax.ws.rs.core.MediaType.MULTIPART_FORM_DATA;
import static javax.ws.rs.core.MediaType.WILDCARD;

import ch.egli.commerce.enumeration.ProductSortOptions;
import ch.egli.commerce.persistence.Product;
import ch.egli.commerce.product.dto.ProductCreationDTO;
import ch.egli.commerce.product.dto.ProductDTO;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

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
  @Path("/all/{options}")
  @Produces(APPLICATION_JSON)
  @PermitAll
  public List<ProductDTO> getAll(
    @PathParam("options") @DefaultValue("NONE") ProductSortOptions options) {
    // FIXME in any later version: performance -> pagination
    //  we should never select *, rather pass limits from client
    return productService.getAll(options)
      .stream()
      .map(p -> new ProductDTO().fromEntity(p))
      .collect(Collectors.toList());
  }

  @GET
  @Path("/newest/{limit}")
  @Produces(APPLICATION_JSON)
  @PermitAll
  public List<ProductDTO> getNewest(@PathParam("limit") @DefaultValue("4") int limit) {
    return productService.getNewest(limit)
      .stream()
      .map(p -> new ProductDTO().fromEntity(p))
      .collect(Collectors.toList());
  }

  @GET
  @Path("/{id}")
  @Produces(APPLICATION_JSON)
  public ProductDTO get(
    @PathParam("id") @NotNull @Valid String id
  ) {
    return new ProductDTO().fromEntity(productService.find(id));
  }

  @POST
  @Path("")
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public ProductDTO post(
    @NotNull @Valid ProductCreationDTO productCreationDTO
  ) {
    var saved = productService.post(productCreationDTO);
    return new ProductDTO().fromEntity(saved);
  }

  @PUT
  @RolesAllowed("ROLE_ADMIN")
  @Path("")
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public ProductDTO put(
    @NotNull @Valid ProductCreationDTO productDTO
  ) {
    Product merged = productService.update(productDTO);
    return new ProductDTO().fromEntity(merged);
  }

  @DELETE
  @RolesAllowed("ROLE_ADMIN")
  @Path("/{id}")
  @Consumes(APPLICATION_JSON)
  @Produces(WILDCARD)
  public Response delete(
    @PathParam("id") @NotNull @Valid String id
  ) {
    productService.delete(id);
    return Response.ok().build();
  }

  @POST
  @RolesAllowed("ROLE_ADMIN")
  @Path("/image/{productId}")
  @Consumes(MULTIPART_FORM_DATA)
  public Response uploadImage(
    @PathParam("productId") @NotNull @Valid String id,
    MultipartFormDataInput input
  ) {

    productService.uploadImage(id, input);

    return Response.ok().build();
  }

  @GET
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_OCTET_STREAM)
  @Path("/image/{productId}")
  public Response getFile(@PathParam("productId") @NotNull @Valid String id) {
    Product product = productService.find(id);
    return Response.ok(product.getPicture(), APPLICATION_OCTET_STREAM)
      .header("Content-Disposition", "attachment; filename=\"" + product.getFileName() + "\"")
      .build();
  }

}
