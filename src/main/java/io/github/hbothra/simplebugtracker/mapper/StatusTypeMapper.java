package io.github.hbothra.simplebugtracker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import io.github.hbothra.simplebugtracker.eo.StatusType;
import io.github.hbothra.simplebugtracker.ro.StatusRo;

@Mapper(componentModel = "spring")
public interface StatusTypeMapper extends BaseMapper<StatusRo, StatusType> {

	@Override
	@Mapping(source = "lookupValue", target = "status")
	@Mapping(source = "lookupCode", target = "statusId")
	StatusRo destinationToSource(StatusType destinations);

	@Override
	@Mapping(target = "lookupValue", source = "status")
	@Mapping(target = "lookupCode", source = "statusId")
	StatusType sourceToDestination(StatusRo sources);
}
