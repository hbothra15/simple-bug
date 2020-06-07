package io.github.hbothra.simplebugtracker.mapper;

import org.mapstruct.Mapper;

import io.github.hbothra.simplebugtracker.eo.Bugs;
import io.github.hbothra.simplebugtracker.ro.BugRo;

@Mapper(componentModel = "spring", uses = { SimpleUserMapper.class, BugTypeMapper.class, StatusTypeMapper.class })
public interface BugsMapper extends BaseMapper<Bugs, BugRo> {

}
