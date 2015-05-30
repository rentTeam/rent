package com.rent.domin;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@IdClass(PictureUser.class)
public class PictureUser {
	private Picture pictureId;
	private User userId;
	
	@Id
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="pictureid")
	public Picture getPictureId() {
		return pictureId;
	}
	public void setPictureId(Picture pictureId) {
		this.pictureId = pictureId;
	}
	
	@Id
	@OneToOne(cascade=CascadeType.ALL)
	public User getUserId() {
		return userId;
	}
	public void setUserId(User userId) {
		this.userId = userId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((pictureId == null) ? 0 : pictureId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		PictureUser other = (PictureUser) obj;
		if (pictureId == null) {
			if (other.pictureId != null)
				return false;
		} else if (!pictureId.equals(other.pictureId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	
}
