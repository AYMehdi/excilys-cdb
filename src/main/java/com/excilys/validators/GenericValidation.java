package main.java.com.excilys.validators;

import java.util.function.Predicate;

public class GenericValidation<T> {

	// ******* VARIABLES *******
	private Predicate<T> predicate;

	// ******* CONSTRUCTOR *******
	private GenericValidation(Predicate<T> predicate) {
		this.predicate = predicate;
	}

	// ******* METHODS *******
	public static <T> GenericValidation<T> from(Predicate<T> predicate) {
		return new GenericValidation<T>(predicate);
	}

	public GenericValidation<T> and(GenericValidation<T> other) {
		return GenericValidation.from((T param) -> {
			return this.predicate.test(param) && other.predicate.test(param);
		});
	}

	public GenericValidationResult test(T param) {
		return predicate.test(param) ? GenericValidationResult.ok() : GenericValidationResult.fail();
	}
}
