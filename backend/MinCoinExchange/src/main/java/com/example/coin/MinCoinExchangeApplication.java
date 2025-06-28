package com.example.coin;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;
import com.example.coin.controller.CoinExchangeController;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class MinCoinExchangeApplication extends Application<io.dropwizard.Configuration> {
    public static void main(String[] args) throws Exception {
        new MinCoinExchangeApplication().run(args);
    }

    @Override
    public void run(io.dropwizard.Configuration configuration,
                    Environment environment) {

        // Enable CORS
        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        cors.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM,
                "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM,
                "OPTIONS,GET,PUT,POST,DELETE,HEAD");
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        // Register your controller
        environment.jersey().register(new CoinExchangeController());
    }
}