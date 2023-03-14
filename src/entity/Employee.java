package entity;

public class Employee {
	private int id;
	private String email;
	private String password;

	public Employee() {
	}

	public Employee(String email, String password) {
		this.password = password;
		this.email = email;
	}

	public Employee(int id, String email, String password) {
		this.id = id;
		this.password = password;
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public final int getId() {
		return id;
	}

	public final void setId(int id) {
		this.id = id;
	}

	public final String getPassword() {
		return password;
	}

	public final void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", email=" + email + "]";
	}

}
