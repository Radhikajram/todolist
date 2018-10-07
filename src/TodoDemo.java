package src;

/**
 * A class to maintain an arbitrary number of Task details.
 * 
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class TodoDemo {
	private ArrayList<Task> inp = new ArrayList<>();

//	read the file to and load in the  Arraylist
public void inputReader() throws IOException
{
	{
		BufferedReader inpFile = new BufferedReader(new FileReader("/Users/tmp-sda-1157/Desktop/Input.txt"));
	    String str;

	    while((str = inpFile.readLine()) != null)
	    {
	    	String str1 = str.toString();
	    	String[] arr = str1.split(";");
	        inp.add(new Task(arr[0],arr[1],arr[2]));
	
	    }
	}
}

//List display method to display the list in the list frame
public void listDisplay(boolean getDisplay)
{
	if(getDisplay == true)
	{
		TaskGUI.listModel.removeAllElements();
	}
	for(Task file : inp)
	{
	TaskGUI.listModel.addElement( file.getTaskName()+ "; "+file.getTaskDescription()+" ; "+file.getSatus()+"; "+ "\n");
	}
}

//Add new Todo task.
public void addRecord(String taskName,String taskDesc,String taskStatus) throws IOException
{
	inp.add(new Task(taskName,taskDesc,taskStatus));

}



//Delte the Todo task which are done or which you don't want to maintain in the TOdo list
public void deleteRecord(int removeindex) throws IOException
{
	inp.remove(removeindex);
}


//Write back to the file which should be used when you open the Todo list task next time.
public void outputWriter() throws IOException
{
	BufferedWriter writer = new  BufferedWriter(new FileWriter("/Users/tmp-sda-1157/Desktop/Input.txt")); 

		for (Task out: inp)
		{
		writer.write(out.getTaskName() + ";"+out.getTaskDescription()+";"+out.getSatus()+ ";"+ "\n");
		}

	writer.close();
}

// Edit the Arraylist in the particular index

public void rewriteRecord(int editIndex,String taskName,String taskDesc,String taskStatus) throws IOException
{
	inp.set(editIndex,new Task(taskName,taskDesc,taskStatus));
}


}