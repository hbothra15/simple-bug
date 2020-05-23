package io.github.hbothra.simplebugtracker.eo;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name="USER")
@NamedEntityGraphs({
	@NamedEntityGraph(name="User.role", attributeNodes = @NamedAttributeNode("roles"))
})
@JsonAutoDetect
public class User extends AuditTrail implements com.github.hbothra.user.entity.User {

	private static final long serialVersionUID = 3047826211552173830L;

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
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="USER_ROLE", joinColumns = @JoinColumn(name="USER_ID"),
		inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<UserRole> roles = new HashSet<>();

	public void addRole (UserRole role) {
		roles.add(role);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles.parallelStream()
				.map(role -> new SimpleGrantedAuthority("ROLE_" + role.getType().toUpperCase()))
				.collect(Collectors.toSet());
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	@Transient
	public String getUserToken() {
		return userId.toString();
	}
}
