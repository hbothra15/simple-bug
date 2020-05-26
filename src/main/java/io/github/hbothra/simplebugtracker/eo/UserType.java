package io.github.hbothra.simplebugtracker.eo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(UserType.DISCRIMINATOR_VALUE)
public class UserType extends Lookup {
	public static final String DISCRIMINATOR_VALUE="USER_TYPE";
}
