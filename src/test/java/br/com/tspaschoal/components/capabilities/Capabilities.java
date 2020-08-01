package br.com.tspaschoal.components.capabilities;

import br.com.tspaschoal.components.utils.Utils;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Properties;

public class Capabilities {

    public DesiredCapabilities getDesiredCapabilites() {
        final Properties bundle = Utils.loadProperties();
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, bundle.getProperty("platformName"));
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, bundle.getProperty("deviceName"));
        desiredCapabilities.setCapability(MobileCapabilityType.APP, Utils.getFullPath() + bundle.getProperty("app"));
        return desiredCapabilities;
    }

}
