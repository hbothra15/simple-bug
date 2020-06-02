package io.github.hbothra.simplebugtracker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import io.github.hbothra.simplebugtracker.eo.Bugs;
import io.github.hbothra.simplebugtracker.ro.BugRo;

@Mapper(componentModel = "spring", uses = {BugTypeMapper.class, AuditTrailMapper.class, StatusTypeMapper.class})
public interface BugsMapper extends BaseMapper<Bugs, BugRo> {

	@Override
	// Below are marked as Ignore to avoid HibernateLazy Loading issue
	@Mapping(source = "bugType", target = "bugType", ignore = true)
	@Mapping(source = "bugStatus", target = "bugStatus", ignore = true)
	@Mapping(source = "assignedTo", target = "assignedTo", ignore = true)
	@Mapping(source = "modifiedBy", target = "modifiedBy", ignore = true)
	@Mapping(source = "createdBy", target = "createdBy", ignore = true)
	BugRo sourceToDestination(Bugs source);
}
