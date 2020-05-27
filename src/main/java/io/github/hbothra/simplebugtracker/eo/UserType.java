package io.github.hbothra.simplebugtracker.eo;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(UserType.DISCRIMINATOR_VALUE)
public class UserType extends Lookup implements Serializable {
	private static final long serialVersionUID = 8630715121706546152L;
	public static final String DISCRIMINATOR_VALUE="USER_TYPE";
}
