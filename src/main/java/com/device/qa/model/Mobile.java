package com.device.qa.model;
import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.*;
@Entity
@Table(name = "mobile")
public class Mobile implements Serializable {

    private static final long serialVersionUID = 432154291451321L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "mobile_name")
    private String mobileName;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "availability_status")
    private AvailabilityStatus availabilityStatus;

	public Mobile(Long id, String mobileName, AvailabilityStatus availabilityStatus) {
		super();
		this.id = id;
		this.mobileName = mobileName;
		this.availabilityStatus = availabilityStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMobileName() {
		return mobileName;
	}

	public void setMobileName(String mobileName) {
		this.mobileName = mobileName;
	}

	public AvailabilityStatus getAvailabilityStatus() {
		return availabilityStatus;
	}

	public void setAvailabilityStatus(AvailabilityStatus availabilityStatus) {
		this.availabilityStatus = availabilityStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(availabilityStatus, id, mobileName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mobile other = (Mobile) obj;
		return availabilityStatus == other.availabilityStatus && Objects.equals(id, other.id)
				&& Objects.equals(mobileName, other.mobileName);
	}

	@Override
	public String toString() {
		return "Mobile [id=" + id + ", mobileName=" + mobileName + ", availabilityStatus=" + availabilityStatus + "]";
	}
   
}
