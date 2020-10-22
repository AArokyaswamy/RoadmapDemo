package com.roadmap.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import junit.framework.AssertionFailedError;

@SpringBootTest
public class RoadmapApplicationTests {

	RestTemplate restTemplate = new RestTemplate();
	private static final String SERVER_URI = "http://localhost:8080";

	@Test
	public void testRoadConnected() {

		try {
			String response = restTemplate.getForObject(SERVER_URI + "/connected?origin=Boston&destination=New York",
					String.class);

			assertEquals("yes", response);
		} catch (HttpClientErrorException ex) {
			throw new AssertionFailedError(ex.getMessage());
		}
	}

	@Test
	public void testRoadNotConnected() {

		try {
			String response = restTemplate
					.getForObject(SERVER_URI + "/connected?origin=Philadelphia&destination=Albany", String.class);

			assertEquals("no", response);
		} catch (HttpClientErrorException ex) {
			throw new AssertionFailedError(ex.getMessage());
		}
	}

	@Test
	public void testMissingSourceParameterMissing() {

		try {
			String response = restTemplate.getForObject(SERVER_URI + "/connected?origin=&destination=Albany",
					String.class);
			assertEquals("no", response);
		} catch (HttpClientErrorException ex) {
			throw new AssertionFailedError(ex.getMessage());
		}
	}

	@Test
	public void testMissingDestinationParameterMissing() {

		try {
			String response = restTemplate.getForObject(SERVER_URI + "/connected?origin=Trenton&destination=",
					String.class);
			assertEquals("no", response);
		} catch (HttpClientErrorException ex) {
			throw new AssertionFailedError(ex.getMessage());
		}
	}

	@Test
	public void contextLoads() {

	}

}
