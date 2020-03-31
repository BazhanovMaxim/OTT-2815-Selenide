package selenideElements;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class NavigationPanel {

    // Кнопка создания Issue
    private SelenideElement createButton = $("#create_link");
    // Issues
    private SelenideElement issueNavigationLink = $("#find_link");
    // Reported by me
    private SelenideElement reporterByMeLink = $("#filter_lnk_reported_lnk");

    // profile links
    private SelenideElement profileLink = $("#header-details-user-fullname > span > span > img");
    private SelenideElement logOutButton = $("#log_out");

    // Methods
    public void clickCreateButton(){
        createButton.click();
    }
    public void clickIssueNavigation(){
        issueNavigationLink.click();
    }
    public void clickReporterLink(){
        reporterByMeLink.click();
    }
    public void clickProfileLink (){
        profileLink.click();
    }

    public void clickLogOutButton(){
        logOutButton.click();
    }

}
