package com.lacoste.uat.testprovider.defaultprovider;

public interface HomePageProvider {

  void openCatalogAndCheckProduct();
  void validateSelectedProduct();
  void validateAddCartOperation();
  void removeProductFromCart();
  void checkRemovedProductFromCart();
}
