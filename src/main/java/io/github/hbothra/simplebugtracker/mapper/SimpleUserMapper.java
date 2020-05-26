package io.github.hbothra.simplebugtracker.mapper;

import org.mapstruct.Mapper;

import io.github.hbothra.simplebugtracker.eo.SimpleUser;
import io.github.hbothra.simplebugtracker.ro.SimpleUserRo;

@Mapper(componentModel = "spring", uses = { AuditTrailMapper.class, UserRoleMapper.class })
public interface SimpleUserMapper extends BaseMapper<SimpleUser, SimpleUserRo> {

}
