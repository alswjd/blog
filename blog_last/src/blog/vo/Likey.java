package blog.vo;

public class Likey {
	private int lokeyNo;
	private int postNo;
	private String memberId;
	private int likeyCk;
	private String likeyDate;
	
	public int getLokeyNo() {
		return lokeyNo;
	}
	public void setLokeyNo(int lokeyNo) {
		this.lokeyNo = lokeyNo;
	}
	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public int getLikeyCk() {
		return likeyCk;
	}
	public void setLikeyCk(int likeyCk) {
		this.likeyCk = likeyCk;
	}
	public String getLikeyDate() {
		return likeyDate;
	}
	public void setLikeyDate(String likeyDate) {
		this.likeyDate = likeyDate;
	}
}
