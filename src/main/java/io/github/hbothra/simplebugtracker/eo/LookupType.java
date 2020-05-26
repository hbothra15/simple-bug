package io.github.hbothra.simplebugtracker.eo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum LookupType {
	USER_TYPE(UserType.DISCRIMINATOR_VALUE),
	BUG_TYPE(BugType.DISCRIMINATOR_VALUE),
	STATUS_TYPE(StatusType.DISCRIMINATOR_VALUE);
	
	@Getter
    private final String discriminatorValue;

}
