package io.github.hbothra.simplebugtracker.eo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name="BUGS_COMMENTS")
public class BugsComments extends AuditTrail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name="COMMENT_ID", nullable = false, updatable = false, insertable = false)
	@EqualsAndHashCode.Include
	private Long commentId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Bugs bug;
	
	@Column(name="BUG_ID", nullable = false, insertable = false, updatable = false)
	private Long bugId;
	
	@Column(name="COMMENTS", nullable = false, length = 1000)
	private String comments;

}
