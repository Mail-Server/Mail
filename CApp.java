import java.io.*;
import java.util.Scanner;
import javax.swing.*;
public class CApp implements IApp{
Scanner reader=new Scanner(System.in);
UserAccount currentUser;
	/**
	 * Used to check if the character is a number or an alphabet.
	 * @param  symbol .
	 * @return a boolean which determines whether the input character is an alphabetical character or numeral value .
	 * 
	 */
	private boolean isAlnum(char symbol) {
		
		if(isNum(symbol)||isAlpha(symbol)){
			return true;
		}
		return false;
	}
	
	
	/**
	 *Check if the given character is alphabetical or not
	 * @param  symbol
	 * @return a boolean to determine whether the input character is an alphabetical character
	 */
	
	private boolean isAlpha(char symbol) {
		
		if((symbol>=65&&symbol<=90)||(symbol>=97&&symbol<=122)) {
			return true;
		}
		return false;
	}
	
	/**Check if the given character is a digit or not
	 * @param symbol
	 * @return a boolean to determine whether the input character is a a digit or not 
	 */
	
	private boolean isNum(char symbol) {
		
		
		
		
		if((symbol>=48&symbol<=57)) {
			return true;
		}
		return false;
	}
	/**
	 * Checks if the given email is formed correctly or not 
	 * @param Email
	 * Check
	 * @return
	 */
public boolean checkEmail(String Email) {
	if(Email.length()==0) {
		JOptionPane.showMessageDialog(null,"The Email field was left blank");
		return false;
	}
	else if(!Email.endsWith("@mail.com")) {
		JOptionPane.showMessageDialog(null, "The email must contain @mail.com");
		return false;
	}
	String EmailWOM=Email.substring(0,Email.lastIndexOf('@'));//The EmailWOM is without @mail.com
	if(EmailWOM.length()==0) {
		JOptionPane.showMessageDialog(null, "The email provided was empty and only contains @mail.com");
		return false;
	}
	if(EmailWOM.contains("@mail.com")) {
		JOptionPane.showMessageDialog(null, "You cannot add @mail.com more than once");
		return false;
	}
	if(!(isAlnum(EmailWOM.charAt(0)))) {
		JOptionPane.showMessageDialog(null, "The email must start with alphabets and numbers only");
		return false;
	}
	boolean containAlpha=false;
	for (int i=0;i<EmailWOM.length();i++) {
		if(isAlnum(EmailWOM.charAt(i))) {
			if(isAlpha(EmailWOM.charAt(i))) {
				containAlpha=true;
			}
		}
		if(!(isAlnum(EmailWOM.charAt(i)))) {
			JOptionPane.showMessageDialog(null, "The email must contain only alphabets and numbers and no spaces are allowed");
			return false;
		}
	}
	if(!containAlpha) {
		return false;
	}
	return true;
}
/**
 * Takes the email address from the user for the sign up operation
 * @param newAccount
 * A new email address to be set in the sign up operation for the new user
 * @return 
 * A boolean to determine whether the email was created successfully or not 
 */
public boolean setUserEmail(String email) {
	boolean success=true;
	File newUser=new File("Accounts\\"+ email);
	if(newUser.exists()) {//Check if the email already exists
		JOptionPane.showMessageDialog(null, "Email already exists");
		return false;
	}
	else {
		newUser.mkdirs();
		String dirsarray[]=new String [] {"\\Inbox","\\Trash","\\Drafts","\\Sent"};
		for(String dir:dirsarray) {//Make the directories Inbox ,trash , drafts and sent for the new email
			File newDirectory=new File(newUser.getPath() + dir);
			newDirectory.mkdirs();
			File indexFile=new File(newDirectory.getPath()+"\\Index.txt");
			try {
			indexFile.createNewFile();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	return success;
}
/**
 * 
 * @param newEmail
 *  A new email address to be set in the sign up operation for the new user
 * @return
 * return a boolean to determine whether the email has been made or not
 */

public boolean signup(UserAccount newEmail) {
		boolean success=checkEmail(newEmail.getEmail())&&setUserEmail(newEmail.getEmail());
		if(!success) {
			return success;
		}
		else {
			currentUser=newEmail;
			String emailPath="Accounts\\"+newEmail.getEmail();
			File emailInfo=new File(emailPath +"\\EmailInfo.txt");
			try {
				FileWriter write=new FileWriter(emailInfo);
				write.write(newEmail.getName());
				write.write("\n");
				write.append(newEmail.getEmail());
				write.append("\n");
				write.append(newEmail.getPassword());
				write.close();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
	return success;
}



/*******
 * @param email 
 * The email of the user
 * @param password 
 * The password of the user
 * @return 
 * A boolean to determine whether the email information are right or not
 * 
 */
public boolean signin(String email,String password) {
	if(email.length()==0||password.length()==0) {
		JOptionPane.showMessageDialog(null,"The email field is left blank");
		return false;
	}
	File existingEmail=new File("Accounts\\"+ email);
	boolean rightEmail=true;//A boolean which check if the given email and password are right or not
	if(existingEmail.exists()) {//Check if the email exists
		
		
		String pass="";//The password that is stored in the email directory
		
		try {
		FileReader emailInfo=new FileReader(existingEmail.getPath() + "\\EmailInfo.txt");
		BufferedReader read=new BufferedReader(emailInfo);
		for(int i=0;i<2;i++) {//Skip the first 2 lines which are the name and email of the user
			pass=read.readLine();
		}
		pass=read.readLine();
		read.close();
		}
		
		
		catch (IOException e) {
			e.printStackTrace();
		}
		if(!(pass.equals(password))) {//If the entered password doesn't match the stored password
			JOptionPane.showMessageDialog(null, "Wrong password");
			rightEmail=false;
		}
	
	}
	
	else {//The email doesn't exist
		JOptionPane.showMessageDialog(null, "The entered email doesn't exist");
		rightEmail=false;
	}
	if(rightEmail) {
		currentUser.setEmail(email);
	}
	return rightEmail;
}
/**
 * 
 * @param newEmail
 * The email being composed
 * @return A boolean to determine if the body or the receiver of the email have not been specified in case of composing the email and not sending as draft
 */

private boolean checkEmpty(Email newEmail) {
	boolean isEmpty=false;
	if(newEmail.getFolder()=="Sent") {
		if(newEmail.getBody().length()==0||newEmail.getTo().size()==0) {
			isEmpty=true;
		}
	}
	else {
		isEmpty=false;
	}
	return isEmpty;
}
private boolean setTo(SLL to) {
		for(int i=0;i<to.size();i++) {
		File receiver=new File("Accounts\\"+(String)to.get(i));
		if(!receiver.exists()) {
			return false;
		}
	}
	return true;
}
/**
 * Sets the subject of the composed email
 * @param newEmail
 * The email which is being composed 
 */
private boolean setSubject(String subject) {
	if(subject.contains("—")) {
		return false;
	}
	return true;
}
/**
 * Sets the priority of the composed email
 * @param newEmail
 * The email which is being composed 
 */
//TODO Combobox to choose the priority of the email
private boolean setPriority(String priority,Email newEmail) {
	if(priority.length()==0) {
		newEmail.setPriority(4);
		return true;
	}
	try {
	switch(Integer.parseInt(priority)) {
	case 1:
	case 2:
	case 3:
	case 4:
		return true;
	default:
		
		return false;//TODO Message to say that the priority must be 1,2,3 or 4
	}
	}
	catch(Exception e) {
		return false;//TODO Message to say that the priority must be 1,2,3 or 4 
	}
	}
/**
 * Sets the attachments of the Email being composed
 * @param newEmail
 * The email being composed
 */
private void setAttachments(SLL attachments) {
	
}


@Override
public boolean signup(IContact contact) {
	// TODO Auto-generated method stub
	return false;
}
 
/*public boolean compose(Email newEmail) {
isRight=setSubject(newEmail.getSubject())||setPriority(newEmail.getPriority())||setBody(newEmail.getBody());
//TODO Add setTo to isRight
}*/
}
