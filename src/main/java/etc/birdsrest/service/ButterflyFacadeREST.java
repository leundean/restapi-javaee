/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etc.birdsrest.service;

import entities.Bird;
import entities.Butterfly;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author lceo
 */
@Stateless
@Path("butterflies")
public class ButterflyFacadeREST extends AbstractFacade<Butterfly> {

    @PersistenceContext(unitName = "BirdsPU")
    private EntityManager em;

    public ButterflyFacadeREST() {
        super(Butterfly.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Butterfly entity) {
        super.create(entity);
    }

    /**
     * Generates a list of some example butterflies to initially fill the table
     * <br>Method: GET
     * <br>Path: {URI}/birds/generate
     */
    @GET
    @Path("generate")
    @Consumes(MediaType.APPLICATION_JSON)
    public void generate() {
        List<Butterfly> butterflyList = new ArrayList<Butterfly>();
        butterflyList.add(new Butterfly("citron", "gul", new Date()));
        butterflyList.add(new Butterfly("påfågelsöga", "röd, svart, vit", new Date()));
        butterflyList.add(new Butterfly("mal", "brun", new Date()));
        butterflyList.add(new Butterfly("nässel", "svart, vit, blå, orange", new Date()));
        for (Butterfly butterfly : butterflyList){
            super.create(butterfly);
        }
    }

    /**
     * Creates a butterfly from a JSONobject. The id should be omitted (auto-generated) 
     * <br>Method: PUT
     * <br>Path: {URI}/butterflies/create
     */
    @PUT
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createb(Butterfly butterfly) {
        super.create(butterfly);
    }
    
    
    
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, Butterfly entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Butterfly find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Butterfly> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Butterfly> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

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
