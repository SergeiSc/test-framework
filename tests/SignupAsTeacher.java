package tests;

import org.testng.annotations.Test;

import pages.Pages;
import testSetup.TestSetup;

public class SignupAsTeacher extends TestSetup {

	@Test
	public void canSignupAsTeacher() {
		Pages.home().signup();
	}

}
