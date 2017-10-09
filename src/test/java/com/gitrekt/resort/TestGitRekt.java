package com.gitrekt.resort;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.jupiter.api; //package for jUnit 5 jupiter api

/**
 * This class is here just to test the basic functionality of JUnit in our 
 * application. It should be removed as we get further into production, but for
 * now, it is a good way to make sure JUnit is working properly.
 */
public class TestGitRekt {
    
    // TODO: Remove temporary tests, add real tests.
    
    @Test
    public void testJUnitWorking() {
        int i = 1;
        assertNotNull(i, "Something is very, very wrong.");
    }
    
    @Disabled
    @Test
    public void testJUnitTestFailure() {
        int i = 1;
        assertNotEquals(i, 1, "JUnit test is failing properly");
    }
}
