package io.github.hbothra.simplebugtracker.eo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(StatusType.DISCRIMINATOR_VALUE)
public class StatusType extends Lookup {
	public static final String DISCRIMINATOR_VALUE="STATUS_TYPE";

}
