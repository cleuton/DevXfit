package com.datalearninghub.demos.functional;

import java.util.ArrayList;
import java.util.List;

/**
 * finds the intersection between two integer vectors, 
 * with the limitation of not repeating the common numbers. And they said that 
 * You cannot assume anything about the classification, uniqueness and size of the vectors.!
 */
public class App 
{
	public int [] findCommons(int [] a, int [] b) {
		List<Integer> list = new ArrayList<Integer>();
		int [] v1 = null;
		int [] v2 = null;
		if (a.length > b.length) {
			v1 = a;
			v2 = b;
		}
		else {
			v1 = b;
			v2 = a;
		}
		for (int x=0; x<v1.length; x++) {
			for (int y=0; y<v2.length; y++) {
				if (v1[x]==v2[y]) {
					if (!list.contains(v1[x])) {
						list.add(v1[x]);
					}
				}
			}
		}
		int [] returnArray = new int[list.size()];
		for (int i=0; i<list.size(); i++) {
			returnArray[i] = list.get(i);
		}
		return returnArray;
	}
}
