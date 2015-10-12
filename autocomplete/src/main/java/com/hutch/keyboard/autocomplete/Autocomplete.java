package com.hutch.keyboard.autocomplete;

import java.util.List;

public class Autocomplete {

	private IAutocompleteProvider provider;
	
	public List<ICandidate> getWords(String fragment) {
		return provider.getWords(fragment);
	}
}
