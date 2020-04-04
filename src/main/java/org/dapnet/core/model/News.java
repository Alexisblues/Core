/*
 * DAPNET CORE PROJECT
 * Copyright (C) 2016
 *
 * Daniel Sialkowski
 *
 * daniel.sialkowski@rwth-aachen.de
 *
 * Institute of High Frequency Technology
 * RWTH AACHEN UNIVERSITY
 * Melatener Str. 25
 * 52074 Aachen
 */

package org.dapnet.core.model;

import java.time.Instant;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.dapnet.core.model.validator.ValidName;

public class News extends Entity {
	private static final long serialVersionUID = 1L;
	private static volatile State state;

	@NotNull
	@Size(min = 1, max = 80)
	private String text;

	@NotNull
	private String rubricName;

	@NotNull
	@Min(value = 0)
	@Max(value = 10)
	private int number;

	// Internally set
	@NotNull
	private Instant timestamp;

	// Internally set
	@NotNull
	@Size(min = 1, message = "must contain at least one ownerName")
	private String ownerName;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getRubricName() {
		return rubricName;
	}

	public void setRubricName(String rubricName) {
		this.rubricName = rubricName;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public static State getState() {
		return state;
	}

	public static void setState(State statePar) {
		state = statePar;
	}

	@ValidName(message = "must contain the name of an existing rubric", fieldName = "rubricName", constraintName = "ValidRubricName")
	public Rubric getRubric() throws Exception {
		if (state == null) {
			throw new Exception("StateNotSetException");
		}
		if (rubricName != null) {
			return state.getRubrics().get(rubricName.toLowerCase());
		} else {
			return null;
		}
	}

	@ValidName(message = "must contain the name of an existing user", fieldName = "ownerNames", constraintName = "ValidOwnerNames")
	public User getOwner() throws Exception {
		if (state == null) {
			throw new Exception("StateNotSetException");
		}
		if (ownerName != null) {
			return state.getUsers().get(ownerName.toLowerCase());
		} else {
			return null;
		}
	}

	@Override
	public String toString() {
		return String.format("News{rubricName='%s', number=%d}", rubricName, number);
	}
}
