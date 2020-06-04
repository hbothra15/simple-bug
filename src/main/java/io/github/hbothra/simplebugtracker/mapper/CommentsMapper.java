package io.github.hbothra.simplebugtracker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import io.github.hbothra.simplebugtracker.eo.BugsComments;
import io.github.hbothra.simplebugtracker.ro.BugCommentsRo;

@Mapper(componentModel = "spring", uses = {BugsMapper.class, AuditTrailMapper.class})
public interface CommentsMapper extends BaseMapper<BugsComments, BugCommentsRo> {

	@Override
	@Mapping(source = "modifiedBy", target = "modifiedBy", ignore = true)
	@Mapping(source = "createdBy", target = "createdBy", ignore = true)
	BugCommentsRo sourceToDestination(BugsComments source);
}
