package tests;

import org.testng.annotations.Test;

import pages.Pages;
import testSetup.TestSetup;

public class Login extends TestSetup {

	@Test
	public void canLogin() {
		Pages.home().login();
	}

}
