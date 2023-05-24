class User {
  String firstName = '';
  String lastName = '';
  String email = '';
  String phoneNumber = '';
  String address = '';
  int id = 0;

  String getFirstName() {
    return this.firstName;
  }

  void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  String getLastName() {
    return this.lastName;
  }

  void setLastName(String lastName) {
    this.lastName = lastName;
  }

  String getEmail() {
    return this.email;
  }

  void setEmail(String email) {
    this.email = email;
  }

  String getPhoneNumber() {
    return this.phoneNumber;
  }

  void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  String getAddress() {
    return this.address;
  }

  void setAddress(String address) {
    this.address = address;
  }

  int getId() {
    return this.id;
  }

  void setId(int id) {
    this.id = id;
  }
}
