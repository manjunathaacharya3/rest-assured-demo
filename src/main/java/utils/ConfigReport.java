package utils;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;

public class ConfigReport {

	public static void logJsonContentToReport(String info, Object content, boolean log) {
		String contentInJson = null;
		if (content instanceof String) {
			contentInJson = content.toString();
		} else {
			try {
				contentInJson = new ObjectMapper().writeValueAsString(content);
			} catch (JsonProcessingException e) {
				System.out.println("Exception while converting request body to JSON");
			}
		}
		if (contentInJson != null & log)
			ExtentCucumberAdapter.addTestStepLog(info);
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,
				MarkupHelper.createCodeBlock(contentInJson, CodeLanguage.JSON));
	}

	public static void logInfoToReport(String info, String content) {
		ExtentCucumberAdapter.addTestStepLog(info);
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, content);
	}

	public static void logJsonContentToReport(String info, Object content) {
		logJsonContentToReport(info, content, true);
	}

}
