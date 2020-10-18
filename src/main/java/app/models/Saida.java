package app.models;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
public class Saida {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

    public Saida() {
		
	}

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}