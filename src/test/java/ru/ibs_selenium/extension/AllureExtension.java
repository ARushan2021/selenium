package ru.ibs_selenium.extension;

import io.qameta.allure.Attachment;
import io.qameta.allure.model.TestResult;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.w3c.dom.html.HTMLInputElement;
import ru.appline.project.DriverManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AllureExtension implements TestWatcher, AfterAllCallback {

    private List<TestResultStatus> testResultStatus = new ArrayList<>();

    private enum TestResultStatus{SUCCESSFUL, ABORTED, FAILED, DISABLED;}

    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {

    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        TestWatcher.super.testDisabled(context, reason);
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        TestWatcher.super.testSuccessful(context);
        takeScreenshot();
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        TestWatcher.super.testAborted(context, cause);
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        takeScreenshot();
        testResultStatus.add(TestResultStatus.FAILED);
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] takeScreenshot() {
        return ((TakesScreenshot) DriverManager.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
