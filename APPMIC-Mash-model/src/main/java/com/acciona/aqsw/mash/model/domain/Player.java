package com.acciona.aqsw.mash.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class Player.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PLAYER")
public class Player {

	@Id
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "AGE", nullable = false)
	private Integer age;

	@Column(name = "NUMBER", nullable = false)
	private Long number;

	/**
	 * To string.
	 *
	 * @return the string
	 */
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
