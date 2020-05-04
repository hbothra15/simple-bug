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
@Table(name="USER_TYPE")
@AttributeOverride(name="TYPE", column = @Column(name="USER_TYPE", nullable = false))
public class UserType extends BaseType<UserRole>{

}
