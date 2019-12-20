package by.bsu.finalproject.validator;

import org.junit.Assert;
import org.junit.Test;

public class StudentValidatorTest {

    private String name = "Артем";
    private String secondName = "Kuzmik";
    private String weight = "133";
    private String height = "188";

    @Test
    public void isNameValidTest() {
        boolean isValidFats = StudentInformationValidator.INSTANCE.isValidName(name);
        Assert.assertTrue(isValidFats);

    }

    @Test
    public void isSecondNameValidTest() {
        boolean isValidDiet = StudentInformationValidator.INSTANCE.isValidName(secondName);
        Assert.assertTrue(isValidDiet);

    }

    @Test
    public void isHeightValidTest() {
        boolean isValidDiet = StudentInformationValidator.INSTANCE.isValidWeight(height);
        Assert.assertTrue(isValidDiet);
    }

    @Test
    public void isWeightValidTest() {
        boolean isValidDiet = StudentInformationValidator.INSTANCE.isValidHeight(weight);
        Assert.assertTrue(isValidDiet);
    }
}
