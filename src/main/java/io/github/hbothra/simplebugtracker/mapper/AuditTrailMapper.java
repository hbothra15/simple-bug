package io.github.hbothra.simplebugtracker.mapper;

import org.mapstruct.Mapper;

import io.github.hbothra.simplebugtracker.eo.AuditTrail;
import io.github.hbothra.simplebugtracker.ro.AuditTrailRo;

@Mapper(componentModel = "spring")
public interface AuditTrailMapper extends BaseMapper<AuditTrailRo, AuditTrail> {

}
