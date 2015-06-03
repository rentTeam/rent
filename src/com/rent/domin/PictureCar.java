package com.rent.domin;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@IdClass(PictureCar.class)
public class PictureCar implements Serializable{
	private Picture pictureId;
	private Car carId;
	
	@Id
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="pictureId")
	public Picture getPictureId() {
		return pictureId;
	}
	public void setPictureId(Picture pictureId) {
		this.pictureId = pictureId;
	}
	@Id
	@OneToOne(cascade=CascadeType.ALL)
	public Car getCarId() {
		return carId;
	}
	public void setCarId(Car carId) {
		this.carId = carId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carId == null) ? 0 : carId.hashCode());
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
		PictureCar other = (PictureCar) obj;
		if (carId == null) {
			if (other.carId != null)
				return false;
		} else if (!carId.equals(other.carId))
			return false;
		if (pictureId == null) {
			if (other.pictureId != null)
				return false;
		} else if (!pictureId.equals(other.pictureId))
			return false;
		return true;
	}
	
}
