package ch.egli.commerce.enumeration;

// TODO for future releases categories should be configurable and available in i18n
//  too much work for now tho
public enum ProductCategory {
  FOOD("Food"),
  DRINK("Drink"),
  UTILS("Utilities");

  private String displayName;

  ProductCategory(String displayName) {
    this.displayName = displayName
    ;
  }
}
