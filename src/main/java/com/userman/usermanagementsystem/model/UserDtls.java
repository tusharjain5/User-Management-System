package com.userman.usermanagementsystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class UserDtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "username")
	private String username;

	@Column(name = "email")
	private String email;

	@Column(name = "address")
	private String address;

	@Column(name = "password")
	private String password;
	
	@Column(name = "role")
	private String role;

	@Column(name = "phonenumber")
	private String phonenumber;

	@Column(name = "accountNonLocked", nullable = false)
	private boolean accountNonLocked = true;

	@Column(name = "enabled", nullable = false)
	private boolean enabled = true;
	
	@Column(name = "verificationcode")
	private String verificationcode;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getVerificationcode() {
		return verificationcode;
	}

	public void setVerificationcode(String verificationcode) {
		this.verificationcode = verificationcode;
	}

	public UserDtls(int id, String username, String email, String address, String password, String role,
			String phonenumber, boolean accountNonLocked, boolean enabled, String verificationcode) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.address = address;
		this.password = password;
		this.role = role;
		this.phonenumber = phonenumber;
		this.accountNonLocked = accountNonLocked;
		this.enabled = enabled;
		this.verificationcode = verificationcode;
	}

	public UserDtls() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "UserDtls [id=" + id + ", username=" + username + ", email=" + email + ", address=" + address
				+ ", password=" + password + ", role=" + role + ", phonenumber=" + phonenumber + ", accountNonLocked="
				+ accountNonLocked + ", enabled=" + enabled + ", verificationcode=" + verificationcode + "]";
	}
	

		
	
	
	
	
	
	
}
