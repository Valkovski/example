package applicationPages.pages;

import com.epam.training.framework.core.DriverWrapper;

public class SoftServeMain extends BasePage {
    public static final String URL_ADDRESS_FAIL = "http://softserve1.ua/";
    public static final String URL_ADDRESS = "http://softserve.ua/";

    public String getUrl() {
        return DriverWrapper.getDriver().getCurrentUrl();
    }
}