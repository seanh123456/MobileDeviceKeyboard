package com.hutch.keyboard.autocomplete;

import java.util.List;

public interface IAutocompleteProvider {
	/**
	 * returns list of autocomplete candidates ordered by confidence
	 * @param fragment
	 * @return list of autocomplete candidates
	 */
	public List<ICandidate> getWords(String fragment);
	
	/**
	 * trains the algorithm with the provided passage
	 * @param passage
	 */
    public void train(String passage);
}
