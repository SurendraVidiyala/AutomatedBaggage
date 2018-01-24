package com.automatedbaggage.tests;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AutomatedBaggageAppTest 
    extends TestCase
{
  
    public AutomatedBaggageAppTest( String testName )
    {
        super( testName );
    }
    public static Test suite()
    {
        return new TestSuite( AutomatedBaggageAppTest.class );
    }

    public void testApp()
    {
        assertTrue( true );
    }
}
