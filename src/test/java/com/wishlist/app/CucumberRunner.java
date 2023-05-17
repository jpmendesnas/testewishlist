package com.wishlist.app;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/com/wishlist/app/resources",
        glue = "com.wishlist.app.steps"
)
public class CucumberRunner {

}
