package com.qa.flipkart.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.flipkart.pages.HomePage;
import com.qa.flipkart.pages.LoginPage;
import com.qa.flipkart.pages.MyProfilePage;
import com.qa.flipkart.testbase.TestBase;
import com.qa.flipkart.testiutil.TestUtil;

import constantsUtil.Constants;

public class MyProfilePageTest extends TestBase {

	HomePage homePage;
	LoginPage loginPage;
	MyProfilePage myProfilePage;

	public MyProfilePageTest() {
		// TODO Auto-generated constructor stub

		super();
	}

	@Parameters("Browser1")
	@BeforeMethod
	public void setUp(String browser) {
		TestBase.initialize(browser);
		TestBase.launchUrl();
		homePage = new HomePage();
		loginPage = new LoginPage();
		myProfilePage = new MyProfilePage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		TestUtil.waitForPageLoad(driver);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		homePage.doGetMenuBarList(0);
		homePage.clickOnMyAccounts_MyProfile();

	}

	@Test
	public void verifyMyProfilePageTitleTest() {

		driver.navigate().to(Constants.MYPROFILE_PAGE_URL);
		System.out.println();
		Assert.assertEquals(driver.getTitle(), Constants.MYPROFILE_PAGE_TITLE, "My Profile Page title not verified");

	}

	@Test
	public void verifyPersonalInfoclickOnEdit() {
		driver.navigate().to(Constants.MYPROFILE_PAGE_URL);
		System.out.println();

		boolean isClicked = myProfilePage.clickOnPersonalInfoEdit();
		Assert.assertTrue(isClicked, "Not clicked on edit link");
	}

	@Test
	public void enterFirstNameTest() {

		driver.navigate().to(Constants.MYPROFILE_PAGE_URL);

		boolean isClicked = myProfilePage.clickOnPersonalInfoEdit();
		Assert.assertTrue(isClicked, "Not clicked on edit link");

		boolean isEntered = myProfilePage.enterFirstNamePersonalInfo("Shobhit");
		Assert.assertTrue(isEntered, "First Name not entered");

		myProfilePage.clickOnCancelLnkPersonalInfo();
	}

	@Test
	public void enterLastNameTest() {

		driver.navigate().to(Constants.MYPROFILE_PAGE_URL);

		boolean isClicked = myProfilePage.clickOnPersonalInfoEdit();
		Assert.assertTrue(isClicked, "Not clicked on edit link");

		boolean isEntered = myProfilePage.enterLastNamePersonalInfo("-");
		Assert.assertTrue(isEntered, "Last Name is not entered");
		myProfilePage.clickOnCancelLnkPersonalInfo();
	}

	@Test
	public void selectRadioBtnTest_Male() {
		driver.navigate().to(Constants.MYPROFILE_PAGE_URL);

		boolean isClicked = myProfilePage.clickOnPersonalInfoEdit();
		Assert.assertTrue(isClicked, "Not clicked on edit link");

		boolean isSelected = myProfilePage.selectRadioBtnGender("Male");
		Assert.assertTrue(isSelected, "Male radio button not seleted");

		myProfilePage.clickOnCancelLnkPersonalInfo();

	}

	@Test
	public void selectRadioBtnTest_Female() {
		driver.navigate().to(Constants.MYPROFILE_PAGE_URL);

		boolean isClicked = myProfilePage.clickOnPersonalInfoEdit();
		Assert.assertTrue(isClicked, "Not clicked on edit link");

		boolean isSelected = myProfilePage.selectRadioBtnGender("Female");
		Assert.assertTrue(isSelected, "Female radio button not seleted");

		myProfilePage.clickOnCancelLnkPersonalInfo();

	}

	@Test
	public void verifyClickOnEditEmailAddLnk() {

		driver.navigate().to(Constants.MYPROFILE_PAGE_URL);

		boolean isDone = myProfilePage.clickOnEditEmailAddLnk(1);
		Assert.assertTrue(isDone, "Email add edit clicked and values in text box is entered");

		myProfilePage.clickOnCancelLnkEmailAdd();

	}

	@AfterMethod
	public void tearDown() {
		TestBase.close();
	}

}
