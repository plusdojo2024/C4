package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.conversations;

public class ConvDao {
    public List<conversations> selectByConversationsId(String conversations_id) {
        Connection conn = null;
        List<conversations> convList = new ArrayList<conversations>();

        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/C4", "sa", "");

            String sql = "SELECT * FROM conversations WHERE conversations_id = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, conversations_id);
            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                conversations conv = new conversations(
                    rs.getInt("conversations_id"),
                    rs.getString("user1_id"),
                    rs.getString("user2_id")
                );
                convList.add(conv);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            convList = null;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return convList;
    }
}
