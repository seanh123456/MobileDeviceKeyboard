package com.hutch.keyboard.autocomplete.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;

/**
 * A tree container that stores each part of a sequence incrementally. 
 * - A count > 0 on a node indicates that the sum of the keys along the path from 
 * 	 origin to that node is a full sequence. The value of count is the number of entries that sequence has.
 * - Size indicates how many sequences are in the tree below each particular node.
 * @author Sean
 *
 * @param <T>
 */
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
	
	public Integer getSize() {
		return this.size;
	}
	
	public void addChild(T[] sequence) {
		this.size ++;
		addChildRecursive(sequence);
	}
	
	/**
	 * Inserts a sequence into the tree, or increments count if it already exists.
	 * @param term
	 */
	private void addChildRecursive(T[] sequence) {
		FrequencyTree<T> nextFrag = null;
		
		int index = getKeyIndex(sequence[0]);
		
		if (index < 0) {
			nextFrag = new FrequencyTree<T>(sequence[0]);
			this.children.add(nextFrag);
		} else {
			nextFrag = this.children.get(index);
		}
		
		nextFrag.size ++;
		
		this.optimizeChild(nextFrag);
		
		// If there are more keys, keep adding them. Else this is the end, increment count.
		if (sequence.length > 1) {
			nextFrag.addChildRecursive(Arrays.copyOfRange(sequence, 1, sequence.length));
		} else {
			nextFrag.count ++;
		}
	}

	/**
	 * Returns a tree starting at the node defined by the sequenceFragment. Null if the fragment does
	 * not exist.
	 * @param termFragment
	 * @return Tree from path termFragment
	 */
	public FrequencyTree<T> findChildren(T[] sequenceFragment) {
		if (sequenceFragment == null || sequenceFragment.length <= 0) {
			// Invalid term fragment
			return null;
		}

		int index = getKeyIndex(sequenceFragment[0]);
		
		if (index >= 0) {
			if (sequenceFragment.length == 1) {
				return this.children.get(index);
			} else {
				return this.children.get(index)
						.findChildren(Arrays.copyOfRange(sequenceFragment, 1, sequenceFragment.length));
			}
		} else {
			return null;
		}
	}
	
	/**
	 * Returns a list of children and their counts, null if the fragment doesn't exist.
	 * @param termFragment
	 * @return
	 */
	public List<FrequencyTreeItem<T>> listChildren(T[] sequenceFragment) {
		
		FrequencyTree<T> childTree = this.findChildren(sequenceFragment);
		
		// Fragment doesn't exist
		if (childTree == null)
			return null;
		
		return childTree.itemizeTree(Arrays.copyOfRange(sequenceFragment, 0, sequenceFragment.length-1));
	}
	
	@SuppressWarnings("unchecked")
	private List<FrequencyTreeItem<T>> itemizeTree(T[] path) {
		
		List<FrequencyTreeItem<T>> itemizedTree = new ArrayList<FrequencyTreeItem<T>>();
		path = ArrayUtils.addAll(path, this.key);
		
		// Count must be > 0 to indicate a term exists
		if (this.count > 0)
			itemizedTree.add(new FrequencyTreeItem<T>(path, this.count));
		
		if (this.children.isEmpty()) {
			return itemizedTree;
		}
		
		for(FrequencyTree<T> child : this.children) {
			itemizedTree.addAll(child.itemizeTree(path));
		}
		
		return itemizedTree;
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
