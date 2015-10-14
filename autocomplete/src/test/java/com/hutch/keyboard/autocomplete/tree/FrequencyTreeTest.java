package com.hutch.keyboard.autocomplete.tree;

import static org.junit.Assert.*;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

public class FrequencyTreeTest {

	private FrequencyTree<Character> frequencyTree;
	
	@Test
	public void test() {
		frequencyTree = new FrequencyTree<Character>(null);
		
		frequencyTree.addChild(ArrayUtils.toObject("hello".toCharArray()));
		
		System.out.println(frequencyTree.toString());
	}

}
