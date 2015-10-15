package com.hutch.keyboard.autocomplete.words;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import com.hutch.keyboard.autocomplete.tree.FrequencyTree;

public class WordContainer {

	private FrequencyTree<Character> words;
	
	public WordContainer() {
		words = new FrequencyTree<Character>();
	}
	
	public void addWords(List<String> words) {
		for(String word : words) {
			this.addWord(word);
		}
	}
	
	public void addWord(String word) {
		words.addChild(ArrayUtils.toObject(word.toCharArray()));
	}
}
