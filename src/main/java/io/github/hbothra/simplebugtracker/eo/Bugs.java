package io.github.hbothra.simplebugtracker.eo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.github.hbothra.simplebugtracker.eo.listener.BugsEOListener;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="BUGS")
@EntityListeners(BugsEOListener.class)
@JsonIgnoreProperties(value = {"createdOn"}, allowGetters = true)
public class Bugs extends BugBase{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name="BUG_ID", nullable = false, updatable = false, insertable = false)
	private Long bugId;
	
	@JoinColumn(name="CREATED_BY", nullable = false, updatable = false)
	@ManyToOne
	private User craetedBy;
	
	@Column(name="CREATED_ON", nullable = false, updatable = false)
	private LocalDateTime createdOn;
	
	@Column(name="PROJECT", nullable = false, updatable = false)
	private Long project;
}
