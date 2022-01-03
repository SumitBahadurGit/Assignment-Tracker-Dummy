package emailTemplates;

import java.util.Map;
import java.util.function.Function;

public class EmailTemplates {

	public final static String SITENAME = "KUEBIKOIT";
	public final static String SITELINK = "SITELINK";
	public final static String SITELINK_VALUE = "http://kuebikoit-portal.com/";
	public final static String ID = "ID";
	public final static String TITLE = "TITLE";
	public final static String POSTED_BY = "POSTED_BY";
	public final static String UPDATED_BY = "POSTED_BY";
	public final static String ASSIGNED_TO = "UPDATED_BY";
	public final static String DEADLINE = "DEADLINE";
	public final static String USER_NAME = "USER_NAME";

	public final static String SENSITIVE_DATA = "Sensiive Data";
	public final static String NEW_TASK = "New Task Assigned";
	public final static String NEW_COMMENT = "Comments on Assigned Task";
	public final static String TASK_MODIFIED = "Assigned Task Modified";
	public final static String NEW_USER_ADDED = "Assignment Tracker Login";


	public final static String NAME = "NAME";
	public final static String EMAIL = "EMAIL";
	public final static String PASSWORD = "PASSWORD";
	public final static String ROLE = "ROLE";
	public final static String ADDED_BY = "ADDED_BY";

	public final static String AUTHOR = "AUTHOR";
	public final static String COMMENT = "COMMENT";

	public static Function<Map<String, String>, String> forgotPasswordTemplate = (map) -> {
		return "<html >" + " <body style='width:500px; margin: auto; text-align: center; background-color: lightblue'> "
				+ "<h3 style='color:blue'> <a href='" + map.get(SITELINK) + "'>" + SITENAME + "</a> </h3> "
				+ " <div style='width:200px;  text-align:left; padding: 12px; margin: auto; border: 1px solid gray;'>"
				+ " <p> Your temporary password is: " + map.get(PASSWORD) + " <i></i> </p>"
				+ " <p> Please consider changing your password <i></i> </p>" + " </div>" + " <br> <hr></hr>"
				+ " <small>" + " This email is auto generated. Please do not reply to this email." + " </small> "
				+ "</body>" + " </html>";
	};

	public static Function<Map<String, String>, String> newCommentTempalte = (map) -> {
		return "<html >" + " <body style='width:500px; margin: auto; text-align: center; background-color: lightblue'> "
				+ "<h3 style='color:blue'> <a href='" + map.get(SITELINK) + "'>" + SITENAME + "</a> </h3> " + "<h4> HI "
				+ map.get(USER_NAME) + ", There is a comment on an assignment assigned to you.</h4>"
				+ " <div style='width:200px;  text-align:left; padding: 12px; margin: auto; border: 1px solid gray;'>"
				+ "<h3>" + map.get(TITLE) + "</h3>" + "<p> Task Id #: " + map.get(ID) + " </p>" + " <p> "
				+ map.get(AUTHOR) + " commented: " + map.get(COMMENT) + " <i></i> </p>" + " </div>" + " <br> <hr></hr>"
				+ " <small>" + " This email is auto generated. Please do not reply to this email." + " </small> "
				+ "</body>" + " </html>";
	};

	public static Function<Map<String, String>, String> newAssignedTemplate = (map) -> {
		return "<html> " + "<body style='width:500px; margin: auto; text-align: center; background-color: lightblue'>"
				+ "<h3 style='color:blue'> <a href='" + map.get(SITELINK) + "'>" + SITENAME + "</a> </h3> " + "<h4> HI "
				+ map.get(USER_NAME)
				+ ", You have been assigned a new task. Please visit the portal to view details. </h4> "
				+ "<div style='width:200px;  text-align:left; padding: 12px; margin: auto; border: 1px solid gray;'> "
				+ "<h3>" + map.get(TITLE) + "</h3>" + "<p> Task Id #: " + map.get(ID) + " </p>" + "<p> Assigned To: "
				+ map.get(ASSIGNED_TO) + " </p>" + "<p> Deadline: " + map.get(DEADLINE) + "</p>" + "<p> Posted By: "
				+ map.get(POSTED_BY) + " </p>" + "<p> Updated By: " + map.get(UPDATED_BY) + " </p>" + " </div>"
				+ " <br> <hr></hr>"
				+ " <small> This email is auto generated. Please do not reply to this email. </small>" + " </body>"
				+ " </html>";
	};

	public static Function<Map<String, String>, String> assignUpdatedTemplate = (map) -> {
		return "<html> " + "<body style='width:500px; margin: auto; text-align: center; background-color: lightblue'>"
				+ "<h3 style='color:blue'> <a href='" + map.get(SITELINK) + "'>" + SITENAME + "</a> </h3> " + "<h4> HI "
				+ map.get(USER_NAME)
				+ ", an assignment has been updated. Please visit the portal to view details. </h4> "
				+ "<div style='width:200px;  text-align:left; padding: 12px; margin: auto; border: 1px solid gray;'> "
				+ "<h3>" + map.get(TITLE) + "</h3>" + "<p> Task Id #: " + map.get(ID) + " </p>" + "<p> Assigned To: "
				+ map.get(ASSIGNED_TO) + " </p>" + "<p> Deadline: " + map.get(DEADLINE) + "</p>" + "<p> Posted By: "
				+ map.get(POSTED_BY) + " </p>" + "<p> Updated By: " + map.get(UPDATED_BY) + " </p>" + " </div>"
				+ " <br> <hr></hr>"
				+ " <small> This email is auto generated. Please do not reply to this email. </small>" + " </body>"
				+ " </html>";
	};

	public static Function<Map<String, String>, String> assigneeChangeTemplate = (map) -> {
		return "<html> " + "<body style='width:500px; margin: auto; text-align: center; background-color: lightblue'>"
				+ "<h3 style='color:blue'> <a href='" + map.get(SITELINK) + "'>" + SITENAME + "</a> </h3> " + "<h4> HI "
				+ map.get(USER_NAME)
				+ ", ,an assignment, previously assigned to you, has been modified and no longer assigned to you. </h4> "
				+ "<div style='width:200px;  text-align:left; padding: 12px; margin: auto; border: 1px solid gray;'> "
				+ "<h3>" + map.get(TITLE) + "</h3>" + "<p> Task Id #: " + map.get(ID) + " </p>" + "<p> Assigned To: "
				+ map.get(ASSIGNED_TO) + " </p>" + "<p> Deadline: " + map.get(DEADLINE) + "</p>" + "<p> Posted By: "
				+ map.get(POSTED_BY) + " </p>" + "<p> Updated By: " + map.get(UPDATED_BY) + " </p>" + " </div>"
				+ " <br> <hr></hr>"
				+ " <small> This email is auto generated. Please do not reply to this email. </small>" + " </body>"
				+ " </html>";
	};

	public static Function<Map<String, String>, String> newUserAddedTemplate = (map) -> {
		return "<html>" + "<body style='width:500px; margin: auto; text-align: center; background-color: lightblue'> "
				+ "<h3 style='color:blue'> <a href=" + map.get(SITELINK) + ">" + SITENAME + "</a> </h3> "
				+ "<div style='width:200px;  text-align:left; padding: 12px; margin: auto; border: 1px solid gray;'>"
				+ " <p> Hi " + map.get(NAME) + ", you have been added to the KuebikoIT Portal as an "
				+ map.get(ROLE) + "</p>" + " <p> Use these credentials to login</p> <p>Username: " + map.get(USER_NAME)
				+ " </p>" + " <p>Password: " + map.get(PASSWORD) + " </p>" + "</div>" + " <br> <hr></hr>"
				+ " <small> This email is auto generated. Please do not reply to this email. </small> </body> </html>";
	};
}
