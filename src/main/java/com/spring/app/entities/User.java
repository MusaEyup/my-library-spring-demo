package com.spring.app.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name = "Users")
public class User implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;

	@Column(length = 50, nullable = false)
	private String firstName;

	@Column(length = 50, nullable = false)
	private String lastName;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(length = 100, nullable = false, unique = true)
	private String username;

	@Column(length = 64, nullable = false)
	private String password;

	private boolean isAccountNonExpired;

	private boolean isAccountNonLocked;

	private boolean isCredentialsNonExpired;

	private boolean isEnabled;

	private LocalDateTime createDate = LocalDateTime.now();

	@OneToMany(mappedBy="user")
	private List<BookUserDetails> bookUserDetails;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name = "users_roles",
			joinColumns = {@JoinColumn(name = "user_id")},
			inverseJoinColumns = {@JoinColumn(name = "role_id")}
	)
	private Set<Role> roles;

	public User() {}

	public User(String firstName, String lastName, String email, String username, String password, Set<Role> roles) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;

		this.roles = roles;
		isAccountNonExpired = true;
		isAccountNonLocked = true;
		isCredentialsNonExpired = true;
		isEnabled = true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String name) {
		this.firstName = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@JsonIgnore
	public List<BookUserDetails> getBookUserDetails() {
		return bookUserDetails;
	}

	public void setBookUserDetails(List<BookUserDetails> bookUserDetails) {
		this.bookUserDetails = bookUserDetails;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		isAccountNonExpired = accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		isAccountNonLocked = accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		isCredentialsNonExpired = credentialsNonExpired;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean enabled) {
		isEnabled = enabled;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}



	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		if (isAccountNonExpired != user.isAccountNonExpired) return false;
		if (isAccountNonLocked != user.isAccountNonLocked) return false;
		if (isCredentialsNonExpired != user.isCredentialsNonExpired) return false;
		if (isEnabled != user.isEnabled) return false;
		if (!id.equals(user.id)) return false;
		if (!firstName.equals(user.firstName)) return false;
		if (!lastName.equals(user.lastName)) return false;
		if (!email.equals(user.email)) return false;
		if (!username.equals(user.username)) return false;
		if (!password.equals(user.password)) return false;
		if (!createDate.equals(user.createDate)) return false;
		if (!bookUserDetails.equals(user.bookUserDetails)) return false;
		return roles.equals(user.roles);
	}

	@Override
	public int hashCode() {
		int result = id.hashCode();
		result = 31 * result + firstName.hashCode();
		result = 31 * result + lastName.hashCode();
		result = 31 * result + email.hashCode();
		result = 31 * result + username.hashCode();
		result = 31 * result + password.hashCode();
		result = 31 * result + (isAccountNonExpired ? 1 : 0);
		result = 31 * result + (isAccountNonLocked ? 1 : 0);
		result = 31 * result + (isCredentialsNonExpired ? 1 : 0);
		result = 31 * result + (isEnabled ? 1 : 0);
		result = 31 * result + createDate.hashCode();
		result = 31 * result + bookUserDetails.hashCode();
		result = 31 * result + roles.hashCode();
		return result;
	}
}
