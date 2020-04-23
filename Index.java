import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 
 * The Class is used to handle the index file 
 *
 */
public class Index implements IIndex {
	private String path;//Path of the email
	private String date;//The date on which the email was composed
	private String from;//The sender of the email
	private SLL to;//The receivers of the email
	private String subject;//The subject of the email
	private int priority;//The priority of the email
	
	
	
	/*
	 * Setters And Getters
	 */
	public void setPath(String path) {
		this.path=path;
	}
	
	public String getPath() {
		return this.path;
	}
	
	public void setDate(String date) {
		this.date=date;
	}
	
	public String getDate() {
		return this.date;
	}
	
	public void setTo(SLL to) {
		this.to=to;
	}
	
	public SLL getTo() {
		return this.to;
	}
	public void setFrom(String from) {
		this.from=from;
	}
	
	public String getFrom() {
		return this.from;
	}
	public void setSubject(String subject) {
		this.subject=subject;
	}
	
	public String getSubject() {
		return this.subject;
	}
	
	public void setPriority(int priority) {
		this.priority=priority;
	}
	public int getPriority() {
		return this.priority;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Sets the info of the email to the index object
	 * @param newEmail
	 * The email whose info will be stored in the index file 
	 */
	private Index setIndexFile(Email newEmail) {
		Index newIndexFile=new Index();
		newIndexFile.setDate(newEmail.getDate());//TODO date won't be empty even if it is a draft
		newIndexFile.setFrom(newEmail.getFrom());//TODO From cannot be empty
		if(!(newEmail.getTo()!=null)) {
		newIndexFile.setTo(newEmail.getTo());
		}
		if((newEmail.getSubject())!=null)
		newIndexFile.setSubject(newEmail.getSubject());
		newIndexFile.setPriority(newEmail.getPriority());//TODO if priority not set put it as 4 
		return newIndexFile;
	}
	/**
	 * 
	 * @param indexLine
	 * An index object that contains the info of one line of the index file 
	 * @return
	 * An Email object that contains the info of the email with the body and attachments
	 */
	private Email setEmail(Index indexLine) {
		Email newEmail=new Email();
		newEmail.setDate(indexLine.getDate());
		newEmail.setFrom(indexLine.getFrom());
		newEmail.setPriority(indexLine.getPriority());
		newEmail.setSubject(indexLine.getSubject());
		newEmail.setTo(indexLine.getTo());
		newEmail.setPath(indexLine.getPath());
		return newEmail;
	}
	@Override
	public void write(Email newEmail,Folder des) {//TODO I assume that the path in the folder is the path of the index file is that right ?
		Index newIndexFile=new Index();
		newIndexFile=setIndexFile(newEmail);
		newIndexFile.setPath(newEmail.getPath());
		File indexFile=new File(des.getPath());//Get the path of the index file 
		try {
		FileWriter writer=new FileWriter(indexFile,true);
		writer.append(newIndexFile.getPath());
		writer.append("—");
		writer.append(newIndexFile.getDate());
		writer.append("—");
		writer.append(newIndexFile.getFrom());
		writer.append("—");
		if((newEmail.getTo())==null) {//In drafts,the receivers may not be specified
			writer.append("NORECEIVERS");
		}
		else {
			for(int i=0;i<newIndexFile.getTo().size();i++) {
				writer.append((String)newIndexFile.getTo().get(i));
				if(i!=(newIndexFile.getTo().size()-1))
				writer.append("˜");
			}
		}
		writer.append("—");
		if((newEmail.getSubject())==null) {
			writer.append("NOSUBJECT");
		}
		else {
			writer.append(newIndexFile.getSubject());
		}
		writer.append("—");
		writer.append(Integer.toString((newIndexFile.getPriority())));	
		writer.append("\n");
		writer.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void writeAll(SLL newEmails,Folder des) {
			for(int i=0;i<newEmails.size();i++) {
				write((Email)newEmails.get(i),des);
			}
	}
	
	@Override
	public Index readLine(Folder des,int lineNum) {//TODO EMPTY INDEX FILE AND EMPTY BODY TEXT it should be handled in the GUI using the count lines method
		Index indexFile =new Index();
		try {
			FileReader indexFilePath=new FileReader(des.getPath());
			BufferedReader reader=new BufferedReader(indexFilePath);
			for(int i=1;i<lineNum;i++) {//We start from 1 because lineNum start from 1 also
				reader.readLine();//Skip that line
			}
			String line=reader.readLine();//A string which contains the contents of a line of the index file
			String [] Fields=line.split("—");//An array contains the fields of the index file 
			indexFile.setPath(Fields[0]);
			indexFile.setDate(Fields[1]);
			indexFile.setFrom(Fields[2]);
			String [] receiversArr=Fields[3].split("˜");//separate the receivers of the email
			//Make a SLL of the receivers
			SLL receiversSLL=new SLL();
			for(int i=0;i<receiversArr.length;i++) {
				receiversSLL.add(receiversArr[i]);
			}
			indexFile.setTo(receiversSLL);
			indexFile.setSubject(Fields[4]);//TODO if the receivers are NORECEIVERS or if the subject is NOSUBJECT we need to handle those cases
			//when converting the draft into an email and a solution is to display the contents of the draft onto the composing GUI to continue
			//composing  and you shall choose whether to overwrite the old draft or make a new one with a new Data and user can choose from all 
			//the given dates of the drafts 
			indexFile.setPriority(Integer.parseInt(Fields[5]));
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return indexFile;
	}

	@Override
	public SLL read10(Folder des) {//TODO In GUI special case if the index file doesn't contain 10 lines,you should check first using count lines
		SLL indices=new SLL();//A single linked list which contains 10 index objects 
		for(int i=0;i<10;i++) {
			Index indexLine=readLine(des,i+1);//i+1 because we send the right number of the line such as 1 for the first line and 2 for the secomd 
			indices.add(indexLine);
		}
		
		return indices;
	}
	/**
	 *
	 * @param des
	 * The index file which this function will count the number of lines in 
	 * @return
	 * The number of lines in the text index file 
	 */
	private int countLines(Folder des) {
		int lines=0;
		try {
			FileReader fr=new FileReader(des.getPath());
			BufferedReader reader=new BufferedReader(fr);
			while((reader.readLine())!=null) {
				lines++;
			}
			reader.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return lines;
	}
	@Override
	public SLL readAll(Folder des) {
		SLL indices=new SLL();//A single linked list which contains index objects  that hold all the information in the text index file
		try {
			FileReader fr=new FileReader(des.getPath());
			BufferedReader reader=new BufferedReader(fr);
			int linesNum=countLines(des);
			
			for(int i=0;i<linesNum;i++) {
				Index indexLine=readLine(des,i+1);
				indices.add(indexLine);
			}
			reader.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return indices;
	}

	@Override
	public SLL getEmails(SLL Indexes) {
		SLL Emails=new SLL();
		for(int i=0;i<Indexes.size();i++) {
			Email newEmail=new Email();
			newEmail=setEmail((Index)Indexes.get(i));
			
			//reading the body of the email and putting it into the email object
			try {
				File f=new File(newEmail.getPath()+"\\Body.txt");
				FileReader fr=new FileReader(f);
				BufferedReader reader=new BufferedReader(fr);
				String line;
				String Body="";
				Folder des =new Folder();
				des.setPath(f.toString());
				if((countLines(des)==0)){
					Body="NOTEXT";//TODO In GUI SEE how are you going to display NOTEXT on the screen to the User 
				}
				else {
				while((line=reader.readLine())!=null){
				Body+=line;
				Body+="\n";
				}
				}
				newEmail.setBody(Body);
				reader.close();
			}
			
			catch(IOException e) {
				e.printStackTrace();
			}
			//reading the paths of the attachments and storing them into the email object
			MyFileVisitor visitor=new MyFileVisitor();
			try {
				Files.walkFileTree(Paths.get(newEmail.getPath()),visitor);
				newEmail.setAttachments(visitor.getAttachments());//TODO in GUI attachments SLL is equal to null if there are no attachments in the email folder 
			} catch (IOException e) {
				e.printStackTrace();
				e.printStackTrace();
			}
			Emails.add(newEmail);
		}
		return Emails;
	}
	
}
