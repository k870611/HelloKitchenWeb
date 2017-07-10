package EntityComponents;

public class Message implements java.io.Serializable,Comparable<Message>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int fId; //--pk
	private String sender, receiver, message, time;

	public Message(){
		
	}

	public Message(int fId, String sender, String receiver, String message, String time) {
		super();
		this.fId = fId;
		this.sender = sender;
		this.receiver = receiver;
		this.message = message;
		this.time = time;
	}
	
	@Override
	public String toString() {
		return "Message [fId=" + fId + ", sender=" + sender + ", receiver=" + receiver
				+ ", message=" + message + ", time=" + time + "]";
	}
	
	public int getfId() {
		return fId;
	}
    public void setfId(int fId) {
		this.fId = fId;
	}


	public String getSender() {
		return sender;
	}


	public void setSender(String sender) {
		this.sender = sender;
	}


	public String getReceiver() {
		return receiver;
	}


	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public int compareTo(Message message) {
		// TODO Auto-generated method stub
		int result = this.getTime().compareTo(message.getTime());
		return result;
	}

}
