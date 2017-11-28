package application;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DataSender {
	
	private static OutputStream outputStream;
	private String msg;
	
	public DataSender(String msg) {
		this.msg = msg;
	}
	
	public void runOperation() {
		DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
		try {
			dataOutputStream.writeUTF(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void setOutputStream(OutputStream outputStream) {
		DataSender.outputStream = outputStream;
	}
}
