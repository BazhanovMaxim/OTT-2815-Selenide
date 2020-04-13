package filesUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import json.IssueInfo;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ToJson {

    public void serialize(String IssueKey, String issueSummary, String issueReporter, String issueType,
                          String issuePriority, String issueResolution, String issueCreated, String issueWasUpdated, String nameFile){

        IssueInfo issueInfo = new IssueInfo(IssueKey, issueSummary, issueReporter, issueType, issuePriority,
                issueResolution, issueCreated, issueWasUpdated);
        try {
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            String test = gson.toJson(issueInfo);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("target\\TestsFiles\\" + nameFile, true), StandardCharsets.UTF_8));
            writer.write(test);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
