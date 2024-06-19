package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Chs;
import model.Posts;



public class ChsDao {

	          // 引数paramで検索項目を指定し、検索結果のリストを返す
	public List<Chs> select(Chs card) {
		Connection conn = null;
		List<Chs> cardList = new ArrayList<Chs>();
		try {
               // データベースに接続する
 			conn = DriverManager.getConnection("jdChs:h2:file:C:/pleiades/workspace/data/C4_LinX", "sa", "");


 				// JDChsドライバを読み込む
 				Class.forName("org.h2.Driver");

 				// データベースに接続する
 				conn = DriverManager.getConnection("jdChs:h2:file:C:/pleiades/workspace/data/C4_LinX", "sa", "");

 				// SQL文を準備する
 				String sql = "SELECT * FROM Chs WHERE CHNAME LIKE ?  ";


 				PreparedStatement pStmt = conn.prepareStatement(sql);

 				// SQL文を完成させる

 				if (card.getChName() != "") {
 					pStmt.setString(1, "%" + card.getChName() + "%");
 				} else {
 					pStmt.setString(1, "%");
 				}


 				// SQL文を実行し、結果表を取得する
 				ResultSet rs = pStmt.executeQuery();

 				// 結果表をコレクションにコピーする
 				while (rs.next()) {
 					Chs record = new Chs(
 						rs.getInt("ChannelId"),
 						rs.getString("chName"),
 						rs.getString("chComment"),
 						rs.getString("createdAt")
 				);
 						cardList.add(record);
 				}
 			} catch (SQLException e) {
 				e.printStackTrace();
 				cardList = null;
 			} catch (ClassNotFoundException e) {
 				e.printStackTrace();
 				cardList = null;
 			} finally {
 				// データベースを切断
 				if (conn != null) {
 					try {
 						conn.close();
 					} catch (SQLException e) {
 						e.printStackTrace();
 						cardList = null;
 					}
 				}
 			}

 			// 結果を返す
 			return cardList;


 		}

	// 引数paramで検索項目を指定し、検索結果のリストを返す
			public List<Posts> chSelect(int chId) {
				Connection conn = null;
				List<Posts> cardList = new ArrayList<Posts>();
				try {


		 				// JDChsドライバを読み込む
		 				Class.forName("org.h2.Driver");

		 				 // データベースに接続する
		 	 			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/C4_LinX", "sa", "");


		 				// SQL文を準備する
		 				String sql = "SELECT *FROM postsWHERE channels_id LIKE ?";



		 				PreparedStatement pStmt = conn.prepareStatement(sql);

		 				// SQL文を完成させる


		 					pStmt.setString(1, "%" + chId + "%");



		 				// SQL文を実行し、結果表を取得する
		 				ResultSet rs = pStmt.executeQuery();

		 				// 結果表をコレクションにコピーする
		 				//カラム名を合わせる
		 				while (rs.next()) {
		 					Posts record = new Posts(
		 						rs.getInt("CHANNEL_ID"),
		 						rs.getInt("CHANNEL_ID"),
		 						rs.getInt("CHANNEL_ID"),
		 						rs.getString("CHNAME"),
		 						rs.getInt("CHANNEL_ID"),
		 						rs.getInt("CHANNEL_ID"),
		 						rs.getInt("CHANNEL_ID"),
		 						rs.getString("CHNAME")
		 				);
		 						cardList.add(record);
		 				}
		 			} catch (SQLException e) {
		 				e.printStackTrace();
		 				cardList = null;
		 			} catch (ClassNotFoundException e) {
		 				e.printStackTrace();
		 				cardList = null;
		 			} finally {
		 				// データベースを切断
		 				if (conn != null) {
		 					try {
		 						conn.close();
		 					} catch (SQLException e) {
		 						e.printStackTrace();
		 						cardList = null;
		 					}
		 				}
		 			}

		 			// 結果を返す
		 			return cardList;


		 		}
/*

 		// 引数cardで指定されたレコードを登録し、成功したらtrueを返す
 		public boolean insert(Chs card) {
 			Connection conn = null;
 			boolean result = false;

 			try {
 				// JDChsドライバを読み込む
 				Class.forName("org.h2.Driver");

 				// データベースに接続する
 				conn = DriverManager.getConnection("jdChs:h2:file:C:/pleiades/workspace/data/simpleChs", "sa", "");

 				// SQL文を準備する（AUTO_INCREMENTのNUMBER列にはNULLを指定する）
 				String sql = "INSERT INTO Chs (CHANNEL_ID, CHNAME, CHCOMMENT,CREATED_AT ) VALUES (?, ?, ?, ?)";
 				PreparedStatement pStmt = conn.prepareStatement(sql);

 				// SQL文を完成させる
 				pStmt.setString(1, card.getChannelId());
 				pStmt.setString(2, card.getchName());
 				pStmt.setString(3, card.getCHCOMMENT());
 				pStmt.setString(4, card.getcreatedAt());



 				// SQL文を実行する
 				if (pStmt.executeUpdate() == 1) {
 					result = true;
 				}
 			} catch (SQLException e) {
 				e.printStackTrace();
 			} catch (ClassNotFoundException e) {
 				e.printStackTrace();
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
 			return result;
 		}



 		// 引数numberで指定されたレコードを削除し、成功したらtrueを返す
 		public boolean delete(int number) {
 			Connection conn = null;
 			boolean result = false;

 			try {
 				// JDChsドライバを読み込む
 				Class.forName("org.h2.Driver");

 				// データベースに接続する
 				conn = DriverManager.getConnection("jdChs:h2:file:C:/pleiades/workspace/data/C4_LinX", "sa", "");

 				// SQL文を準備する
 				String sql = "DELETE FROM Chs WHERE number=?";
 				PreparedStatement pStmt = conn.prepareStatement(sql);

 				// SQL文を完成させる
 				pStmt.setInt(1, number);

 				// SQL文を実行する
 				if (pStmt.executeUpdate() == 1) {
 					result = true;
 				}
 			} catch (SQLException e) {
 				e.printStackTrace();
 			} catch (ClassNotFoundException e) {
 				e.printStackTrace();
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
 			return result;
 		}*/
 	}
