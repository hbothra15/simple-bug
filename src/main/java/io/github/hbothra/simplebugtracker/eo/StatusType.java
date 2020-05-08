package io.github.hbothra.simplebugtracker.eo;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="STATUS_TYPE")
@AttributeOverride(name="type", column = @Column(name="STATUS_TYPE", nullable = false))
public class StatusType extends BaseType<Status> {

}
