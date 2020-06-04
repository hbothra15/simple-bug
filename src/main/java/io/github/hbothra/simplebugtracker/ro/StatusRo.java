package io.github.hbothra.simplebugtracker.ro;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Data;

@Data
@JsonAutoDetect
public class StatusRo {

	private Long statusId;
	private String status;

}
