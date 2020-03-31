package selenideElements;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import filesUtils.CreateFile;
import filesUtils.ReadFile;

public class ReportedByMePage{

    private ReadFile readFile;
    private CreateFile createFile;

    // Titles
    private SelenideElement reportedByMe = $(".search-title");
    private SelenideElement issueEditButton = $("#edit-issue > .trigger-label");
    private SelenideElement issueKey = $("#key-val");
    private SelenideElement issueSummary = $("#summary-val");
    private SelenideElement issueMoreButton = $("#opsbar-operations_more > .dropdown-text");
    private SelenideElement issueDeleteButton = $("#delete-issue > a");
    private SelenideElement issueCommentButton = $("#comment-issue > .trigger-label");
    private SelenideElement issueCommentField = $("#comment");
    private SelenideElement issueCommentAddButton = $("#issue-comment-add-submit");
    private SelenideElement issueAddedPanel = $(".issue-created-key");
    private SelenideElement commentAdd = $(".action-body > p");
    private SelenideElement issueReporter = $("#reporter-val span");
    private SelenideElement issueType = $("#type-val");
    private SelenideElement issuePriority = $("#priority-val");
    private SelenideElement issueResolution = $("#resolution-val");
    private SelenideElement issueWasCreated = $("#created-val > .livestamp");
    private SelenideElement issueWasUpdate = $("#updated-val > .livestamp");
    private SelenideElement issueCountComments = $(".show-more-comments");
    private SelenideElement issueCommentChangeToText = $(".aui-navgroup-primary .aui-nav-selected > a");

    // Methods
    public void checkTitle(String title){
        reportedByMe.waitUntil(visible, 10000).shouldBe(exactText(title));
    }

    public void clickEditButton(){
        issueEditButton.click();
    }

    public String getIssueKey(){  return issueKey.getText();}

    public void clickToButtonMore(){
        issueMoreButton.click();
    }

    public void clickToDeleteButton(){
        issueDeleteButton.scrollIntoView(false);
        issueDeleteButton.click();
    }

    public void clickToIssueById(){
        readFile = new ReadFile();
        String pathToFileIssueUI = "src/main/resources/response/keyIssueUI.txt";
        $$(".issue-link-key").findBy(text(readFile.readFile(pathToFileIssueUI))).click();
    }

    public void clickToCreatedIssueById(){
        readFile = new ReadFile();
        String pathToFileTargetUI = "target/TestsFiles/IssueKey.txt";
        $$(".issue-content-container span").findBy(text(readFile.readFile(pathToFileTargetUI))).click();
    }

    public void clickButtonComment(){
        issueCommentButton.click();
    }

    public void setIssueCommentField(String text){
        // нажимаем на вкладку Text (в добавлении комментария)
        issueCommentChangeToText.click();
        issueCommentField.setValue(text);
    }

    public void addCommentButton(){
        issueCommentAddButton.click();
    }

    public void checkIssueCreated(){
        issueAddedPanel.waitUntil(visible, 10000).isEnabled();
        String key_issue = issueAddedPanel.getText();
        setKeyIssueUI(key_issue.substring(0, 4));
    }

    private void setKeyIssueUI(String keyIssueUI){
        createFile = new CreateFile();
        createFile.checkFile(keyIssueUI, "IssueKey.txt");
    }

    public void checkAddedComment(String comment){
        //commentAdd.shouldBe(visible);
        commentAdd.shouldBe(exactText(comment));
    }

    public void getIssueUI(String nameFile){
        String issueInfo = "Issue key: " + issueKey.getText() + "\n "
                + "Issue summary: " + issueSummary.getText() + "\n "
                + "Issue reporter: " + issueReporter.getText() + "\n "
                + "Issue type: " + issueType.getText() + "\n "
                + "Issue Priority: " + issuePriority.getText() + "\n "
                + "Issue resolution: " + issueResolution.getText() + "\n "
                + "Issue created: " + issueWasCreated.getText() + "\n "
                + "Issue was updated: " + issueWasUpdate.getText() + "\n ";
        setGetIssueUI(issueInfo, nameFile);
    }

    private void setGetIssueUI(String issueInfo, String nameFile){
        createFile = new CreateFile();
        createFile.checkFile(issueInfo, nameFile);
    }
}