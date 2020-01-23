package kr.or.ddit.vo;

import java.io.Serializable;

public class DirectorVO implements Serializable{
	
	public DirectorVO() {
		super();
	}
	
	public DirectorVO(String code, String name, String nickname, String contentPath) {
		super();
		this.code = code;
		this.name = name;
		this.nickname = nickname;
		this.contentPath = contentPath;
	}
	private String code;
	private String name;
	private String nickname;
	private String contentPath;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getContentPath() {
		return contentPath;
	}
	public void setContentPath(String contentPath) {
		this.contentPath = contentPath;
	}
	@Override
	public String toString() {
		return "DirectorVO [code=" + code + ", name=" + name + ", nickname=" + nickname + ", contentPath=" + contentPath
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DirectorVO other = (DirectorVO) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
	
	
}
