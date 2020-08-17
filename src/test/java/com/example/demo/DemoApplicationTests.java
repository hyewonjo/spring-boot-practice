package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void iteratorTest() {
		Map map = new HashMap<String, String>();

		// data put into map
		long startTime = System.currentTimeMillis();
		for (int i = 0; i<2000000; i++) {
			map.put(i+"", i+"");
		}
		long endTime = System.currentTimeMillis();
		System.out.println("map put time : " + (endTime - startTime) + "ms");

		Set keySet = map.keySet();
		Object[] keySetArray = keySet.toArray();

		// update data using for loop
		startTime = System.currentTimeMillis();
		for (int i=0; i < keySetArray.length; i++) {
			map.put(i+"", i+".");
		}

		endTime = System.currentTimeMillis();
		System.out.println("update data using for loop time : " + (endTime - startTime) + "ms");

		// update data using iterator
		Set entrySet = map.entrySet();
		Iterator it = entrySet.iterator();

		int i = 0;
		startTime = System.currentTimeMillis();
		while (it.hasNext()) {
			Map.Entry currentEntrySet = (Map.Entry)it.next();
			currentEntrySet.setValue(++i + ",");
		}

		endTime = System.currentTimeMillis();
		System.out.println("update data using iterator time : " + (endTime - startTime) + "ms");

	}
}
