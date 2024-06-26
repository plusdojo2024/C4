package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class UserDao {
	// ログインできるならtrueを返す
		public boolean isLoginOK(User user) {
			Connection conn = null;
			boolean loginResult = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/C4", "sa", "");

				// SELECT文を準備する
				String sql = "SELECT COUNT(*) FROM users WHERE employee_Id = ? AND password = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, user.getemployee_Id());
				pStmt.setString(2,user.getPassword());

				// SELECT文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();

				// ユーザーIDとパスワードが一致するユーザーがいたかどうかをチェックする
				rs.next();
				if (rs.getInt("COUNT(*)") == 1) {
					loginResult = true;
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
				loginResult = false;
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
				loginResult = false;
			}
			finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					}
					catch (SQLException e) {
						e.printStackTrace();
						loginResult = false;
					}
				}
			}

			// 結果を返す
			return loginResult;
		}

		public boolean insert(User user) {
			Connection conn = null;
			boolean CreateUserResult = false;
			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/C4", "sa", "");

				// SQL文を準備する（AUTO_INCREMENTのNUMBER列にはNULLを指定する）
				String sql = "INSERT INTO users VALUES ( ?, ?, ?, ?, ?, ?, ?, NULL)";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる

					pStmt.setString(1, user.getemployee_Id());

					pStmt.setString(2, user.getPassword());

					pStmt.setString(3, user.getUsername());
					pStmt.setString(4, user.getIcon());

					pStmt.setString(5, user.getBirth());

					pStmt.setString(6, user.getComment());

					pStmt.setInt(7, user.getPoint());



				// SQL文を実行する
				if (pStmt.executeUpdate() == 1) {
					CreateUserResult= true;
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


			return CreateUserResult;

}

	public User select(String employee_Id) {
				Connection conn = null;
//				List<User> userList = new ArrayList<User>();
				User user = null;



		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/C4", "sa", "");

			// SQL文を準備する
			/*String sql = "SELECT *  FROM users WHERE employee_Id = ? ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, employee_Id);*/
			//point
			String sql = "SELECT e.EMPLOYEE_ID, e.PASSWORD, e.USERNAME, e.ICON, e.BIRTH, e.COMMENT, COALESCE(post_counts.PostCount, 0)*10 AS POINT, e.BOOKING FROM Users e LEFT JOIN (SELECT EMPLOYEE_ID, COUNT(POST_ID) AS PostCount FROM POSTS WHERE EMPLOYEE_ID = ? GROUP BY EMPLOYEE_ID) post_counts ON e.EMPLOYEE_ID = post_counts.EMPLOYEE_ID WHERE e.EMPLOYEE_ID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, employee_Id);
			pStmt.setString(2, employee_Id);


			String sqlLang = "SELECT u.employee_Id, l.langName FROM users u LEFT JOIN langs l ON u.employee_Id= l.employee_Id WHERE u.employee_Id=? ";
			PreparedStatement pStmtLang = conn.prepareStatement(sqlLang);
			pStmtLang.setString(1, employee_Id);
			//String sql = "SELECT * FROM users WHERE employee_id = '0005'";


			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			ResultSet rsLang = pStmtLang.executeQuery();

			// Collect languages
	        List<String> languages = new ArrayList<>();
	        while (rsLang.next()) {
	            languages.add(rsLang.getString("langName"));
	        }

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				user = new User(
				rs.getString("employee_Id"),
				rs.getString("password"),
				rs.getString("username"),
				rs.getString("icon"),
				rs.getString("birth"),
				rs.getString("comment"),
				rs.getInt("point"),
				languages


				);
//				userList.add(record);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
//			userList = null;
			user = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
//			userList = null;
			user = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
//					userList = null;
					user = null;
				}
			}
		}

		// 結果を返す
//		return userList;
		return user;
	}
//	public boolean update(User user,String[] language)
	public boolean update(User user,String[] language) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/C4", "sa", "");

			// SQL文を準備する
			String sql = "UPDATE users SET username=?, birth=?, comment=?, point=? WHERE employee_Id=?";
//			String sqlDeleteLang = "DELETE FROM langs WHERE employee_Id=?";
			String sqlLang ="INSERT INTO langs VALUES ( NULL,?, ?) ";

			PreparedStatement pStmt = conn.prepareStatement(sql);
//			PreparedStatement pStmtDeleteLang = conn.prepareStatement(sqlDeleteLang);
			PreparedStatement pStmtLang = conn.prepareStatement(sqlLang);

//			UPDATE users SET username= '山口' WHERE employee_Id = '0003';
			// SQL文を完成させる
				pStmt.setString(1, user.getUsername());
				pStmt.setString(2, user.getBirth());
				pStmt.setString(3, user.getComment());
				pStmt.setInt(4, user.getPoint());
				pStmt.setString(5, user.getemployee_Id());

				;
//				pStmtDeleteLang.setString(1, user.getemployee_Id());
//		        pStmtDeleteLang.executeUpdate();

		        if(language != null) {
				for (String lang : language) {
		            pStmtLang.setString(1, user.getemployee_Id());
		            pStmtLang.setString(2, lang);
		            pStmtLang.executeUpdate();
		        	}
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

	//Photo Upload Methods

	//ここまで

	public boolean updatePhoto(String employeeId, String password, String username, String icon) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/C4", "sa", "");

			// SQL文を準備する
			String sql = "UPDATE users SET icon=? WHERE employee_Id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる

				pStmt.setString(1, icon);




			pStmt.setString(2,employeeId);

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


//	//↓以下 藤土編集 for account search
			//アカウント一覧表示
			public List<User> selectAllUsers() {
				Connection conn = null;
				List<User> userList = new ArrayList<User>();
			try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/C4", "sa", "");
			// SQL文を準備する
			//String sql = "SELECT *  FROM users WHERE username LIKE ? ";
			String sql = "SELECT * FROM users";

			//select  employee_Id,username from users;
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//pStmt.setString(1, employee_Id);
			//String sql = "SELECT * FROM users WHERE employee_id = '0005'";
			//pStmt.setString(1, "%" + username + "%");
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			// 結果表をコレクションにコピーする
			while (rs.next()) {
				User record = new User(
				rs.getString("employee_Id"),
				rs.getString("password"),
				rs.getString("username"),
				//rs.getString("icon"),
				rs.getString("birth"),
				rs.getString("comment"),
				rs.getInt("point")
				);
				userList.add(record);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			userList = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			userList = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					userList = null;
				}
			}
		}
		// 結果を返す
		System.out.print(userList);
		return userList;
	}
//		//アカウント検索後
		public List<User> selectByUsername(String username) {
					Connection conn = null;
					List<User> userList = new ArrayList<User>();
			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");
				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/C4", "sa", "");
				// SQL文を準備する
				String sql = "SELECT *  FROM users WHERE username LIKE ? ";
//				select  employee_Id,username from users;

				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, "%"+ username + "%");
				//pStmt.setString(1, employee_Id);
				//String sql = "SELECT * FROM users WHERE employee_id = '0005'";
				// SQL文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();
				// 結果表をコレクションにコピーする
				while (rs.next()) {
					User record = new User(
					rs.getString("employee_Id"),
					rs.getString("password"),
					rs.getString("username"),
					//rs.getString("icon"),
					rs.getString("birth"),
					rs.getString("comment"),
					rs.getInt("point")
					);
					userList.add(record);
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
				userList = null;
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
				userList = null;
			}
			finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					}
					catch (SQLException e) {
						e.printStackTrace();
						userList = null;
					}
				}
			}
			// 結果を返す
			return userList;
		}

//// ConversationIdを取得
//		public int getConversationIdByEmployeeId(String employee_Id) {
//	        Connection conn = null;
//	        int conversationId = -1;
//	        try {
//	            // JDBCドライバを読み込む
//	            Class.forName("org.h2.Driver");
//	            // データベースに接続する
//	            conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/C4", "sa", "");
//	            // SQL文を準備する
//	            String sql = "SELECT conversations_id FROM messages WHERE receiver_id = ?";
//	            PreparedStatement pStmt = conn.prepareStatement(sql);
//	            pStmt.setString(1, employee_Id);
//
//	            // SQL文を実行し、結果表を取得する
//	            ResultSet rs = pStmt.executeQuery();
//	            if (rs.next()) {
//	                conversationId = rs.getInt("conversations_id");
//	            }
//	        } catch (SQLException | ClassNotFoundException e) {
//	            e.printStackTrace();
//	        } finally {
//	            // データベースを切断
//	            if (conn != null) {
//	                try {
//	                    conn.close();
//	                } catch (SQLException e) {
//	                    e.printStackTrace();
//	                }
//	            }
//	        }
//	        return conversationId;
//	    }

//	↑ここまで藤土編集
		public boolean booking(String date, String id) {
			Connection conn = null;
			boolean result = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/C4", "sa", "");

				// SQL文を準備する
				String sql = "UPDATE users SET booking=? WHERE employee_Id=?";

				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				pStmt.setString(1, date);
				pStmt.setString(2, id);

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

		public String getName(String id) {
			Connection conn = null;
			String name = null;
	try {
		// JDBCドライバを読み込む
		Class.forName("org.h2.Driver");
		// データベースに接続する
		conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/C4", "sa", "");
		// SQL文を準備する
		String sql = "SELECT *  FROM users WHERE employee_id = ? ";
//		select  employee_Id,username from users;

		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setString(1, id);
		//pStmt.setString(1, employee_Id);
		//String sql = "SELECT * FROM users WHERE employee_id = '0005'";
		// SQL文を実行し、結果表を取得する
		ResultSet rs = pStmt.executeQuery();
		// 結果表をコレクションにコピーする
		rs.next();
			name = rs.getString("username");
	}
	catch (SQLException e) {
		e.printStackTrace();
		name = null;
	}
	catch (ClassNotFoundException e) {
		e.printStackTrace();
		name = null;
	}
	finally {
		// データベースを切断
		if (conn != null) {
			try {
				conn.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
				name = null;
			}
		}
	}
	// 結果を返す
	return name;
}








}












