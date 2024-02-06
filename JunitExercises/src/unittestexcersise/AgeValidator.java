package unittestexcersise;

public class AgeValidator {

	public static String validate(int age) {

		if (age >= 0 && age <= 17)

		{
			return "minor";
		} else if (age >= 18 && age <= 120)

		{
			return "adult";
		} else

		{
			return "invalid age";
		}
	}

}
