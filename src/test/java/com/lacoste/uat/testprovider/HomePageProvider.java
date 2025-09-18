package com.lacoste.uat.testprovider;

public interface HomePageProvider {

  void openCatalogAndCheckProduct();
  void validateSelectedProduct();
  void validateAddCartOperation();
  void removeProductFromCart();
  void checkRemovedProductFromCart();
}
