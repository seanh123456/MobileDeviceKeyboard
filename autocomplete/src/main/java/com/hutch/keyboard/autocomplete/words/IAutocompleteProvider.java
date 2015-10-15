package com.hutch.keyboard.autocomplete.words;

import java.util.List;

public interface IAutocompleteProvider {
	/**
	 * Returns list of autocomplete candidates ordered by confidence
	 * @param fragment
	 * @return list of autocomplete candidates
	 */
	public List<ICandidate> getWords(String fragment);
	
	/**
	 * Trains the algorithm with the provided passage
	 * @param passage
	 */
    public void train(String passage);
}
