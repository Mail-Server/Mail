
public class Contact implements IContact{
private String name;
SLL emails;
public void setName(String name) {
	this.name=name;
}
public void setEmails(SLL emails) {
	this.emails=emails;
}
public String getName() {
	return this.name;
}
public SLL getEmails() {
	return this.emails;
}
}
