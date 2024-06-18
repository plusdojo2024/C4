package model;
import java.io.Serializable;
import java.sql.Timestamp;

public class Notices implements Serializable {
	private int noticesId;
    private String employeeId;
    private String content;
    private String noticeStatus;
    private Timestamp noticeDate;

    //コンストラクタ」
	public Notices() {
		this(0, null, null, null, null);
	}

	public Notices(
			int noticesId,
			String employeeId,
			String content,
			String noticeStatus,
			Timestamp noticeDate) {

			this.noticesId = noticesId;
	        this.employeeId = employeeId;
	        this.content = content;
	        this.noticeStatus = noticeStatus;
	        this.noticeDate = noticeDate;

	}

	// ゲッターとセッター
    public int getNoticesId() {
        return noticesId;
    }

    public void setNoticesId(int noticesId) {
        this.noticesId = noticesId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNoticeStatus() {
        return noticeStatus;
    }

    public void setNoticeStatus(String noticeStatus) {
        this.noticeStatus = noticeStatus;
    }

    public Timestamp getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(Timestamp noticeDate) {
        this.noticeDate = noticeDate;
    }
}

