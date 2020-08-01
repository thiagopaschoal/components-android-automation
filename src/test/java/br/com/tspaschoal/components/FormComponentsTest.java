package br.com.tspaschoal.components;

import br.com.tspaschoal.components.capabilities.Capabilities;
import br.com.tspaschoal.components.utils.Utils;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class FormComponentsTest {

    private AndroidDriver<MobileElement> driver;

    @Before
    public void setUp() throws IOException {
        final DesiredCapabilities desiredCapabilites = new Capabilities().getDesiredCapabilites();
        URL remoteUrl = new URL(Utils.loadProperties().getProperty("remote_url"));
        driver = new AndroidDriver(remoteUrl, desiredCapabilites);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Formulário']")).click();
    }

    @Test
    public void shouldCheckThePageTitleIsCTAppium() {
        final String expected = "CT Appium";
        final MobileElement titleLabel = driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='CT Appium']"));
        assertEquals(expected, titleLabel.getText());
    }

    @Test
    public void shouldFillThiagoPaschoalOnInputName() {
        final String expected = "Thiago Paschoal";
        final MobileElement inputName = driver.findElement(MobileBy.AccessibilityId("nome"));
        inputName.sendKeys(expected);
        assertEquals(expected, inputName.getText());
    }

    @Test
    public void shouldCheckWithPS4OptionWasSelectedInSelectOneMenu() {
        final String expected = "PS4";
        driver.findElement(MobileBy.xpath("//android.widget.Spinner")).click();
        driver.findElement(MobileBy.xpath("//android.widget.CheckedTextView[@text='PS4']")).click();
        final MobileElement optionSelected = driver.findElement(MobileBy.xpath("//android.widget.Spinner/android.widget.TextView"));
        assertEquals(expected, optionSelected.getText());
    }

    @Test
    public void shouldCheckIfCheckBoxIsNotSelected() {
        final MobileElement checkBoxElement = driver.findElement(MobileBy.xpath("//android.widget.CheckBox"));
        assertTrue(!checkBoxElement.getAttribute("checked").equals("true"));
    }

    @Test
    public void shouldCheckIfSwitchIsSelected() {
        final MobileElement switchElement = driver.findElement(MobileBy.xpath("//android.widget.Switch"));
        assertTrue(switchElement.getAttribute("checked").equals("true"));
    }

    @Test
    public void shouldCheckIfCheckBoxIsSelectedAfterClick() {
        final MobileElement checkBoxElement = driver.findElement(MobileBy.xpath("//android.widget.CheckBox"));
        checkBoxElement.click();
        assertTrue(checkBoxElement.getAttribute("checked").equals("true"));
    }

    @Test
    public void shouldCheckIfSwitchIsNotSelectedAfterClick() {
        final MobileElement switchElement = driver.findElement(MobileBy.xpath("//android.widget.Switch"));
        switchElement.click();
        assertTrue(!switchElement.getAttribute("checked").equals("true"));
    }

    @Test
    public void shouldCheckIfTheValuesWereRegisteredWithSuccess() {

        final String expected = "Thiago Paschoal";
        final String optionSelectedExpected = "PS4";

        driver.findElement(MobileBy.AccessibilityId("nome")).sendKeys(expected);
        driver.findElement(MobileBy.xpath("//android.widget.Spinner")).click();
        driver.findElement(MobileBy.xpath("//android.widget.CheckedTextView[@text='" + optionSelectedExpected + "']")).click();
        driver.findElement(MobileBy.xpath("//android.widget.CheckBox")).click();
        driver.findElement(MobileBy.xpath("//android.widget.Switch")).click();
        driver.findElement(MobileBy.xpath("//*[@text='SALVAR']")).click();

        final String name = driver.findElement(By.xpath("//android.widget.TextView[starts-with(@text, 'Nome:')]")).getText();
        assertEquals("Nome: Thiago Paschoal", name);

        final String optionSelected = driver.findElement(By.xpath("//android.widget.TextView[starts-with(@text, 'Console:')]")).getText();
        assertEquals("Console: ps4", optionSelected);

        final String slider = driver.findElement(By.xpath("//android.widget.TextView[starts-with(@text, 'Slider:')]")).getText();
        assertEquals("Slider: 25", slider);

        final String switchElement = driver.findElement(By.xpath("//android.widget.TextView[starts-with(@text, 'Switch:')]")).getText();
        assertEquals("Switch: Off", switchElement);

        final String checkBoxElement = driver.findElement(By.xpath("//android.widget.TextView[starts-with(@text, 'Checkbox:')]")).getText();
        assertEquals("Checkbox: Marcado", checkBoxElement);

        final String data = driver.findElement(By.xpath("//android.widget.TextView[starts-with(@text, 'Data:')]")).getText();
        assertEquals("Data: 01/01/2000", data);

        final String hora = driver.findElement(By.xpath("//android.widget.TextView[starts-with(@text, 'Hora:')]")).getText();
        assertEquals("Hora: 06:00", hora);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
