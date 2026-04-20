package TestCases;

import DataProvider.DataDrivenProcess;
import ExcelUtilPack.ExcelUtil;

import Listner.MyListen;
import PageObjects.HomePage;
import PageObjects.RegisterPage;
import RandomInput.RandomValue;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;


@Listeners(MyListen.class)
public class TestCase extends BaseTest {

    public  int rowIndex = 1;
    public static String sheetName = "Sheet1";
    public static String excelPath;
    public static SoftAssert softAssert;
    public String actualMessage=null;



    @DataProvider(name = "nameList")
    public Object[][] getObjectArray() throws IOException {
        excelPath = prop.getProperty("excelPath");
        return DataDrivenProcess.getExcelData(excelPath, sheetName);
    }

    @Test(dataProvider = "nameList")
    public void runTest(String firstName, String lastName,
                        String phoneNo, String pass, String confirmPass, String expectedMessage) throws IOException {

        softAssert = new SoftAssert();


        int correctPass = Integer.parseInt(pass);
        int confirmPassword = Integer.parseInt(confirmPass);

        try {
            driver.navigate().to(prop.getProperty("url"));
            driver.manage().deleteAllCookies();
            driver.navigate().refresh();

            logger.info("Staring of all files ");

            HomePage page = new HomePage(driver);
            page.myAccount();
            page.register();

            logger.info("Now click the refister button");
            RegisterPage rePage = new RegisterPage(driver);
            softAssert.assertTrue(rePage.isValidDestination(firstName));
            if(!rePage.isValidDestination(firstName)){
                ExcelUtil.setCellData(excelPath, sheetName, rowIndex, 6, "Invalid name"+firstName);
                ExcelUtil.setCellData(excelPath, sheetName, rowIndex, 7, "Fail");
                ExcelUtil.setRedColor(excelPath, sheetName, rowIndex, 7);
            }
            Assert.assertTrue(rePage.isValidDestination(firstName),"Hard assert is used "+firstName);

            rePage.enterFirstName(firstName);
            rePage.enterLastName(lastName);
            rePage.enterEmail(RandomValue.getAllAlphabetic()+"@gmail.com");
            rePage.enterTelephone(phoneNo);
            logger.info("now the filling of all files");

            rePage.enterPassword(correctPass);
            rePage.enterConfirmPassword(confirmPassword);
            rePage.privacy();
            rePage.clickSubmit();


            if(!pass.equalsIgnoreCase(confirmPass)){
                ExcelUtil.setCellData(excelPath, sheetName, rowIndex, 6,pass+" doesn't match "+confirmPass );
                ExcelUtil.setCellData(excelPath, sheetName, rowIndex, 7, "Fail");
                ExcelUtil.setRedColor(excelPath, sheetName, rowIndex, 7);
            }

            Assert.assertEquals(pass,confirmPass);


            actualMessage = rePage.getSuccessMessage();

            logger.info("Checking the actual and expected ");

            ExcelUtil.setCellData(excelPath, sheetName, rowIndex, 6, actualMessage);


            if (actualMessage.equalsIgnoreCase(expectedMessage)) {
                ExcelUtil.setCellData(excelPath, sheetName, rowIndex, 7, "PASS");
                ExcelUtil.setGreenColor(excelPath, sheetName, rowIndex, 7);


                page.myAccount();
                page.logOut();
            } else {
                Assert.assertEquals(actualMessage, expectedMessage,"Results is : "+actualMessage);
                ExcelUtil.setCellData(excelPath, sheetName, rowIndex, 7, "FAIL");
                ExcelUtil.setRedColor(excelPath, sheetName, rowIndex, 7);
            }

            softAssert.assertAll();


        } catch (Exception e) {
            logger.error("the test case failed ");
            logger.debug("Debug logs....");
            ExcelUtil.setCellData(excelPath, sheetName, rowIndex, 6, actualMessage);
            ExcelUtil.setCellData(excelPath, sheetName, rowIndex, 7, "FAIL");
            ExcelUtil.setRedColor(excelPath, sheetName, rowIndex, 7);
            Assert.fail();
        } finally {
            rowIndex++;
        }
    }
}
