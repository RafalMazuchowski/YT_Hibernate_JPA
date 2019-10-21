package info.owczarek.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class DateTime {

	@Id
	private long id;

	private java.sql.Date date; // 1
	private java.sql.Time time; // 2
	private java.sql.Timestamp timestamp; // 3

	@Temporal(TemporalType.DATE) // {to samo co 1
	private java.util.Date utilData; // }
	@Temporal(TemporalType.TIME) // {to samo co 2
	private java.util.Date utilTime; // }
	@Temporal(TemporalType.TIMESTAMP) // {to samo co 3
	private java.util.Date utilTimeDate; // }

	@Temporal(TemporalType.DATE)
	private java.util.Calendar utilCalendar;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public java.sql.Date getDate() {
		return date;
	}

	public void setDate(java.sql.Date date) {
		this.date = date;
	}

	public java.sql.Time getTime() {
		return time;
	}

	public void setTime(java.sql.Time time) {
		this.time = time;
	}

	public java.sql.Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(java.sql.Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public java.util.Date getUtilData() {
		return utilData;
	}

	public void setUtilData(java.util.Date utilData) {
		this.utilData = utilData;
	}

	public java.util.Calendar getUtilCalendar() {
		return utilCalendar;
	}

	public void setUtilCalendar(java.util.Calendar utilCalendar) {
		this.utilCalendar = utilCalendar;
	}

	public java.util.Date getUtilTime() {
		return utilTime;
	}

	public void setUtilTime(java.util.Date utilTime) {
		this.utilTime = utilTime;
	}

	public java.util.Date getUtilTimeDate() {
		return utilTimeDate;
	}

	public void setUtilTimeDate(java.util.Date utilTimeDate) {
		this.utilTimeDate = utilTimeDate;
	}

}
