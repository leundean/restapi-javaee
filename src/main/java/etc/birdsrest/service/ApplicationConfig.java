/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etc.birdsrest.service;

import java.util.Set;
import javax.ws.rs.core.Application;
import javax.ws.rs.ApplicationPath;
/**
 * 
 * Main application with listing of the classes containing the service endpoints.
 * Application path (here set to "/") defines the service URI of the web-application.
 * 
 * @author Mikael Lundin
 * @version 0.1.0
 * @since 2019-01-02 
 */
@ApplicationPath("")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        resources.add(BirdFacadeREST.class);
        resources.add(ButterflyFacadeREST.class);
        return resources;
    }

}
