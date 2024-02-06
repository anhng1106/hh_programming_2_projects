package agevalidatortest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unittestexcersise.AgeValidator;

class TestAgeValidator {

	@Test
	void testMinorAge() {
		assertEquals("minor", AgeValidator.validate(0));
		assertEquals("minor", AgeValidator.validate(17));
	}

	@Test
	void testAdultAge() {
		assertEquals("adult", AgeValidator.validate(18));
		assertEquals("adult", AgeValidator.validate(30));
		assertEquals("adult", AgeValidator.validate(120));
	}

	@Test
	void testInvalidAge() {
		assertEquals("invalid age", AgeValidator.validate(-5));
		assertEquals("invalid age", AgeValidator.validate(121));
	}

}
