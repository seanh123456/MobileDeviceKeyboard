package com.hutch.keyboard.autocomplete.words;

public class Candidate implements ICandidate {

	private String word;
	private Integer confidence;
	
	public Candidate(String word, Integer confidence) {
		this.word = word;
		this.confidence = confidence;
	}
	
	public String getWord() {
		return word;
	}
	public Integer getConfidence() {
		return confidence;
	}

	@Override
	public String toString() {
		return "Candidate [word=" + word + ", confidence=" + confidence + "]";
	}
}
