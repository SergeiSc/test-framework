package tests;

import org.testng.annotations.Test;

import pages.Pages;
import testSetup.TestSetup;

public class LeftNavigationTest extends TestSetup {
	
    @Test
	public void canGotoClasses() {
		Pages.dashboard().classes().goTo();
	}
	
	@Test
	public void canGotoAssignments() {
		Pages.dashboard().assignments().goTo();
	}
	
	@Test
	public void canGotoLibrary() {
		Pages.dashboard().library().goTo();
	}
	
	@Test
	public void canGotoTimetable() {
		Pages.dashboard().timetable().goTo();
	}
	
	@Test
	public void canGotoActivities() {
		Pages.dashboard().activities().goTo();
	}
	
	@Test
	public void canGotoDashboard() {
		Pages.dashboard().goTo();
	}
	
}
