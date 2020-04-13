package json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IssueInfo {

    @SerializedName("Issue key")
    @Expose
    private String issueKey;
    @SerializedName("Issue summary")
    @Expose
    private String issueSummary;
    @SerializedName("Issue reporter")
    @Expose
    private String issueReporter;
    @SerializedName("Issue type")
    @Expose
    private String issueType;
    @SerializedName("Issue Priority")
    @Expose
    private String issuePriority;
    @SerializedName("Issue resolution")
    @Expose
    private String issueResolution;
    @SerializedName("Issue created")
    @Expose
    private String issueCreated;
    @SerializedName("Issue was updated")
    @Expose
    private String issueWasUpdated;

    public IssueInfo(String issueKey, String issueSummary, String issueReporter, String issueType, String issuePriority, String issueResolution, String issueCreated, String issueWasUpdated) {
        this.issueKey = issueKey;
        this.issueSummary = issueSummary;
        this.issueReporter = issueReporter;
        this.issueType = issueType;
        this.issuePriority = issuePriority;
        this.issueResolution = issueResolution;
        this.issueCreated = issueCreated;
        this.issueWasUpdated = issueWasUpdated;
    }
}
