package Listner;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyListen implements ITestListener{
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("this is ruuning ");
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("the test start");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("the test  fail..... ");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("this is skippped");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("the test successs....");
    }
}
