package com.lacoste.uat.steps;

import com.lacoste.uat.chore.AppiumDriverInitializer;
import com.lacoste.uat.testprovider.HomePageProvider;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HomeStepDefinitions {
  protected final HomePageProvider homePageProvider ;

  public HomeStepDefinitions(AppiumDriverInitializer mobileDriver, HomePageProvider homePageProvider) {
    this.homePageProvider = homePageProvider;
  }



  @When("I open product catalog")
  public void openCatalog(){
    homePageProvider.openCatalogAndCheckProduct();
  }


  @When("I add product to cart")
  public void validateProduct() {
    homePageProvider.validateSelectedProduct();
  }

  @When("I expect adding successfully the product")
  public void validateCart() {
    homePageProvider.validateAddCartOperation();
  }

  @When("I remove product from cart")
  public void removeProduct() {
   homePageProvider.removeProductFromCart();
  }

  @When("I expect product to be removed from cart")
  public void checkRemovedProduct() {
   homePageProvider.checkRemovedProductFromCart();
  }
}
