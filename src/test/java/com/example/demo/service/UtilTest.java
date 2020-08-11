package com.example.demo.service;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class UtilTest {

    @Test
    public void getBytesTest() {
        String testStr = "hyewonj";
        System.out.println(Arrays.toString(testStr.getBytes()));
    }
}
