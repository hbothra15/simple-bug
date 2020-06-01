package io.github.hbothra.simplebugtracker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import io.github.hbothra.simplebugtracker.eo.UserType;
import io.github.hbothra.simplebugtracker.ro.UsersRoleRo;

@Mapper(componentModel = "spring")
public interface UserRoleMapper extends BaseMapper<UsersRoleRo, UserType> {

	@Override
	@Mapping(source = "lookupCode", target = "roleId")
	@Mapping(source = "lookupValue", target = "role")
	UsersRoleRo destinationToSource(UserType destination);

	@Override
	@Mapping(target = "lookupCode", source = "roleId")
	@Mapping(target = "lookupValue", source = "role")
	UserType sourceToDestination(UsersRoleRo source);
}
