package com.rest.api.test; 
import org.junit.runner.RunWith; 
import org.junit.runners.Suite; 
// Define the test suite 
@RunWith(Suite.class) 
@Suite.SuiteClasses({ 
    AdditionsAPITest.class, 
    SubtractionAPITest.class, 
    ArithmeticIntegrationTest.class 
}) 
public class ArithmeticTestSuite { 
    // This class remains empty, it is used only as a holder for the above annotations 
} 