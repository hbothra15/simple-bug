package io.github.hbothra.simplebugtracker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import io.github.hbothra.simplebugtracker.eo.BugType;
import io.github.hbothra.simplebugtracker.ro.BugTypeRo;

@Mapper(componentModel = "spring")
public interface BugTypeMapper extends BaseMapper<BugTypeRo, BugType> {

	@Override
	@Mapping(source = "lookupValue", target = "bugType")
	@Mapping(source = "lookupCode", target = "bugTypeId")
	BugTypeRo destinationToSource(BugType destination);

	@Override
	@Mapping(target = "lookupValue", source = "bugType")
	@Mapping(target = "lookupCode", source = "bugTypeId")
	BugType sourceToDestination(BugTypeRo source);
}
