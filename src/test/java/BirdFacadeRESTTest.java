/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import etc.birdsrest.service.BirdFacadeREST;
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
public class BirdFacadeRESTTest {
    
    public BirdFacadeRESTTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    BirdFacadeREST birdFacadeREST;
    
    @Before
    public void setUp() {
        birdFacadeREST = new BirdFacadeREST();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Tests that the birds facade exists
    */
    @Test
    public void birdFacadeExists() {
        assertNotNull(birdFacadeREST);
    }
    
}
