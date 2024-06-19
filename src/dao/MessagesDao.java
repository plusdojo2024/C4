package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.messages;

public class MessagesDao {
	// 引数paramで検索項目を指定し、検索結果のリストを返す
	public List<messages> select(int conversation_id) {
		Connection conn = null;
		List<messages> messagesList = new ArrayList<messages>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/C4", "sa", "");

			// SQL文を準備する
			String sql = "SELECT * FROM MESSAGES WHERE conversations_id =?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// SQL文を完成させる

				pStmt.setInt(1,conversation_id);


			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				messages record = new messages(
				rs.getInt("MESSAGE_ID"),
				rs.getInt("CONVERSATIONS_ID"),
				rs.getString("SENDER_ID"),
				rs.getString("RECEIVER_ID"),
				rs.getString("MESSAGE_CONTENT"),
				rs.getTimestamp ("CREATED_AT")

				);
				messagesList.add(record);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			messagesList = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			messagesList = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					messagesList = null;
				}
			}
		}

		// 結果を返す
		return messagesList;
	}
/*
	// 引数cardで指定されたレコードを登録し、成功したらtrueを返す
	public boolean insert(Bc card) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/simpleBC", "sa", "");

			// SQL文を準備する（AUTO_INCREMENTのNUMBER列にはNULLを指定する）
			String sql = "INSERT INTO Bc VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (card.getCompany() != null && !card.getCompany().equals("")) {
				pStmt.setString(1, card.getCompany());
			}
			else {
				pStmt.setString(1, "（未設定）");
			}
			if (card.getDepartment() != null && !card.getDepartment().equals("")) {
				pStmt.setString(2, card.getDepartment());
			}
			else {
				pStmt.setString(2, "（未設定）");
			}
			if (card.getPosition() != null && !card.getPosition().equals("")) {
				pStmt.setString(3, card.getPosition());
			}
			else {
				pStmt.setString(3, "（未設定）");
			}
			if (card.getName() != null && !card.getName().equals("")) {
				pStmt.setString(4, card.getName());
			}
			else {
				pStmt.setString(4, "（未設定）");
			}
			if (card.getZipcode() != null && !card.getZipcode().equals("")) {
				pStmt.setString(5, card.getZipcode());
			}
			else {
				pStmt.setString(5, "（未設定）");
			}
			if (card.getAddress() != null && !card.getAddress().equals("")) {
				pStmt.setString(6, card.getAddress());
			}
			else {
				pStmt.setString(6, "（未設定）");
			}
			if (card.getPhone() != null && !card.getPhone().equals("")) {
				pStmt.setString(7, card.getPhone());
			}
			else {
				pStmt.setString(7, "（未設定）");
			}
			if (card.getFax() != null && !card.getFax().equals("")) {
				pStmt.setString(8, card.getFax());
			}
			else {
				pStmt.setString(8, "（未設定）");
			}
			if (card.getEmail() != null && !card.getEmail().equals("")) {
				pStmt.setString(9, card.getEmail());
			}
			else {
				pStmt.setString(9, "（未設定）");
			}
			if (card.getRemarks() != null && !card.getRemarks().equals("")) {
				pStmt.setString(10, card.getRemarks());
			}
			else {
				pStmt.setString(10, "（未設定）");
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
	public boolean update(Bc card) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/simpleBC", "sa", "");

			// SQL文を準備する
			String sql = "UPDATE Bc SET company=?, department=?, position=?, name=?, zipcode=?, address=?, phone=?, fax=?, email=?, remarks=? WHERE number=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (card.getCompany() != null && !card.getCompany().equals("")) {
				pStmt.setString(1, card.getCompany());
			}
			else {
				pStmt.setString(1, null);
			}
			if (card.getDepartment() != null && !card.getDepartment().equals("")) {
				pStmt.setString(2, card.getDepartment());
			}
			else {
				pStmt.setString(2, null);
			}
			if (card.getPosition() != null && !card.getPosition().equals("")) {
				pStmt.setString(3, card.getPosition());
			}
			else {
				pStmt.setString(3, null);
			}
			if (card.getName() != null && !card.getName().equals("")) {
				pStmt.setString(4, card.getName());
			}
			else {
				pStmt.setString(4, null);
			}
			if (card.getZipcode() != null && !card.getZipcode().equals("")) {
				pStmt.setString(5, card.getZipcode());
			}
			else {
				pStmt.setString(5, null);
			}
			if (card.getAddress() != null && !card.getAddress().equals("")) {
				pStmt.setString(6, card.getAddress());
			}
			else {
				pStmt.setString(6, null);
			}
			if (card.getPhone() != null && !card.getPhone().equals("")) {
				pStmt.setString(7, card.getPhone());
			}
			else {
				pStmt.setString(7, null);
			}
			if (card.getFax() != null && !card.getFax().equals("")) {
				pStmt.setString(8, card.getFax());
			}
			else {
				pStmt.setString(8, null);
			}
			if (card.getEmail() != null && !card.getEmail().equals("")) {
				pStmt.setString(9, card.getEmail());
			}
			else {
				pStmt.setString(9, null);
			}
			if (card.getRemarks() != null && !card.getRemarks().equals("")) {
				pStmt.setString(10, card.getRemarks());
			}
			else {
				pStmt.setString(10, null);
			}
			pStmt.setInt(11, card.getNumber());

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

	// 引数numberで指定されたレコードを削除し、成功したらtrueを返す
	public boolean delete(int number) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/simpleBC", "sa", "");

			// SQL文を準備する
			String sql = "DELETE FROM Bc WHERE number=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, number);

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
	}*/
}
