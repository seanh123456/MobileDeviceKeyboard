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
	public void testTree() {
		System.out.println(words.getWords("thi").toString());
		System.out.println(words.getWords("nee").toString());
		System.out.println(words.getWords("th").toString());
	}

}
