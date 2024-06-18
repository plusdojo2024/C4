package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Notices;

public class NoticesDao {

    // Noticesテーブルの全レコードを取得するメソッド
    public List<Notices> findAll() {
        Connection conn = null;
        List<Notices> notices = new ArrayList<Notices>();

        try {
            // JDBCドライバを読み込む
            Class.forName("org.h2.Driver");

            // データベースに接続する
            conn = DriverManager.getConnection(
                "jdbc:h2:file:C:/pleiades/workspace/data/C4", "sa", "");

            // SQL文を準備する
            String sql = "SELECT * FROM Notices";
            PreparedStatement pStmt = conn.prepareStatement(sql);

            // SQL文を実行し、結果表を取得する
            ResultSet rs = pStmt.executeQuery();

            // 結果表をコレクションにコピーする
            while (rs.next()) {
                Notices record = new Notices(
                    rs.getInt("notices_id"), // notices_idカラムの値を取得
                    rs.getString("employee_id"), // employee_idカラムの値を取得
                    rs.getString("content"), // contentカラムの値を取得
                    rs.getString("notice_status"), // notice_statusカラムの値を取得
                    rs.getTimestamp("notice_date") // notice_dateカラムの値を取得
                );
                notices.add(record); // 取得したレコードをリストに追加
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            notices = null; // エラー発生時にはnullを設定
        } finally {
            // データベースを切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        // 結果を返す
        return notices;
    }
}
