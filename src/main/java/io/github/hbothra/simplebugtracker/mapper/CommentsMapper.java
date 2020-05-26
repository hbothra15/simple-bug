package io.github.hbothra.simplebugtracker.mapper;

import org.mapstruct.Mapper;

import io.github.hbothra.simplebugtracker.eo.BugsComments;
import io.github.hbothra.simplebugtracker.ro.BugCommentsRo;

@Mapper(componentModel = "spring", uses = {BugTypeMapper.class, AuditTrailMapper.class})
public interface CommentsMapper extends BaseMapper<BugsComments, BugCommentsRo> {
	
	@Override
	BugsComments destinationToSource(BugCommentsRo destination);
	
	@Override
	BugCommentsRo sourceToDestination(BugsComments source);

}
