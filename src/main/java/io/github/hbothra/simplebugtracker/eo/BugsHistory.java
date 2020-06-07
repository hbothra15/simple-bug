package io.github.hbothra.simplebugtracker.eo;

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
@Table(name="H$BUGS")
public class BugsHistory extends BugBase {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name="HISTORY_ID", nullable = false, updatable = false, insertable = false)
	private Long bugHistoryId;
	
	@Column(name="BUG_ID", nullable = false, updatable = false)
	private Long bugId;
		
	public BugsHistory() {
		// Default, as we need to have another one as well
	}
	
	public BugsHistory(BugBase base, Long bugId) {
		this.setBugId(bugId);
		this.setBugStatus(base.getBugStatus());
		this.setBugType(base.getBugType());
		this.setDescr(base.getDescr());
		this.setModifiedBy(base.getModifiedBy());
		this.setModifiedOn(base.getModifiedOn());
		this.setTitle(base.getTitle());
		this.setAssignedTo(base.getAssignedTo());
		this.setAssignedToId(base.getAssignedToId());
		this.setCreatedBy(base.getCreatedBy());
		this.setCreatedById(base.getCreatedById());
		this.setVersion(base.getVersion());
	}
}
