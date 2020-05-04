package eg.edu.alexu.csd.datastructure.mailServer;

public interface IIndex {
	/**
	 * 
	 * @param newEmail
	 * The email to be written on the index file
	 * @param des
	 * The folder which contains the index file 
	 */
	public void write(Mail newEmail,Folder des);
	/**
	 * Used with the move or delete functions
	 * @param newEmails 
	 * A single linked list of the e-mails that would be written to the index file
	 * @param des the folder containing the index file
	 */
	public void writeAll(SLL newEmails,Folder des);

	/**
	 * 
	 * @param des
	 * The index file to be read
	 * @param lineNum
	 * The number of the line to be read for example 1 for the first line,2 for the second line and so on
	 * @return
	 * Index object that contains the info read from the index file 
	 */
	public IndexObject readLine(Folder des,int lineNum);

	/**
	 * 
	 * @param des
	 * The index file to be read
	 * @return
	 * A Single linked list containing 10 index objects each one containing the info of a specific email
	 */
	public SLL read10(Folder des);
	/**
	 * 
	 * @param des
	 * The index file to be read
	 * @return
	 * A single linked list containing index objects for all the e-mails stored in the index file 
	 */
	public SLL readAll(Folder des);

	/**
	 * 
	 * @param Indexes
	 * A single linked list containing index objects 
	 * @return
	 * A single linked list containing the corresponding e-mails for the index objects
	 */
	public SLL getEmails(SLL Indexes);
	void copyAttachments(SLL attachments, Folder To);

}
