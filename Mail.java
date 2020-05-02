

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Mail implements IApp {
	static SLL originallist=new SLL();
	static SLL list=new SLL();
	static SLL listFilter = new SLL();
    public static void main(String[] args) throws IOException {
        Mail mm=new Mail();
        Folder f=new Folder();
        File myObj = new File("C:\\Users\\LENOVO\\Desktop\\java college\\last project\\file.txt");
        try{
            if(myObj.createNewFile()){
                System.out.println("File created : "+myObj.getName());
            }else{
                System.out.println("File already exists");
            }
        }catch (IOException e){
            System.out.println("an error occurred ");
        }
        
        f.setPath("C:\\Users\\LENOVO\\Desktop\\java college\\last project");
        Search ss=new Search();
		ss.setSr("math");
		Sort m=new Sort();
		m.setSo("date");
		mm.setViewingOptions(f, null,m);
    }

    @Override
    public boolean signin(String email, String password) {
        return false;
    }

    @Override
    public boolean signup(IContact contact) {
        return false;
    }

    @Override
    public void setViewingOptions(IFolder folder, IFilter filter, ISort sort) {
    	Folder obj3=(Folder)folder;
    	obj3.setPath(obj3.getPath()+"\\index.txt");
        Sort obj = (Sort)sort;
        Search obj2 =(Search)filter;
        try {
        File ki=new File(obj3.getPath());
        Scanner scan=new Scanner(ki);
        while (scan.hasNextLine()){
            String data =scan.nextLine();
            originallist.add(data);
        }
        scan.close();
    }catch (IOException e){
        System.out.println("error");
    }
        for(int i=0;i<originallist.size();i++) {
        	list.add(originallist.get(i));
        }

        System.out.print("this is the original ");
        originallist.show();
        if(filter!=null) {
        	String seq = obj2.getSr();
        	String type="";
        		if(seq.contains("/")) {
        			type="date";
        		}else if(seq.contains("@")) {
        			type="from";
        		}else {
        			type="subject";
        		}
        	try {
			    obj.quick(list, type);
				list.show();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	int[] indexx=obj2.binary(list, obj2.getSr());
        	for(int j=0;j<indexx.length;j++) {
        		System.out.print(indexx[j]+" ");
        	}
        	System.out.println();
        	for(int j=0;j<indexx.length;j++) {
        		listFilter.add(list.get(indexx[j]));
        	}
        	System.out.print("this is the filtered ");
        	listFilter.show();
        	if(sort!=null) {
        		try {
    			    obj.quick(listFilter,obj.getSo());
    			    listFilter.show();
    			} catch (ParseException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
        	}
        }else if(sort!=null) {
        	try {
			    obj.quick(list,obj.getSo());
			    list.show();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }

    }

    @Override
    public IMail[] listEmails(int page) {
    	Email[] em=new Email[list.size()];
    	Stacks st=new Stacks();
    	for(int i=0;i<list.size();i++) {
    		st.push(list.get(i));
    	}
    	for(int i=0;i<list.size();i++) {
    		list.set(i, st.pop());
    	}
    	int start=page*10-10;
    	int end=start+9;
    	int j=0;
    	int last=end;
    	System.out.println(list.size());
    	if(list.size()<end) {
    		 last=list.size()-1;
    	}
    	while(start<=last) {
    		String tt=(String) list.get(start);
    		Email m=new Email();  		
    		String[] ss=tt.split("—");
    		m.setPath(ss[0]);
    		m.setDate(ss[1]);
    		m.setFrom(ss[2]);
    		SLL to=new SLL();
    		to.add(ss[3]);
    		m.setTo(to);
    		m.setSubject(ss[4]);
    		m.setPriority(Integer.parseInt(ss[5]));
    		//HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH
    		//HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH
    		//HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH
    		//HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH
    		IndexFile hah=new IndexFile();
            try {
                File f=new File(m.getPath()+"\\Body.txt");
                FileReader fr=new FileReader(f);
                BufferedReader reader=new BufferedReader(fr);
                String line;
                String Body="";
                Folder des =new Folder();
                des.setPath(f.toString());
                if((hah.countLines(des)==0)){
                    Body="NOTEXT";//TODO In GUI SEE how are you going to display NOTEXT on the screen to the User
                }
                else {
                    while((line=reader.readLine())!=null){
                        Body+=line;
                        //Body+="\n";
                    }
                }
                m.setBody(Body);
                reader.close();
            }

            catch(IOException e) {
                e.printStackTrace();
            }
            //reading the paths of the attachments and storing them into the email object
            MyFileVisitor visitor=new MyFileVisitor();
            try {
                Files.walkFileTree(Paths.get(m.getPath()),visitor);
                m.setAttachments(visitor.getAttachments());//TODO in GUI attachments SLL is equal to null if there are no attachments in the email folder
            } catch (IOException e) {
                e.printStackTrace();
                e.printStackTrace();
            }
            em[j]=m;
            System.out.println(em[j].getBody());
    		start++;
    		j++;
    	}
        return em;
    }

    @Override
    public void deleteEmails(ILinkedList mails) {

    }

    @Override
    public void moveEmails(ILinkedList mails, IFolder des) {

    }

    @Override
    public boolean compose(IMail email) {
        return false;
    }
    }
