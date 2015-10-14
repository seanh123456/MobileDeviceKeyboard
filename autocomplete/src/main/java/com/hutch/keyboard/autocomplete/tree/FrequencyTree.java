package com.hutch.keyboard.autocomplete.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FrequencyTree<T> {

	private T key;
	private Integer count;
	private Integer size;
	private List<FrequencyTree<T>> children;
	
	public FrequencyTree(T key) {
		this.key = key;
		this.count = 0;
		this.size = 0;
		this.children = new ArrayList<FrequencyTree<T>>();
	}
	
	public FrequencyTree(T key, List<FrequencyTree<T>> children) {
		this.key = key;
		this.count = 0;
		this.size = 0;
		this.children = children;
	}
	
	public void addChild(T[] term) {
		FrequencyTree<T> nextFrag = null;
		
		int index = getKeyIndex(term[0]);
		
		if (index < 0) {
			nextFrag = new FrequencyTree<T>(term[0]);
			this.children.add(nextFrag);
		} else {
			nextFrag = this.children.get(index);
		}
		
		nextFrag.size ++;
		
		this.optimizeChild(nextFrag);
		
		System.out.println("term.length = " + term.length);
		if (term.length > 1) {
			nextFrag.addChild(Arrays.copyOfRange(term, 1, term.length));
		} else {
			nextFrag.count ++;
		}
	}

	public FrequencyTree<T> findChildren(T[] termFragment) {
		if (termFragment == null || termFragment.length <= 0) {
			// Invalid term fragment
			return null;
		}

		int index = getKeyIndex(termFragment[0]);
		
		if (index >= 0) {
			if (termFragment.length == 1) {
				return this.children.get(index);
			} else {
				return this.children.get(index)
						.findChildren(Arrays.copyOfRange(termFragment, 1, termFragment.length-1));
			}
		} else {
			return null;
		}
	}
	
	private int getKeyIndex(T key) {
		if (this.children == null)
			return -1;
		
		int length = this.children.size();
		
		for(int i = 0; i < length; i ++) {
			if (this.children.get(i).key.equals(key)) {
				return i;
			}
		}
		
		return -1;
	}
	
	private void optimizeChild(FrequencyTree<T> child) {
		
		int index = getKeyIndex(child.key);
		
		while (index > 0 && this.children.get(index-1).size < child.size) {
			this.children.set(index, this.children.get(index-1));
			index --;
		}
		
		this.children.set(index, child);
	}

	@Override
	public String toString() {
		return "FrequencyTree [key=" + this.key + ", count=" + this.count + ", size=" + this.size + ", children=" + this.children + "]";
	}
}
