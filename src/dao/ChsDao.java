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
	public List<Chs> select() {
		Connection conn = null;
		List<Chs> chList = new ArrayList<Chs>();
		try {
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/C4", "sa", "");

			// JDChsドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/C4", "sa", "");

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

	// 引数paramで検索項目を指定し、検索結果のリストを返す
	public List<Posts> chPostSelect(int chId) {
		Connection conn = null;
		List<Posts> cardList = new ArrayList<Posts>();
		try {

			// JDChsドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/C4", "sa", "");

			// SQL文を準備する
			String sql = "SELECT * FROM USERS INNER JOIN POSTS ON USERS.EMPLOYEE_ID = POSTS.EMPLOYEE_ID WHERE channels_id LIKE ? ORDER BY created_At DESC";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる

			pStmt.setString(1, "%" + chId + "%");

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			//カラム名を合わせる
			while (rs.next()) {
				Posts record = new Posts(
				rs.getInt("post_id"),
				rs.getInt("channels_id"),
				rs.getString("employee_id"),
				rs.getString("content"),
				rs.getInt("comments_id"),
				rs.getInt("reaction_id"),
				rs.getInt("file_id"),
				rs.getString("created_at"),
				rs.getString("USERNAME")
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
		public List<Chs> chSelect(String chName) {
			Connection conn = null;
			List<Chs> chList = new ArrayList<Chs>();
			try {

				// JDChsドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/C4", "sa", "");

				// SQL文を準備する
				String sql = "SELECT * FROM CHANNELS WHERE CHNAME LIKE ?";

				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる

				pStmt.setString(1, "%" + chName + "%");

				// SQL文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();

				// 結果表をコレクションにコピーする
				//カラム名を合わせる
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

		public Chs chOneSelect(int chId) {
			Connection conn = null;
			Chs ch = null;
			try {

				// JDChsドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/C4", "sa", "");

				// SQL文を準備する
				String sql = "SELECT * FROM CHANNELS WHERE CHANNEL_ID = ?";

				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる

				pStmt.setInt(1, chId);

				// SQL文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();

				// 結果表をコレクションにコピーする
				//カラム名を合わせる
				rs.next();
					ch = new Chs(
						rs.getInt("Channel_Id"),
						rs.getString("chName"),
						rs.getString("chComment"),
						rs.getString("created_At"));
			} catch (SQLException e) {
				e.printStackTrace();
				ch = null;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				ch = null;
			} finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						ch = null;
					}
				}
			}

			// 結果を返す
			return ch;
		}

	public boolean delete(int chId) {
		Connection conn = null;
		boolean result = false;
		System.out.print(chId);

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/C4", "sa", "");

			// SQL文を準備する
			String sql = "DELETE FROM CHANNELS WHERE CHANNEL_ID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, chId);

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
	// チャンネルを削除するメソッド

	// 引数cardで指定されたレコードを登録し、成功したらtrueを返す
	public boolean regist(String name, String comment) {
		Connection conn = null;
		boolean result = false;
		System.out.print(name);
		System.out.print(comment);

		try {
			// JDChsドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/C4", "sa", "");

			// SQL文を準備する（AUTO_INCREMENTのNUMBER列にはNULLを指定する）
			String sql = "INSERT INTO CHANNELS (CHANNEL_ID, CHNAME, CHCOMMENT,CREATED_AT ) VALUES(null, ?, ?, CURRENT_TIMESTAMP);";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// SQL文を完成させる
			pStmt.setString(1, name);
			pStmt.setString(2, comment);

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

	public List<String> booking(String date) {
		Connection conn = null;
		List<String> booking = new ArrayList<String>();
		try {
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/C4", "sa", "");

			// JDChsドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/C4", "sa", "");

			// SQL文を準備する
			String sql = "SELECT * FROM USERS WHERE booking = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setString(1, date);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				String record = rs.getString("EMPLOYEE_ID");
				booking.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			booking = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			booking = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					booking = null;
				}
			}
		}

		// 結果を返す
		return booking;

	}
}