package ch.egli.commerce.product;

import ch.egli.commerce.enumeration.ProductSortOptions;
import ch.egli.commerce.persistence.Product;
import ch.egli.commerce.product.dto.ProductCreationDTO;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.MultivaluedMap;
import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

@RequestScoped
@Transactional
public class ProductServiceBean implements ProductService {

  private ProductRepo productRepo;

  public ProductServiceBean() {
    // nope
  }

  @Inject
  ProductServiceBean(ProductRepo productRepo) {
    this.productRepo = productRepo;
  }

  @Override
  public Product find(String id) {
    return productRepo.find(id);
  }

  @Override
  public Collection<Product> getAll(ProductSortOptions options) {
    return productRepo.getAll(options);
  }

  @Override
  public Collection<Product> getNewest(int limit) {
    return productRepo.getNewest(limit);
  }

  @Override
  public Product update(ProductCreationDTO productDTO) {
    Product target = productRepo.find(productDTO.getId());
    return productRepo.put(productDTO.toEntity(target));
  }

  @Override
  public Product post(ProductCreationDTO creationDTO) {

    Product p = creationDTO.toEntity(new Product());
    productRepo.post(p);

    return p;
  }

  @Override
  public void delete(String id) {
    Product toDelete = productRepo.find(id);
    toDelete.setDeleted(true);
    productRepo.put(toDelete);
  }

  @Override
  public void uploadImage(String productId, MultipartFormDataInput input) {

    Product product = productRepo.find(productId);
    String fileName = "";

    Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
    List<InputPart> inputParts = uploadForm.get("file");

    for (InputPart inputPart : inputParts) {

      try {

        MultivaluedMap<String, String> header = inputPart.getHeaders();
        fileName = getFileName(header);

        InputStream inputStream = inputPart.getBody(InputStream.class, null);

        byte[] bytes = IOUtils.toByteArray(inputStream);

        product.setFileName(fileName);
        product.setPicture(bytes);
        product.setFileType(header.getFirst("Content-Type"));

        productRepo.put(product);

      } catch (IOException e) {
        e.printStackTrace();
      }

    }
  }

  private String getFileName(MultivaluedMap<String, String> header) {
    String[] contentDisposition = header.getFirst("Content-Disposition").split(";");

    for (String filename : contentDisposition) {
      if ((filename.trim().startsWith("filename"))) {

        String[] name = filename.split("=");

        String finalFileName = name[1].trim().replaceAll("\"", "");
        return finalFileName;
      }
    }
    return "unknown";
  }
}
