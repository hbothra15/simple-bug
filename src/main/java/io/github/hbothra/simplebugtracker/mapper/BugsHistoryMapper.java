package io.github.hbothra.simplebugtracker.mapper;

import org.mapstruct.Mapper;

import io.github.hbothra.simplebugtracker.eo.BugsHistory;
import io.github.hbothra.simplebugtracker.ro.BugRo;

@Mapper(componentModel = "spring", uses = { BugTypeMapper.class, AuditTrailMapper.class, StatusTypeMapper.class })
public interface BugsHistoryMapper extends BaseMapper<BugsHistory, BugRo> {

}
