package com.usa.ciclo3.reto4.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Historia de usuario 3.7 -Creación de usuarios administrativos.
 */
@Entity
@Table(name = "admins")
public class Admin implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2247784609374932777L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty(message = "El campo nombre es obligatorio")
	@Size(min = 3, max = 250, message = "El nombre debe tener mas de 3 letras y menos de 250")
	@Column(length = 250)
	private String name;

	@NotEmpty(message = "El email es obligatorio")
	@Size(max = 45, message = "El email no puede exceder los 45 caracteres")
	@Column(length = 45)
	@Email(message="Email inválido")
	private String email;

	@NotEmpty(message = "La contraseña es obligatoria")
	@Size(min = 8, max = 45, message = "La contraseña debe tener entre 8 y 45 caracteres")
	@Column(length = 45)
	private String password;

	// --- Getters y Setters ---

	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// Constructores

	public Admin() {

	}

	/**
	 * @param String name
	 * @param String email
	 * @param String password
	 */
	public Admin(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

}
