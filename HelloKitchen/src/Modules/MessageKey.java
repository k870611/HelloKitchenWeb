package Modules;

public class MessageKey implements java.io.Serializable {
	private String sender, receiver, time;

	/**
	 * @param sender
	 * @param receiver
	 * @param time
	 */
	public MessageKey(String sender, String receiver, String time) {
		super();
		 this.setSender(sender);
		 this.setReceiver(receiver);
		 this.setTime(time);
	}
	public MessageKey(){
		
	}
	
//----------------------------------------------------------------
	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender.toLowerCase();
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver.toLowerCase();
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time.toLowerCase();
	}
	
	@Override
	public String toString() {
		return "MessageKey [sender=" + sender + ", receiver=" + receiver + ", time=" + time + "]";
	}
	@Override
	public boolean equals(Object messagekey) {
		// TODO Auto-generated method stub
		MessageKey mk = (MessageKey) messagekey;
		boolean sender = this.getSender().equals(mk.getSender());
		boolean receiver = this.getReceiver().equals(mk.getReceiver());
		boolean time = this.getTime().equals(mk.getTime());
		return sender&&receiver&&time;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
	int s = sender.length();
	int r = receiver.length();
	return s*2+r*3;
	}
	/*比對寄件者---*/
	public boolean findBySenderId(String SenderId){
		return this.getSender().equals(SenderId.toLowerCase());
	}
    /*比對收件者---*/
	public boolean findByReceiverId(String ReceiverId){
		return this.getReceiver().equals(ReceiverId.toLowerCase());
	}
	/*比對寄件者和收件者*/
	public boolean findBySidAndRid(String SenderId,String ReceiverId){
		boolean sender = this.getSender().equals(SenderId.toLowerCase());
		boolean receiver = this.getReceiver().equals(ReceiverId.toLowerCase());
		return sender&&receiver;
	}
	/*取得混和資料*/
	public boolean findMixMessage(String SenderId,String ReceiverId){
		boolean sender = this.getSender().equals(SenderId.toLowerCase());
		boolean receiver = this.getReceiver().equals(ReceiverId.toLowerCase());
		//---------------------------------
		boolean sr = this.getSender().equals(ReceiverId.toLowerCase());
		boolean rs = this.getReceiver().equals(SenderId.toLowerCase());
		return (sender&&receiver) || (sr&&rs);
	}
}
