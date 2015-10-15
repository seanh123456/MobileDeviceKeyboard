package com.hutch.keyboard.autocomplete.words;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AutocompleteProviderTest {
	
	private AutocompleteProvider words;

    @Before
    public void setUp() {
    	words = new AutocompleteProvider();
    	words.train("The third thing that I need to tell you is that this thing does not think thoroughly.");
    }
    
    @After
    public void tearDown() {
    	words = null;
    }
	
	@Test
	public void testNull() {
		assertEquals(null, words.getWords(null));
	}
	
	@Test
	public void testFragment() {
		assertEquals(7, words.getWords("th").size());
	}
	
	@Test
	public void testFullWord() {
		assertEquals(1, words.getWords("thoroughly").size());
	}
	
	@Test
	public void testNonExistantWord() {
		assertEquals(null, words.getWords("alexander"));
	}
	
	@Test
	public void testCase() {
    	words.train("tHiSiSaCaSeTeSt");
		assertEquals("thisisacasetest", words.getWords("tHiSiSaCaSeTeSt").get(0).getWord());
	}
	
	@Test
	public void testDesiredSpecialCharacters() {
    	words.train("'ÃÆØò");
		assertEquals("ãæøò", words.getWords("'ÃÆØò").get(0).getWord());
	}
	
//	@Test
//	public void testSortedOrder() {
//		assertEquals(null, words.getWords(null));
//	}

}
