/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */


/**
 *
 * @author Donnell
 */
import org.junit.Test;
import static org.junit.Assert.*;

public class LoginPart2Test {

    // Test for valid username
    @Test
    public void testCheckUsername_Valid() {
        assertTrue(LoginPart2.checkUsername("ab_c"));
    }

    // Test for invalid username (too long)
    @Test
    public void testCheckUsername_TooLong() {
        assertFalse(LoginPart2.checkUsername("abcdef_"));
    }

    // Test for invalid username (no underscore)
    @Test
    public void testCheckUsername_NoUnderscore() {
        assertFalse(LoginPart2.checkUsername("abcde"));
    }

    // Test valid password
    @Test
    public void testCheckPasswordComplexity_Valid() {
        assertTrue(LoginPart2.checkPasswordComplexity("Strong1@3"));
    }

    // Test password missing uppercase
    @Test
    public void testCheckPasswordComplexity_NoUppercase() {
        assertFalse(LoginPart2.checkPasswordComplexity("strong1@3"));
    }

    // Test password missing digit
    @Test
    public void testCheckPasswordComplexity_NoDigit() {
        assertFalse(LoginPart2.checkPasswordComplexity("Strong@abc"));
    }

    // Test password missing special character
    @Test
    public void testCheckPasswordComplexity_NoSpecialCharacter() {
        assertFalse(LoginPart2.checkPasswordComplexity("Strong123"));
    }

    // Test password too short
    @Test
    public void testCheckPasswordComplexity_TooShort() {
        assertFalse(LoginPart2.checkPasswordComplexity("S1@a"));
    }

    // Test valid phone number
    @Test
    public void testCheckCellNumber_Valid() {
        assertTrue(LoginPart2.checkCellNumber("+27831234567"));
    }

    // Test invalid phone number (missing +27)
    @Test
    public void testCheckCellNumber_MissingCode() {
        assertFalse(LoginPart2.checkCellNumber("0831234567"));
    }

    // Test invalid phone number (too long)
    @Test
    public void testCheckCellNumber_TooLong() {
        assertFalse(LoginPart2.checkCellNumber("+27831234567890"));
    }

    // Test login success
    @Test
    public void testLoginUser_Success() {
        assertTrue(LoginPart2.loginUser("user_1", "Pass123@", "user_1", "Pass123@"));
    }

    // Test login fail
    @Test
    public void testLoginUser_Fail() {
        assertFalse(LoginPart2.loginUser("user_1", "Pass123@", "user_2", "Wrong123@"));
    }

    // Test registerUser success
    @Test
    public void testRegisterUser_Success() {
        String result = LoginPart2.registerUser("user_", "Pass123@");
        assertEquals("Registration is a success", result);
    }

    // Test registerUser fail - both username and password
    @Test
    public void testRegisterUser_Fail_Both() {
        String result = LoginPart2.registerUser("user", "pass");
        assertTrue(result.toLowerCase().contains("username"));
    }
}
