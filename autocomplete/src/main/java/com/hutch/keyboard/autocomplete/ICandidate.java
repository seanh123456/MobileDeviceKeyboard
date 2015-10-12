package com.hutch.keyboard.autocomplete;

public interface ICandidate {
	
	/**
	 * returns the autocomplete candidate
	 * @return
	 */
    public String getWord();
    
    /**
     * returns the confidence* for the candidate
     * @return
     */
    public Integer getConfidence();
}
