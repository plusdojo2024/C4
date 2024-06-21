package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Chs;

public class ChsPostDao {

	public List<Chs> select() {
		Connection conn = null;
		List<Chs> chList = new ArrayList<Chs>();
		try {
               // データベースに接続する
 			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/C4_LinX", "sa", "");


				// JDChsドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/C4_LinX", "sa", "");

				// SQL文を準備する
				String sql = "SELECT * FROM CHANNELS ORDER BY created_At DESC";


				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();

				// 結果表をコレクションにコピーする
				while (rs.next()) {
					Chs record = new Chs(
		 				rs.getInt("Channel_Id"),
		 				rs.getString("chName"),
		 				rs.getString("chComment"),
		 				rs.getString("created_At"));
					chList.add(record);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				chList = null;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				chList = null;
			} finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						chList = null;
					}
				}
			}

			// 結果を返す
			return chList;


		}


}
