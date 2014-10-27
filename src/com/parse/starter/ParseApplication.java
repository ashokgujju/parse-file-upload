package com.parse.starter;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

public class ParseApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    // Add your initialization code here
    Parse.initialize(this, "7t63rnjnvz4RcGueCGBZKrn7Wmyt2RvbIZlRzn8I", "CPFtb1AJNRKDYihH635DgpBpJBIStJNsSD04sxlF");


    ParseUser.enableAutomaticUser();
    ParseACL defaultACL = new ParseACL();
      
    // If you would like all objects to be private by default, remove this line.
    defaultACL.setPublicReadAccess(true);
    
    ParseACL.setDefaultACL(defaultACL, true);
  }
}
