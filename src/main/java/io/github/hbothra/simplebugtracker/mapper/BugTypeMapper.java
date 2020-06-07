package io.github.hbothra.simplebugtracker.mapper;

import org.mapstruct.Mapper;

import io.github.hbothra.simplebugtracker.eo.BugType;

@Mapper(componentModel = "spring")
public interface BugTypeMapper extends BaseMapper<String, BugType> {

	@Override
	default String destinationToSource(BugType destination) {
		return destination.getLookupValue();
	}
	
	@Override
	default BugType sourceToDestination(String source) {
		BugType type = new BugType();
		type.setLookupValue(source);
		return type;
	}
	
}
