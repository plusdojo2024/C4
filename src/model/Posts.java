package model;
import java.io.Serializable;


public class Posts implements Serializable{


	private int post_id;
	private int channels_id;
	private String employee_id;
	private String content;
	private int comments;
	private int reaction_id;
	private int file_id;
	private String created_at;


	public Posts() {

	}

	public Posts(int post_id, int channels_id, String employee_id, String content, int comments, int reaction_id, int file_id, String created_at) {
		super();
		this.post_id = post_id;
		this.channels_id = channels_id;
		this.employee_id = employee_id;
		this.content = content;
		this.comments = comments;
		this.reaction_id = reaction_id;
		this.file_id = file_id;
		this.created_at = created_at;
	}

	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	public int getChannels_id() {
		return channels_id;
	}

	public void setChannels_id(int channels_id) {
		this.channels_id = channels_id;
	}

	public String getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getComments() {
		return comments;
	}

	public void setComments(int comments) {
		this.comments = comments;
	}

	public int getReaction_id() {
		return reaction_id;
	}

	public void setReaction_id(int reaction_id) {
		this.reaction_id = reaction_id;
	}

	public int getFile_id() {
		return file_id;
	}

	public void setFile_id(int file_id) {
		this.file_id = file_id;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

}
