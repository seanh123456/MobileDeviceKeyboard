package com.hutch.keyboard.autocomplete;

import java.util.List;

import com.hutch.keyboard.autocomplete.words.AutocompleteProvider;
import com.hutch.keyboard.autocomplete.words.IAutocompleteProvider;
import com.hutch.keyboard.autocomplete.words.ICandidate;

public class Autocomplete {

	private IAutocompleteProvider provider;
	
	public Autocomplete() {
		provider = new AutocompleteProvider();
	}
	
	/**
	 * Add a sentence to the autocomplete dictionary
	 * @param sentence
	 */
	public void addSentence(String sentence) {
		provider.train(sentence);
	}
	
	/**
	 * Get autocomplete suggestions from fragment
	 * @param fragment
	 * @return
	 */
	public List<ICandidate> getWords(String fragment) {
		return provider.getWords(fragment);
	}
}
