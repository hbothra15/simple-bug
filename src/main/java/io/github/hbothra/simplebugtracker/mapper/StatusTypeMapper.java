package io.github.hbothra.simplebugtracker.mapper;

import org.mapstruct.Mapper;

import io.github.hbothra.simplebugtracker.eo.StatusType;

@Mapper(componentModel = "spring")
public interface StatusTypeMapper extends BaseMapper<String, StatusType> {

	@Override
	default String destinationToSource(StatusType destination) {
		return destination.getLookupValue();
	}
	
	@Override
	default StatusType sourceToDestination(String source) {
		StatusType type = new StatusType();
		type.setLookupValue(source);
		return type;
	}
}
