package ironcycle.api.model.motorcycle;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "motorcycles")
@Entity(name = "Motorcycle")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class Motorcycle {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String brand;
	private String model;
	private String year;
	private String plan;
	private String plate;
	
	public Motorcycle(@Valid DataRegistrationMotorcycle motorData) {
		this.name = motorData.name();
		this.brand = motorData.brand();
		this.model = motorData.model();
		this.year = motorData.year();
		this.plan = motorData.plan();
		this.plate = motorData.plate();
	}
	
	public void updateMotorcycle(@Valid DataUpdateMotorcycle dataUpdateMotorcycle) {
		if (dataUpdateMotorcycle.name() != null) {
			this.name = dataUpdateMotorcycle.name();
		}
		
		if (dataUpdateMotorcycle.brand() != null) {
			this.brand = dataUpdateMotorcycle.brand();
		}
		
		if (dataUpdateMotorcycle.model() != null) {
			this.model = dataUpdateMotorcycle.model();
		}
		
		if (dataUpdateMotorcycle.year() != null) {
			this.year = dataUpdateMotorcycle.year();
		}
		
		if (dataUpdateMotorcycle.plan() != null) {
			this.plan = dataUpdateMotorcycle.plan();
		}
		
		if (dataUpdateMotorcycle.plate() != null) {
			this.plate = dataUpdateMotorcycle.plate();
		}
	}

}
