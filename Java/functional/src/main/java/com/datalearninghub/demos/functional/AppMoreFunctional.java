package com.datalearninghub.demos.functional;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AppMoreFunctional {
	public int [] findCommons(int [] a, int [] b) {
 		return IntStream.of(b).boxed().collect(Collectors.toSet())
 				.stream()
 				.filter(IntStream.of(a).boxed().collect(Collectors.toSet())::contains)
 				.mapToInt(i -> i).toArray();
	}
}
