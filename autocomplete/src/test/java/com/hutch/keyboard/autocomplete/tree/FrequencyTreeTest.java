package com.hutch.keyboard.autocomplete.tree;

import static org.junit.Assert.*;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FrequencyTreeTest {

	private FrequencyTree<Character> frequencyTree;

    @Before
    public void setUp() {
		frequencyTree = new FrequencyTree<Character>();
		frequencyTree.addChild(ArrayUtils.toObject("hello".toCharArray()));
		frequencyTree.addChild(ArrayUtils.toObject("hydra".toCharArray()));
		frequencyTree.addChild(ArrayUtils.toObject("hydrant".toCharArray()));
		frequencyTree.addChild(ArrayUtils.toObject("helicopter".toCharArray()));
		frequencyTree.addChild(ArrayUtils.toObject("helipad".toCharArray()));
		frequencyTree.addChild(ArrayUtils.toObject("helipad".toCharArray()));
		frequencyTree.addChild(ArrayUtils.toObject("helipad".toCharArray()));
		frequencyTree.addChild(ArrayUtils.toObject("helipad".toCharArray()));
    }
    
    @After
    public void tearDown() {
    	frequencyTree = null;
    }
	
	@Test
	public void testTree() {
		assertEquals(new Integer(8), frequencyTree.getSize());
	}
	
	@Test
	public void testFindNull() {
		// Find null
		assertEquals(null, frequencyTree.findChildren(null));
	}
	
	@Test
	public void testFindExisting() {
		// Find existing fragment
		Character[] path = ArrayUtils.toObject("hel".toCharArray());
		assertEquals(new Integer(6), frequencyTree.findChildren(path).getSize());
	}
	
	@Test
	public void testFindNonExisting() {
		// Find non-existing fragment
		Character[] path = ArrayUtils.toObject("help".toCharArray());
		assertEquals(null, frequencyTree.findChildren(path));
	}
	
	@Test 
	public void testListNull() {
		// List null
		assertEquals(null, frequencyTree.listChildren(null));
	}
	
	@Test 
	public void testListExisting() {
		// List existing fragment 
		Character[] path = ArrayUtils.toObject("hel".toCharArray());
		assertEquals(3, frequencyTree.listChildren(path).size());
	}
	
	@Test 
	public void testListNonExisting() {
		// List non-existing fragment
		Character[] path = ArrayUtils.toObject("help".toCharArray());
		assertEquals(null, frequencyTree.listChildren(path));
	}

}
