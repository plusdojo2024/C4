package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.messages;





public class MessagesDao {
	 // データベース接続情報
    private final String url = "jdbc:mysql://localhost:3306/C4";


 // メッセージをデータベースに挿入するメソッド
    public void insertMessage(int conversationsId, String senderId, String receiverId, String messageContent) {
        String sql = "INSERT INTO messages (conversations_id, sender_id,"
        		+ " receiver_id, message_content, created_at) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url);
                PreparedStatement stmt = conn.prepareStatement(sql)) {
               stmt.setInt(1, conversationsId);
               stmt.setString(2, senderId);
               stmt.setString(3, receiverId);
               stmt.setString(4, messageContent);
               stmt.setTimestamp(5, new Timestamp(System.currentTimeMillis())); // 現在の時刻を取得してセット
               stmt.executeUpdate();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
 // 特定の会話（conversationsId）に関連するメッセージを取得するメソッド
    public List<messages> getMessagesByConversationsId(int conversationsId) {
        List<messages> messages = new ArrayList<>();
        String sql = "SELECT * FROM messages WHERE conversations_id = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, conversationsId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int messageId = rs.getInt("message_id");
                String senderId = rs.getString("sender_id");
                String receiverId = rs.getString("receiver_id");
                String messageContent = rs.getString("message_content");
                Timestamp createdAt = rs.getTimestamp("created_at");
                messages message = new messages(messageId, conversationsId, senderId, receiverId, messageContent, createdAt);
                messages.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messages;
    }

    // conversationsテーブルに対する操作

    // 新しい会話を追加するメソッド
    public void insertConversation(String user1Id, String user2Id) {
        String sql = "INSERT INTO conversations (user1_id, user2_id) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user1Id);
            stmt.setString(2, user2Id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // conversations_idを指定して会話を取得するメソッド
    public Conversation getConversationById(int conversationsId) {
        String sql = "SELECT * FROM conversations WHERE conversations_id = ?";
        Conversation conversation = null;

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, conversationsId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String user1Id = rs.getString("user1_id");
                String user2Id = rs.getString("user2_id");
                conversation = new Conversation(conversationsId, user1Id, user2Id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conversation;
    }

    // conversationsテーブルのエンティティクラス
    public class Conversation {
        private int conversationsId;
        private String user1Id;
        private String user2Id;

        public Conversation(int conversationsId, String user1Id, String user2Id) {
            this.conversationsId = conversationsId;
            this.user1Id = user1Id;
            this.user2Id = user2Id;
        }

        // ゲッター、セッターなどを実装
    }

    // messagesテーブルのエンティティクラス
    public class Message {
        private int messageId;
        private int conversationsId;
        private String senderId;
        private String receiverId;
        private String messageContent;
        private Timestamp createdAt;

        public Message(int messageId, int conversationsId, String senderId, String receiverId, String messageContent, Timestamp createdAt) {
            this.messageId = messageId;
            this.conversationsId = conversationsId;
            this.senderId = senderId;
            this.receiverId = receiverId;
            this.messageContent = messageContent;
            this.createdAt = createdAt;
        }

        // ゲッター、セッターなどを実装
    }
}