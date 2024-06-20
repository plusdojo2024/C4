package model;

import java.io.Serializable;
import java.sql.Timestamp;

public class messages implements Serializable{
   private int messagesId;
   private int conversationsId;
   private String senderId;
   private String receiverId;
   private String messageContent;
   private Timestamp createdAt;


 //引数がないコンストラクタ
 	public messages() {

 	}

 	//引数があるコンストラクタ
 		public messages(int messagesId, int conversationsId, String senderId,
 				String receiverId, String messageContent, Timestamp createdAt
 				) {
 			super();
 			this.messagesId = messagesId;
 			this.conversationsId = conversationsId;
 			this.senderId = senderId;
 			this.receiverId = receiverId;
 			this.messageContent = messageContent;
 			this.createdAt = createdAt;
 		}

		public int getMessagesId() {
			return messagesId;
		}

		public void setMessagesId(int messagesId) {
			this.messagesId = messagesId;
		}

		public int getConversationsId() {
			return conversationsId;
		}

		public void setConversationsId(int conversationsId) {
			this.conversationsId = conversationsId;
		}

		public String getSenderId() {
			return senderId;
		}

		public void setSenderId(String senderId) {
			this.senderId = senderId;
		}

		public String getReceiverId() {
			return receiverId;
		}

		public void setReceiverId(String receiverId) {
			this.receiverId = receiverId;
		}

		public String getMessageContent() {
			return messageContent;
		}

		public void setMessageContent(String messageContent) {
			this.messageContent = messageContent;
		}

		public Timestamp getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(Timestamp createdAt) {
			this.createdAt = createdAt;
		}
}

