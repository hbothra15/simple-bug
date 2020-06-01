package io.github.hbothra.simplebugtracker.mapper;

import java.util.List;

public interface BaseMapper<S, D> {

	D sourceToDestination (S source);
	S destinationToSource (D destination);
	
	List<D> sourceToDestination(List<S> sources);
	List<S> destinationToSource(List<D> destinations);
}
