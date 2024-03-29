package io.github.hbothra.simplebugtracker.eo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import io.github.hbothra.simplebugtracker.eo.listener.BugsEOListener;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "BUGS")
@EntityListeners(BugsEOListener.class)
public class Bugs extends BugBase {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "BUG_ID", nullable = false, updatable = false, insertable = false)
	private Long bugId;

	@Column(name = "PROJECT", nullable = false, updatable = false)
	private Long project;

	@OneToMany(mappedBy = "bug", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	private List<BugsComments> comments = new ArrayList<>();
	
	public void addComment(BugsComments comment) {
        comments.add(comment);
        comment.setBug(this);
    }
 
    public void removeComment(BugsComments comment) {
        comments.remove(comment);
        comment.setBug(null);
    }
}
