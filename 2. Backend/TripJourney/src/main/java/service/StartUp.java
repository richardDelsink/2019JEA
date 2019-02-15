package service;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
public class StartUp {

    @Inject
    private String test;

    @PostConstruct
    void initTripJourney(){

    }
}
