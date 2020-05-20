package io.github.hbothra.simplebugtracker.eo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
@JsonAutoDetect
public class BaseType extends AuditTrail{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name="TYPE_ID", nullable = false, updatable = false, insertable = false)
	private Long id;
	
	@Column(name="TYPE", nullable = false)
	private String type;
}
