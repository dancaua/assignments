package stream.assignment;

public class ContactDetails {

	String address;
	String state;
	long phoneNumber;
	String email;
	public ContactDetails(String address, String state, long phoneNumber, String email) {
		super();
		this.address = address;
		this.state = state;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	@Override
	public String toString() {
		return "ContactDetails{" +
				"address='" + address + '\'' +
				", state='" + state + '\'' +
				", phoneNumber=" + phoneNumber +
				", email='" + email + '\'' +
				'}';
	}
}
