package com.office.visits.model.enums;

public enum PersonRole {

	CUSTOMER(Values.CUSTOMER), EMPLOYEE(Values.EMPLOYEE);

	private PersonRole(String value) {
		if (!this.name().equals(value)) {
			throw new IllegalArgumentException("Incorrect use of PersonRole");
		}
	}

	public static class Values {
		public static final String CUSTOMER = "CUSTOMER";
		public static final String EMPLOYEE = "EMPLOYEE";
	}

}
