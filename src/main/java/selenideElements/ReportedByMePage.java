package selenideElements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

import filesUtils.CreateFile;
import filesUtils.ReadFile;
import filesUtils.ToJson;

public class ReportedByMePage{

    private ReadFile readFile;
    private CreateFile createFile;
    private ToJson toJson;

    // Titles
    private SelenideElement reportedByMeTitle = $(".search-title");
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
    private SelenideElement issueAllCommentsButton = $("#comment-tabpanel > a");
    private SelenideElement issueTrashButtonToDeleteComment = $(".aui-iconfont-delete");
    private SelenideElement issueCheckDeletePanel = $(".aui-message");
    private SelenideElement issueCommentChangeToText = $(".aui-navgroup-primary .aui-nav-selected > a");

    // Methods
    public String checkTitle(){
        return reportedByMeTitle.waitUntil(Condition.visible, 10000).getText();
    }

    public void clickEditButton(){
        issueEditButton.click();
    }

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
        String[] getIssueText = key_issue.split(" ");
        setKeyIssueUI(getIssueText[0]);
    }

    private void setKeyIssueUI(String keyIssueUI){
        createFile = new CreateFile();
        createFile.checkFile(keyIssueUI, "IssueKey.txt");
    }

    public SelenideElement checkAddedComment(){
        return commentAdd;
    }

    public void getIssueUI(String nameFile){
        toJson = new ToJson();

        toJson.serialize(issueKey.getText(), issueSummary.getText(), issueReporter.getText(), issueType.getText(),
                issuePriority.getText(), issueResolution.getText(), issueWasCreated.getText(), issueWasCreated.getText(), nameFile);

    }

    public void clickIssueAllCommentButton(){
        issueAllCommentsButton.click();
    }

    public void clickIssueTrashButtonToDeleteComment(){
        // Сначала наводимся на элемент мышью для появления
        issueTrashButtonToDeleteComment.hover();
        //executeJavaScript(
        //        ".aui-iconfont-delete.hidden = false;"
        //);
        // Кликаем на элемент
        issueTrashButtonToDeleteComment.click();
    }

    public String checkIssueDeletePanel(){
        return issueCheckDeletePanel.getText();
    }
}