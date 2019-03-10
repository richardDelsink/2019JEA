package service;
import dao.JPA;
import domain.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
public class StartUp {

    @Inject
    private UserService service;

    public StartUp(){

    }

    @PostConstruct
    void initTripJourney(){
       service.addUser(new User( "","Richard","","","","",true,"","",""));
  //      service.addUser(new User("","Willem","","","","",true,"","",""));
    }
}
