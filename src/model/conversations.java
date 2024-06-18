package model;

import java.io.Serializable;

public class conversations implements Serializable {
    private int conversations_id;
    private String user1_id;
    private String user2_id;

  //引数がないコンストラクタ
   	public conversations() {

   	}
  //引数があるコンストラクタ
		public conversations(int conversations_id, String user1_id, String user2_id
				) {
			super();
			this.conversations_id = conversations_id;
			this.user1_id = user1_id;
			this.user2_id = user2_id;


		}
		public int getConversations_id() {
			return conversations_id;
		}
		public void setConversations_id(int conversations_id) {
			this.conversations_id = conversations_id;
		}
		public String getUser1_id() {
			return user1_id;
		}
		public void setUser1_id(String user1_id) {
			this.user1_id = user1_id;
		}
		public String getUser2_id() {
			return user2_id;
		}
		public void setUser2_id(String user2_id) {
			this.user2_id = user2_id;
		}

}
