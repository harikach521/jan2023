package com.example.jan2023.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.mongodb.assertions.Assertions.assertNotNull;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest //Annotation helps us to run the test scenarioes in the springboot context
public class HelloControllerTest {

    @Autowired
    HelloController hello;

    @Test
    public void testSayHello(){
        String expectedValue = "Welcome to the SpringBoot Framework";
        String actualValue = hello.sayHello();

        assertEquals(expectedValue, actualValue);//stressing that these both should be equal
    }

    @Test
    public void testSayHelloWithName(){
        String inputName = "TESTNAME";
        String responseString = hello.sayHelloWithName(inputName);

        assertNotNull(responseString);
        //check whether that response string contains input
        assertThat(responseString.contains(inputName));
    }

    @Test
    public void testSayHelloWithGreeting(){
        String inputName = "TESTNAME";
        String responseString = hello.sayHelloWithGreeting(inputName);

        assertNotNull(responseString);
        assertThat(responseString.contains(inputName));
    }
}
