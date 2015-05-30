package com.rent.domin;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@IdClass(RentInfo.class)
public class RentInfo {
	private Car carId;
	private int timeLimit;
	private int keepHour;
	private float count;
	private User userId;
	
	@Id
	@OneToOne(cascade=CascadeType.ALL)
	public Car getCarId() {
		return carId;
	}
	public void setCarId(Car carId) {
		this.carId = carId;
	}
	
	@Basic
	@Column(name="timeLimit")
	public int getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}
	
	@Basic
	@Column(name="keeptime")
	public int getKeepHour() {
		return keepHour;
	}
	public void setKeepHour(int keepHour) {
		this.keepHour = keepHour;
	}
	
	@Basic
	@Column(name="count")
	public float getCount() {
		return count;
	}
	public void setCount(float count) {
		this.count = count;
	}
	
	@Id
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="userid")
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
		result = prime * result + ((carId == null) ? 0 : carId.hashCode());
		result = prime * result + Float.floatToIntBits(count);
		result = prime * result + keepHour;
		result = prime * result + timeLimit;
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
		RentInfo other = (RentInfo) obj;
		if (carId == null) {
			if (other.carId != null)
				return false;
		} else if (!carId.equals(other.carId))
			return false;
		if (Float.floatToIntBits(count) != Float.floatToIntBits(other.count))
			return false;
		if (keepHour != other.keepHour)
			return false;
		if (timeLimit != other.timeLimit)
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	
}
