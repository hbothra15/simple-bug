package io.github.hbothra.simplebugtracker.eo;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="BUG_TYPE")
@AttributeOverride(name="TYPE", column = @Column(name="BUG_TYPE", nullable = false))
public class BugType extends BaseType<Bug> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name="TYPE_ID", nullable = false, updatable = false, insertable = false)
	private Long id;
}
