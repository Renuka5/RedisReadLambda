package com.sample.RedisReadLambda;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class RedisLambdaReadHandlerTest {

    private static Map<String,String> input;

    @BeforeClass
    public static void createInput() throws IOException {
        // TODO: set up your sample input object here.
    	
        input = new HashMap<String, String>();
        input.put("key", "Employee");
    }

    private Context createContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("Function Name");

        return ctx;
    }

    @Test
    public void testLambdaFunctionHandler() {
    	RedisLambdaReadHandler handler = new RedisLambdaReadHandler();
        Context ctx = createContext();

        Object output = handler.handleRequest(input, ctx);        
        
        if (output != null) {
            System.out.println(output.toString());
        }
    }
}
