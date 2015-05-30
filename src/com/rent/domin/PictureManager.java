package com.rent.domin;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@IdClass(PictureManager.class)
public class PictureManager {
	private Picture pictureId;
	private Manager managerId;
	
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
	public Manager getManagerId() {
		return managerId;
	}
	public void setManagerId(Manager managerId) {
		this.managerId = managerId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((managerId == null) ? 0 : managerId.hashCode());
		result = prime * result
				+ ((pictureId == null) ? 0 : pictureId.hashCode());
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
		PictureManager other = (PictureManager) obj;
		if (managerId == null) {
			if (other.managerId != null)
				return false;
		} else if (!managerId.equals(other.managerId))
			return false;
		if (pictureId == null) {
			if (other.pictureId != null)
				return false;
		} else if (!pictureId.equals(other.pictureId))
			return false;
		return true;
	}
	
}
