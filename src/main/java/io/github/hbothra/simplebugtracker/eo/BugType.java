package io.github.hbothra.simplebugtracker.eo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(BugType.DISCRIMINATOR_VALUE)
public class BugType extends Lookup {
	public static final String DISCRIMINATOR_VALUE = "BUG_TYPE";
}
