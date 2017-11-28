package application;


public class Message {
	
	private String msgProcessTime;
	private String userName;
	private String message;
	
	public String getUserName() {
		return userName;
	}
	public String getMessage() {
		return message;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMsgProcessTime() {
		return msgProcessTime;
	}
	public void setMsgProcessTime(String msgProcessTime) {
		this.msgProcessTime = msgProcessTime;
	}
}
