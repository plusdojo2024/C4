package model;

import java.io.Serializable;

public class User implements Serializable {
	private String employeeId;
	private String password;
	private String username;
	private String icon;
	private int lang;
	private String birth;
	private String comment;
	private int point;


	public User(String employeeId, String password, String username, String icon, int lang, String birth, String comment,
			int point) {
		super();
		this.employeeId = employeeId;
		this.password = password;
		this.username = username;
		this.icon = icon;
		this.lang = lang;
		this.birth = birth;
		this.comment = comment;
		this.point = point;
	}
	public User(String employeeId, String password) {
		this.employeeId = employeeId;
		this.password = password;
	}
	public User(String employeeId, String password, String username) {
		this.employeeId = employeeId;
		this.password = password;
		this.username = username;
	}

	public User() {

	}


	public User(String username2) {
		// TODO 自動生成されたコンストラクター・スタブ
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public int getLang() {
		return lang;
	}
	public void setLang(int lang) {
		this.lang = lang;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}



}
