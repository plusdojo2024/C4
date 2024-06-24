package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Posts;

public class HomeDao {
	// 引数paramで検索項目を指定し、検索結果のリストを返す
	public List<Posts> search(String search) {
		Connection conn = null;
		List<Posts> PostList = new ArrayList<Posts>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/C4", "sa", "");

			// SQL文を準備する
			String sql = "SELECT * FROM POSTS WHERE CHANNELS_ID = 0 AND CONTENT LIKE ? ORDER BY CREATED_AT desc";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// SQL文を完成させる
			//検索するためのDAOを作成
			pStmt.setString(1, "%" + search + "%");

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Posts record = new Posts(
				rs.getInt("post_id"),
				rs.getInt("channels_id"),
				rs.getInt("employee_id"),
				rs.getString("content"),
				rs.getInt("comments_id"),
				rs.getInt("reaction_id"),
				rs.getInt("file_id"),
				rs.getString("created_at")
				);
				PostList.add(record);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			PostList = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			PostList = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					PostList = null;
				}
			}
		}

		// 結果を返す
		return PostList;
	}

	// 引数Postsで指定されたレコードを登録し、成功したらtrueを返す
	//登録機能のこと
	public boolean insert(Posts post) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/C4", "sa", "");

			// SQL文を準備する（AUTO_INCREMENTのNUMBER列にはNULLを指定する）
			String sql = "INSERT INTO Bc VALUES (NULL, 0, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, post.getEmployee_id());

			pStmt.setString(2, post.getContent());

			if (post.getComments() != 0) {
				pStmt.setInt(3, post.getComments());
			}
			else {
				pStmt.setInt(3, 0);
			}

			if (post.getReaction_id() != 0) {
				pStmt.setInt(4, post.getReaction_id());
			}
			else {
				pStmt.setInt(4, 0);
			}

			if (post.getFile_id() != 0) {
				pStmt.setInt(5, post.getFile_id());
			}
			else {
				pStmt.setInt(5, 0);
			}


			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		// 結果を返す
		return result;
	}

	// 引数cardで指定されたレコードを更新し、成功したらtrueを返す
	//リアクションの0を他の数字に変える

	public boolean update(Posts post) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/C4", "sa", "");

			// SQL文を準備する
			String sql = "UPDATE Bc SET REACTION_ID=? WHERE POST_ID =?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (post.getReaction_id() == 0) {							//==0のとき
				pStmt.setInt(5, post.getReaction_id());
			}
			else {
				pStmt.setInt(5, 1);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		// 結果を返す
		return result;
	}
				//削除用sql文
	// 引数numberで指定されたレコードを削除し、成功したらtrueを返す
	public boolean delete(int post_id) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/C4", "sa", "");

			// SQL文を準備する
			String sql = "DELETE FROM Bc WHERE post_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, post_id);

			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		// 結果を返す
		return result;
	}

	public List<Posts> select() {
		Connection conn = null;
		List<Posts> PostList = new ArrayList<Posts>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/C4", "sa", "");

			// SQL文を準備する
			String sql = "SELECT * FROM POSTS WHERE CHANNELS_ID = 0 ORDER BY CREATED_AT desc";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Posts record = new Posts(
				rs.getInt("POST_ID"),
				rs.getInt("CHANNELS_ID"),
				rs.getInt("EMPLOYEE_ID"),
				rs.getString("CONTENT"),
				rs.getInt("COMMENTS_ID"),
				rs.getInt("REACTION_ID"),
				rs.getInt("FILE_ID"),
				rs.getString("CREATED_AT")
				);
				PostList.add(record);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			PostList = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			PostList = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					PostList = null;
				}
			}
		}

		// 結果を返す
		return PostList;
	}
}
