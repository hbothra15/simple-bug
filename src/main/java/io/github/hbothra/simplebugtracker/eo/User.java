package io.github.hbothra.simplebugtracker.eo;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name="USER")
@JsonIgnoreProperties(value = {"addressLine1", "addressLine2", "addressLine3"}, allowGetters = true)
public class User extends AuditTrail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name="USER_ID", updatable = false, insertable = false)
	private Long userId;
	
	@Column(name="USER_NAME", nullable = false)
	private String name;
	
	@Column(name="ADDRESS_LINE_1", nullable = false, length = 50)
	private String addressLine1;
	
	@Column(name="ADDRESS_LINE_2", nullable = true, length = 50)
	private String addressLine2;
	
	@Column(name="ADDRESS_LINE_3", nullable = true, length = 100)
	private String addressLine3;
	
	@Column(name="CONTACT", nullable = false, length = 20, unique = true)
	private String contact;
	
	@Column(name="EMAIL", nullable = true, length = 50)
	private String email;
	
	@Column(name="PASSWD", nullable = false)
	private String password;
	
	@Transient
    private String passwordConfirm;
	
	@ManyToMany
	@JoinTable(name="USER_ROLE", joinColumns = @JoinColumn(name="USER_ID"),
		inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<UserRole> roles;
}
