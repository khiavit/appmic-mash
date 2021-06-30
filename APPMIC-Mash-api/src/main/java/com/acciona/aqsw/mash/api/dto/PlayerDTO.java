package com.acciona.aqsw.mash.api.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5877836792102357129L;

	private Long idPlayer;

	private String name;

	private Integer age;

	private Long number;

	@Override
	public String toString() {
		final StringBuilder strBldr = new StringBuilder(String.valueOf(number));
		strBldr.append(" - ");
		strBldr.append(name);
		strBldr.append(" (");
		strBldr.append(age);
		strBldr.append(")");
		return strBldr.toString();
	}

}
