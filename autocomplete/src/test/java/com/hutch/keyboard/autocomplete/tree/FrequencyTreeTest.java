package com.hutch.keyboard.autocomplete.tree;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FrequencyTreeTest {

	private FrequencyTree<Character> frequencyTree;

    @Before
    public void setUp() {
		frequencyTree = new FrequencyTree<Character>(null);
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
	public void test() {		
		System.out.println(frequencyTree.toString());
	}
	
	@Test
	public void testFind() {
		Character[] path = ArrayUtils.toObject("hel".toCharArray());
		FrequencyTree<Character> wordFreq = frequencyTree.findChildren(path);
		System.out.println(wordFreq.toString());
	}
	
	@Test 
	public void testList() {
		Character[] path = ArrayUtils.toObject("hel".toCharArray());
		List<FrequencyTreeItem<Character>> words = frequencyTree.listChildren(path);
		System.out.println(words);
	}

}
