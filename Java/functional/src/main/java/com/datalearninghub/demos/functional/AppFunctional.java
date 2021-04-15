package com.datalearninghub.demos.functional;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AppFunctional {
	public int [] findCommons(int [] a, int [] b) {
	    Set<Integer> setA = IntStream.of(a).boxed().collect(Collectors.toSet());
	    Set<Integer> setB = IntStream.of(b).boxed().collect(Collectors.toSet());
	    setB.retainAll(setA);
	    return setB.stream().mapToInt(i -> i).toArray();
	}
}
