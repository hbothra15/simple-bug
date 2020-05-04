package io.github.hbothra.simplebugtracker.eo;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@MappedSuperclass
public class BaseType<T> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name="TYPE_ID", nullable = false, updatable = false, insertable = false)
	private Long id;
	
	@Column(name="TYPE", nullable = false)
	@Enumerated(EnumType.STRING)
	private T type;
}
