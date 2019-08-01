/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etc.birdsrest.service;

import entities.Bird;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * The REST facade (service endpoint) with methods accessible through HTTP requests. Path set to "/birds".
 * Outputs and accepts content data in form of application/json.
 * 
 * @author Mikael Lundin
 * @version 0.1.0
 * @since 2019-01-02 
 */
@Stateless
@Path("birds")
public class BirdFacadeREST extends AbstractFacade<Bird> {

    @PersistenceContext(unitName = "BirdsPU")
    private EntityManager em;

    public BirdFacadeREST() {
        super(Bird.class);
    }

    /**
     * Generates a list of 10 example birds to initially fill the table
     * <br>Method: GET
     * <br>Path: {URI}/birds/generate
     */
    @GET
    @Path("generate")
    @Consumes(MediaType.APPLICATION_JSON)
    public void generate() {
        List<Bird> birdList = new ArrayList<Bird>();
        birdList.add(new Bird("gråsparv", "grå", new Date()));
        birdList.add(new Bird("skata", "svart, vit", new Date()));
        birdList.add(new Bird("kråka", "svart, grå", new Date()));
        birdList.add(new Bird("pelikan", "vit", new Date()));
        birdList.add(new Bird("flamingo", "rosa", new Date()));
        birdList.add(new Bird("domherre", "svart, röd", new Date()));
        birdList.add(new Bird("gröngöling", "grön", new Date()));
        birdList.add(new Bird("korp", "svart", new Date()));
        birdList.add(new Bird("duva", "vit", new Date()));
        birdList.add(new Bird("papegoja", "röd, grön, gul, blå", new Date()));
        for (Bird bird : birdList){
            super.create(bird);
        }
    }

    /**
     * Creates a bird from a JSONobject. The id should be omitted (auto-generated) 
     * <br>Method: PUT
     * <br>Path: {URI}/birds/create
     */
    @PUT
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(Bird bird) {
        super.create(bird);
    }


    /**
     * With JSONobject id: Updates an existing table entity. Updates with missing object properties are set to <i>null</i> in database.
     * <br>Without JSONobject id: Inserts a new table entity.
     * <br>Method: PUT
     * <br>Path: {URI}/birds/{id}
     */
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void edit(@PathParam("id") Long id, Bird entity) {
        super.edit(entity);
    }

    /**
     * Deletes the table entity by id set in path.
     * <br>Method: DELETE
     * <br>Path: {URI}/birds/{id}
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    /**
     * Retrieves the table entity by id set in path.
     * <br>Method: GET
     * <br>Path: {URI}/birds/{id}
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Bird find(@PathParam("id") Long id) {
        return super.find(id);
    }

    /**
     * Finds all table entities
     * <br>Method: GET
     * <br>Path: {URI}/birds
     */
    @GET
    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public List<Bird> findAll() {
        return super.findAll();
    }    
    
    /**
     * Finds all table entities with whole or part of entity's name property.
     * <br>Method: GET
     * <br>Path (example): {URI}/birds/name/flamingo
     */
    @GET
    @Path("name/{nameParam}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Bird> getBirdsByName(@PathParam("nameParam") String name) {
        String statement = "SELECT * FROM BIRD WHERE name LIKE '%" + name + "%'";
        Query query = em.createNativeQuery(statement, Bird.class);
        return new ArrayList(query.getResultList());
    }    

    /**
     * Finds all table entities with whole or part of entity's colors property.
     * <br>Method: GET
     * <br>Path (example): {URI}/birds/colors/yello
     */
    @GET
    @Path("colors/{colorsParam}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Bird> getBirdsByColors(@PathParam("colorsParam") String colors) {
        String statement = "SELECT * FROM BIRD WHERE colors LIKE '%" + colors + "%'";
        Query query = em.createNativeQuery(statement, Bird.class);
        return new ArrayList(query.getResultList());
    }    
    
    
    /**
     * Finds all table entities within selected limits. Accepts numbers exceeding list size.
     * <br>Method: GET
     * <br>Path (example): {URI}/birds/2/5
     */
    @GET
    @Path("{from}/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Bird> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    /**
     * Returns a string with table list size.
     * <br>Method: GET
     * <br>Path: {URI}/birds/count
     */
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
