package com.hutch.keyboard.autocomplete.tree;

import java.util.Arrays;

public class FrequencyTreeItem<T> implements Comparable<FrequencyTreeItem<T>> {
	private T[] keys;
	private Integer count;
	
	public FrequencyTreeItem(T[] keys, Integer count) {
		this.keys = keys;
		this.count = count;
	}

	public T[] getKeys() {
		return keys;
	}
	public Integer getCount() {
		return count;
	}

	@Override
	public String toString() {
		return "FrequencyTreeItem [keys=" + Arrays.toString(keys) + ", count=" + count + "]";
	}

	/**
	 * Sorts by count, largest to smallest
	 */
	@Override
	public int compareTo(FrequencyTreeItem<T> compareItem) {		
		return compareItem.getCount() - this.count;
	}
}
