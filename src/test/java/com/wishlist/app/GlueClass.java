package com.wishlist.app;

import com.wishlist.app.steps.WishlistSteps;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = WishlistSteps.class)
public class GlueClass { }
