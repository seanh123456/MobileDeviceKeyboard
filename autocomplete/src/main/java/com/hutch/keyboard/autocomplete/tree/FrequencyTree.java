package com.hutch.keyboard.autocomplete.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
	
	public FrequencyTree() {
		this.key = null;
		this.count = 0;
		this.size = 0;
		this.children = new ArrayList<FrequencyTree<T>>();
	}
	
	private FrequencyTree(T key) {
		this.key = key;
		this.count = 0;
		this.size = 0;
		this.children = new ArrayList<FrequencyTree<T>>();
	}
	
	public Integer getSize() {
		return this.size;
	}
	
	public void addChild(T[] sequence) {
		
		if (sequence != null) {
			this.size ++;
			addChildRecursive(sequence);
		}
	}
	
	/**
	 * Inserts a sequence into the tree, or increments count if it already exists.
	 * @param term
	 */
	private void addChildRecursive(T[] sequence) {
		FrequencyTree<T> nextFrag = null;
		
		int index = getKeyIndex(sequence[0]);
		
		if (index < 0) {
			// Key does not exist, add new key to children.
			nextFrag = new FrequencyTree<T>(sequence[0]);
			this.children.add(nextFrag);
		} else {
			// Key exists, use it.
			nextFrag = this.children.get(index);
		}
		
		nextFrag.size ++;
		
		// Put child in the right order.
		this.optimizeChild(nextFrag);
		
		if (sequence.length > 1) {
			// Add next key
			nextFrag.addChildRecursive(Arrays.copyOfRange(sequence, 1, sequence.length));
		} else {
			// Finished
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
		if (sequenceFragment == null) {
			// Invalid fragment
			return null;
		} else if (sequenceFragment.length <= 0) {
			// Get all
			return this;
		}

		int index = getKeyIndex(sequenceFragment[0]);
		
		if (index >= 0) {
			// Key found
			if (sequenceFragment.length == 1) {
				// End of key, return result
				return this.children.get(index);
			} else {
				// More of key, keep searching
				return this.children.get(index)
						.findChildren(Arrays.copyOfRange(sequenceFragment, 1, sequenceFragment.length));
			}
		} else {
			// Key not found
			return null;
		}
	}
	
	/**
	 * Returns a list of children and their counts, null if the fragment doesn't exist.
	 * @param termFragment
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<FrequencyTreeItem<T>> listChildren(T[] sequenceFragment) {
		
		FrequencyTree<T> childTree = this.findChildren(sequenceFragment);
		
		// Fragment doesn't exist
		if (childTree == null)
			return null;
		
		T[] searchArray = null;
		if (sequenceFragment.length - 1 < 0) {
			// Get full tree
			searchArray = (T[]) new Object[0];
		} else {
			// Search tree with fragment
			searchArray = Arrays.copyOfRange(sequenceFragment, 0, sequenceFragment.length - 1);
		}
		
		List<FrequencyTreeItem<T>> sequences = childTree.itemizeTree(searchArray);
		Collections.sort(sequences);
		
		return sequences;
	}
	
	/**
	 * Turn tree into a list of sequences with count
	 * @param path
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<FrequencyTreeItem<T>> itemizeTree(T[] path) {
		
		List<FrequencyTreeItem<T>> itemizedTree = new ArrayList<FrequencyTreeItem<T>>();
		path = ArrayUtils.addAll(path, this.key);
		
		// Count must be > 0 to indicate a sequence exists
		if (this.count > 0)
			itemizedTree.add(new FrequencyTreeItem<T>(path, this.count));
		
		// Done this branch when there are no children
		if (this.children.isEmpty()) {
			return itemizedTree;
		}
		
		// More children, keep going
		for(FrequencyTree<T> child : this.children) {
			itemizedTree.addAll(child.itemizeTree(path));
		}
		
		return itemizedTree;
	}
	
	/**
	 * Find child with key from children
	 * @param key
	 * @return
	 */
	private int getKeyIndex(T key) {
		if (this.children == null || this.children.isEmpty())
			return -1;
		
		int length = this.children.size();
		
		// Find key in children
		for(int i = 0; i < length; i ++) {
			if (this.children.get(i).key.equals(key)) {
				return i;
			}
		}
		
		return -1;
	}
	
	/**
	 * Keep order of children largest to smallest (Assumes only child is possibly out of order)
	 * @param child
	 */
	private void optimizeChild(FrequencyTree<T> child) {
		
		int index = getKeyIndex(child.key);
		
		// If new count of child is larger than neighbor to left, move all smaller neighbors to the left.
		while (index > 0 && this.children.get(index-1).size < child.size) {
			this.children.set(index, this.children.get(index-1));
			index --;
		}
		
		// Put child in empty spot.
		this.children.set(index, child);
	}

	@Override
	public String toString() {
		return "FrequencyTree [key=" + this.key + ", count=" + this.count + ", size=" + this.size + ", children=" + this.children + "]";
	}
}
