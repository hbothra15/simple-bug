package io.github.hbothra.simplebugtracker.eo;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
@Table(name="SIMPLE_LKP")
@DiscriminatorColumn(name="LKP_TYPE", discriminatorType=DiscriminatorType.STRING, length=20)
public class Lookup extends AuditTrail{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name="LKP_CODE", nullable = false, updatable = false, insertable = false)
	private Long lookupCode;
	
	@Column(name="LKP_VALUE", nullable = false)
	private String lookupValue;
	
}
