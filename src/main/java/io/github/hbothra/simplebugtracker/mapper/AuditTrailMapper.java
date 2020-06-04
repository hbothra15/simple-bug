package io.github.hbothra.simplebugtracker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import io.github.hbothra.simplebugtracker.eo.AuditTrail;
import io.github.hbothra.simplebugtracker.ro.AuditTrailRo;

@Mapper(componentModel = "spring", uses = { SimpleUserMapper.class })
public interface AuditTrailMapper extends BaseMapper<AuditTrailRo, AuditTrail> {

	@Override
	@Mapping(source = "modifiedBy", target = "modifiedBy", ignore = true)
	@Mapping(source = "createdBy", target = "createdBy", ignore = true)
	AuditTrailRo destinationToSource(AuditTrail destination);
}
