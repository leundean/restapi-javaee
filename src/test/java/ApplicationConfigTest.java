/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import etc.birdsrest.service.ApplicationConfig;
import etc.birdsrest.service.BirdFacadeREST;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mikael Lundin
 * @version 0.1.0
 * @since 2019-01-02 
 */
public class ApplicationConfigTest {
    
    public ApplicationConfigTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    ApplicationConfig restApp;
    
    @Before
    public void setUp() {
        restApp = new ApplicationConfig();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Tests that the REST application exists
    */
    @Test
    public void restAppExists() {
        assertNotNull(restApp);
    }
    
    /**
     * Tests that application has facades (endpoints)
    */
    @Test
    public void restAppHasEndPointClasses() {
        Set<Class<?>> endPointClasses = restApp.getClasses();
        assertTrue(endPointClasses.contains(new BirdFacadeREST().getClass()));
    }
}
