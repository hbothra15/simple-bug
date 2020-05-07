package io.github.hbothra.simplebugtracker.eo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name="BUGS_COMMENTS")
public class BugsComments {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name="COMMENT_ID", nullable = false, updatable = false, insertable = false)
	@EqualsAndHashCode.Include
	private Long commentId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Bugs bug;
	
	@JoinColumn(name="CREATED_BY", nullable = false, updatable = false)
	@ManyToOne
	private User craetedBy;
	
	@Column(name="CREATED_ON", nullable = false)
	private LocalDateTime createdOn;
	
	@Column(name="MODIFIED_ON", nullable = false)
	private LocalDateTime modifiedOn;
	
	@Column(name="COMMENTS", nullable = false, length = 1000)
	private String comments;

}
