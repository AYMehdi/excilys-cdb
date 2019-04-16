package main.java.com.excilys.validators;

import java.util.Optional;

public class GenericValidationResult {
	
	// ******* VARIABLES *******
	private boolean valid;

	public boolean isValid() {
		return this.valid;
	}
	// ******* CONSTRUCTOR *******
	private GenericValidationResult(boolean bool) {
		this.valid = bool;
	}

	public static GenericValidationResult ok() {
		return new GenericValidationResult(true);
	}

	public static GenericValidationResult fail() {
		return new GenericValidationResult(false);
	}

	// ******* OTHER METHOD *******
	public Optional<String> getFieldNameIfInvalid(String field) {
		return this.valid ? Optional.empty() : Optional.of(field);
	}
}
