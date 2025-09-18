package com.lacoste.uat.testprovider.defaultprovider;

import org.springframework.stereotype.Service;

@Service
public interface LoginPageProvider {

  void logInPagecheck();

  void logInWithMultiCredentials(String credentials);

  void iCheckloginResult(String expectedResult);

  void logOutCheck();

}
