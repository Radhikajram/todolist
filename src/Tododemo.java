import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;



public class Tododemo {
	private ArrayList<Task> inp = new ArrayList<>();
  private int option,taskNo;
  private boolean setExit = false;
  private String inputText3,inputText1,inputText2;
  DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

// Display the To do list data from ArrayList
 public void displayInput()
{
  int counter = 0;
  System.out.println("Task No  "+"\t"+"Task Name           " + "\t" + "              ProjectName         " +"\t"+ "Status          "+"\t"+ " Date       ");
  System.out.println("---------"+"\t"+"--------------------" + "\t"+ "            -------------------    "+ "\t"+"------------    "+"\t"+ "-------------");
   inp.sort((Task d1,Task d2)->d1.getTaskDate().compareTo(d2.getTaskDate()));
  for(Task file : inp)
	{
    counter = counter + 1;
	 System.out.println(counter + "\t"+"         "+ file.getTaskName()+ "   \t\t"+file.getProjectName()+"   \t\t"+file.getSatus()+ "\t\t"+formatter.format(file.getTaskDate())+"\n");
	}
}

public void inputReader() throws IOException
{
    Date date = null;
		BufferedReader inpFile = new BufferedReader(new FileReader("/Users/tmp-sda-1157/Desktop/Input.txt"));
	    String str;
      try{
	    while((str = inpFile.readLine()) != null)
	    {
	    	String str1 = str.toString();
	    	String[] arr = str1.split(";");
        try{
            date = formatter.parse(arr[3]);
      }catch(ParseException p){
          System.out.println("Error while reading the Date field");
      }

      //  System.out.println("date " date);
	        inp.add(new Task(arr[0],arr[1],arr[2],date));

	    }
	}catch(IOException e) {
    e.printStackTrace();
	}

}

//Write back to the file which should be used when you open the Todo list task next time.
public void outputWriter() throws IOException
{
	BufferedWriter writer = new  BufferedWriter(new FileWriter("/Users/tmp-sda-1157/Desktop/Input.txt"));

		for (Task out: inp)
		{
		writer.write(out.getTaskName() + ";"+out.getProjectName()+";"+out.getSatus()+ ";"+ formatter.format(out.getTaskDate())+"\n");
		}

	writer.close();
}

  //Add new Todo task.

public void addRecord(String taskName,String projectName,String taskStatus,Date taskDate) throws IOException
{

////
//     date = formatter.parse(taskDate);
//  }catch(ParseException p){
//    System.out.println("Error occred while parsing Date field while adding to arraylist");
//  }

	inp.add(new Task(taskName,projectName,taskStatus,taskDate));

}

//Edit the already exsisting Todo list
public void editRecord(int editIndex,String inputField,int changeField) throws IOException
{
  Date date = null;
  if (changeField == 1)
  {
    inp.set(editIndex,new Task(inputField,inp.get(editIndex).getProjectName(),inp.get(editIndex).getSatus(),inp.get(editIndex).getTaskDate()));
  }
  if (changeField == 2)
  {
    inp.set(editIndex,new Task(inp.get(editIndex).getTaskName(),inputField,inp.get(editIndex).getSatus(),inp.get(editIndex).getTaskDate()));
  }
  if (changeField == 3)
  {
    inp.set(editIndex,new Task(inp.get(editIndex).getTaskName(),inp.get(editIndex).getProjectName(),inputField,inp.get(editIndex).getTaskDate()));
  }
  if (changeField == 4)
  {
    try{

       date = formatter.parse(inputField);
    }catch(ParseException p){
      System.out.println(" Error occured while editing the arraylist record");
    }
    inp.set(editIndex,new Task(inp.get(editIndex).getTaskName(),inp.get(editIndex).getProjectName(),inp.get(editIndex).getSatus(),date));
  }
}


//Delte the Todo task which are done or which you don't want to maintain in the TOdo list
public void deleteRecord(int removeindex) throws IOException
{
	inp.remove(removeindex);
  System.out.println("Record deleted sucessfully ");
}

}
