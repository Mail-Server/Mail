package eg.edu.alexu.csd.datastructure.mailServer;

public interface IIndex {
	/**
	 * 
	 * @param newEmail
	 * The email to be written on the index file
	 * @param des
	 * The folder which contains the index file 
	 */
	public void write(IMail newEmail,IFolder des);
	/**
	 * Used with the move or delete functions
	 * @param newEmails 
	 * A single linked list of the e-mails that would be written to the index file
	 * @param des the folder containing the index file
	 */
	public void writeAll(SLL newEmails,IFolder des);

	/**
	 * 
	 * @param des
	 * The index file to be read
	 * @param lineNum
	 * The number of the line to be read for example 1 for the first line,2 for the second line and so on
	 * @return
	 * Index object that contains the info read from the index file 
	 */
	public IndexObject readLine(IFolder des,int lineNum);

	/**
	 * 
	 * @param des
	 * The index file to be read
	 * @return
	 * A Single linked list containing 10 index objects each one containing the info of a specific email
	 */
	public SLL read10(IFolder des);
	/**
	 * 
	 * @param des
	 * The index file to be read
	 * @return
	 * A single linked list containing index objects for all the e-mails stored in the index file 
	 */
	public SLL readAll(IFolder des);

	/**
	 * 
	 * @param Indexes
	 * A single linked list containing index objects 
	 * @return
	 * A single linked list containing the corresponding e-mails for the index objects
	 */
	public SLL getEmails(SLL Indexes);
	/**
	 * 
	 * 
	 * @param attachments
	 * The attachments to be copied
	 * @param To
	 * The folder to which the attachments would be copied
	 */
	void copyAttachments(SLL attachments,IFolder To);
	/**
	 * 
	 * @param path
	 * The path of the attachment to be deleted
	 */
	void deleteAttachment(String path);
	/**
	 * 
	 * @param paths
	 * A single linked list containing the paths of the attachments to be deleted
	 */
	void deleteAttachments(SLL paths);
	/**
	 * 
	 * @param des
	 * The folder object which contains the path of the index file from which a line would be deleted
	 * @Param line
	 * The line which will be deleted
	 */
	void deleteLine(IFolder des,IFolder trash,int line);
	/**
	 * 
	 * @param des
	 * The folder object which contains the path of the index file in which a line would be edited
	 * @param line 
	 * The line which will be edited
	 */
	void setLine(IFolder des,IMail email,int line );
	/**
	 * 
	 * @param des
	 * The folder object which contains the path of the index file in which a line would be edited
	 * @param contact
	 * The Contact object which contains the new information of the contact after editing
	 * @param line 
	 * The line which will be edited 
	 */
	void setLine(IFolder des,IContact contact,int line );
}
