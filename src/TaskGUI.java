package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class TaskGUI {
	public static DefaultListModel<String> listModel = new DefaultListModel<>();

	private static final int EXIT_ON_CLOSE = 0;
	private String nameOfTask;
	private String descOfTask;
	private String statusOfTask;
	private JList<String> inputFile;
	private JFrame frame1,frame2;
	private JPanel firstpanel,savepanel;
	private TodoDemo demo = new TodoDemo();

 	 
 	 boolean updateStatus = false;
 	 boolean addStatus = false;
 	 boolean firstTimeDisplay = false;
 	 
 	 private String[] previousValues;
 	 JButton add,edit,delete,exit,save,saveexit;
 	 JLabel  task,desc,status;
 	 JTextField taskt,desct,statust;
	
	
	public TaskGUI() throws IOException
	{
	}
//
	  	
//first method to be called for the GUI
	public void entryPoint() throws IOException
	{
// instantiate the frame
		initFrame();
// instantiate the panel
		initPanel();
// Display the list in the panel which is in the frame
		showList();	
// Add the panel to the frame
		addPanel2frame(frame1,firstpanel);
// Set the first frame visible
		frameVisibility(frame1,true);
// Default frame close operation
		frameCloseoperation(frame1);
   		  	 
// Add button is pressed it will ask the user to enter the TaskName, Task Description, and Task Status.
		 add.addActionListener((ActionListener) new ActionListener() { 
	      public void actionPerformed(ActionEvent e) {
				addRecord();
			          }
			      }); 
			
//Save button is pressed save the user input and go back to the previous screen
		 save.addActionListener((ActionListener) new ActionListener() {
	      public void actionPerformed(ActionEvent ae)
	      {
	    	  saveRecord();
	        	}
	          });
	    		     
// //Exit button is pressed go back to previous screen
//	
		  exit.addActionListener((ActionListener) new ActionListener() {
	      public void actionPerformed(ActionEvent b)
	        {	
	    	  exitRecord();
	        }
	          });
// // Save & Exit option will write the output file and exit the screen.
//		     
		  saveexit.addActionListener((ActionListener) new ActionListener() {
	      public void actionPerformed(ActionEvent c)
	      {
	    	  saveFileandexit();
	      }
		  });	     
// Remove the element from the To DO List
//	     
        inputFile.addListSelectionListener(new ListSelectionListener() {
        public void valueChanged(ListSelectionEvent c) 
        {
            if (!c.getValueIsAdjusting())
            {
           	final List<String> selectedValuesList = inputFile.getSelectedValuesList();
            String str = selectedValuesList.toString();
            previousValues = str.split(";");
          
    	delete.addActionListener((ActionListener) new ActionListener()
  	    {   
	     public void actionPerformed(ActionEvent c)
	   	  {
	    	 deleteRecord();
   	  	   } 
        });
  // edit action edit.addactionlistener
            }
        }
            });    
	
}

//instantiate the JFrame
	private void initFrame()
	{
		frame1 = new JFrame();
		frame2 = new JFrame();
		frameProperties(frame1, "To Do List",400,400);
		frameProperties(frame2, "ADD/EDIT TO DO LIST",500,300);	
	}
//instantiate the JPane
		private void initPanel()
		{
			firstpanel = new JPanel();
			savepanel = new JPanel();
// Instantiate the button
			initButton();
// Instantiate the label and text
			initLabeltext();
// assign element to the first screen panel
			firstScreenelement();
// assign element to the second screen panel
			secondScreenelement();
		}
	private void frameProperties(JFrame Jf, String title, int height, int width)
	{
		Jf.setTitle(title);
	    Jf.setSize(height,width);	
	}
//first screen element - Todo list Screen
	private void firstScreenelement()
	{
		  inputFile = new JList<>(listModel);
	  	  firstpanel.add(inputFile);
		  firstpanel.add(add);
		  firstpanel.add(edit);
		  firstpanel.add(delete);
		  firstpanel.add(saveexit);
	}
//second screen element - Add/Edit list Screen
		private void secondScreenelement()
		{
		  savepanel.add(task);
		  savepanel.add(taskt);
		  savepanel.add(desc);
		  savepanel.add(desct);
		  savepanel.add(status);
		  savepanel.add(statust);
		  savepanel.add(save);
		  savepanel.add(exit);
		}
//Show the list from the file
		private void showList( )
		{
			try {
		 	 demo.inputReader();
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  	 demo.listDisplay(firstTimeDisplay);
		  	 firstTimeDisplay = true;		
		}
//instantiate button for the panel
		private void initButton()
		{
			add = new JButton("ADD");
		    edit = new JButton("EDIT");
			delete = new JButton("DELETE");
			exit = new JButton("EXIT");
			save = new JButton("SAVE");
			saveexit = new JButton("SAVE & EXIT");	

		}
//instantiate text and label fields
		private void initLabeltext()
		{
			task =  new JLabel("Task Name");
			taskt = new JTextField(35);
			desc =  new JLabel("Task Description");
			desct = new JTextField(35);
			status =  new JLabel("     Status     ");
			statust = new JTextField(35);
		}
// add panel to frame
		private void addPanel2frame(JFrame JF,JPanel JP)
		{
			  JF.add(JP);
		}
//Frame Visibility
		private void frameVisibility(JFrame JF,boolean b)
		{
	   		  JF.setVisible(b);
		}
//Frame Default Close operation
		private void frameCloseoperation(JFrame JF)
		{
			  JF.setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
//Frame Dispose
		private void frameDispose(JFrame JF)
		{
			JF.dispose();
		}
//Add record to the list
		private void addRecord()
		{
			frameDispose(frame1);
			frameVisibility(frame1,false);
			addPanel2frame(frame2,savepanel);
			frameVisibility(frame2,true);
			frameCloseoperation(frame2);
		}
// Save record to the list
		private void saveRecord()
		{
  		  nameOfTask = taskt.getText();
  		  descOfTask = desct.getText();
  		  statusOfTask = statust.getText();

  		  try {
				demo.addRecord(nameOfTask,descOfTask,statusOfTask);
				demo.listDisplay(firstTimeDisplay);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			frameDispose(frame2);
			frameVisibility(frame1,true);
		}
// Exit record from the second frame
		private void exitRecord()
		{
		  frameDispose(frame2);
		  frameVisibility(frame1,true);
		}
//Save the file from the list and exit the file
		private void saveFileandexit()
		{
	  		  try {
					demo.outputWriter();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frameDispose(frame1);
		}
//Delete the selected record from the list
		private void deleteRecord() 
		{
 	  	   
			try {
			demo.deleteRecord(inputFile.getSelectedIndex());
			demo.listDisplay(true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}	
		}
}