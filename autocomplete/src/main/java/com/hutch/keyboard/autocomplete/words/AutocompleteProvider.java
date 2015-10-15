package com.hutch.keyboard.autocomplete.words;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import com.hutch.keyboard.autocomplete.tree.FrequencyTree;
import com.hutch.keyboard.autocomplete.tree.FrequencyTreeItem;

public class AutocompleteProvider implements IAutocompleteProvider {
	
	private FrequencyTree<Character> words;
	
	public AutocompleteProvider() {
		words = new FrequencyTree<Character>();
	}

	/**
	 * Returns list of autocomplete candidates ordered by confidence
	 * @param fragment
	 * @return list of autocomplete candidates
	 */
	public void train(String passage) {
		this.addWords(passage.split("\\s+"));
	}
	
	private void addWords(String[] words) {
		for(String word : words) {
			this.addWord(word);
		}
	}
	
	private void addWord(String word) {
		word = parseString(word);
		words.addChild(ArrayUtils.toObject(word.toCharArray()));
	}

	/**
	 * Trains the algorithm with the provided passage
	 * @param passage
	 */
	@Override
	public List<ICandidate> getWords(String fragment) {
		
		if (fragment == null)
			return null;
		
		fragment = parseString(fragment);
		
		List<ICandidate> candidates = new ArrayList<ICandidate>();
		// Fetch search results of fragment
		List<FrequencyTreeItem<Character>> rawCandidates = words.listChildren(ArrayUtils.toObject(fragment.toCharArray()));
		
		if (rawCandidates == null)
			return null;
		
		// Convert return values from Character[] to String
		for (FrequencyTreeItem<Character> rawCandidate : rawCandidates) {
			String word = String.valueOf(ArrayUtils.toPrimitive(rawCandidate.getKeys()));
			candidates.add(new Candidate(word, rawCandidate.getCount()));
		}
		
		return candidates;
	}
	
	/**
	 * Removes invalid characters from string
	 * @return
	 */
	private String parseString(String rawString) {
		// Regex from: http://stackoverflow.com/questions/18830813/how-can-i-remove-punctuation-from-input-text-in-java
		return rawString.trim().replaceAll("[^\\p{L} ]", "").toLowerCase();
	}
}
