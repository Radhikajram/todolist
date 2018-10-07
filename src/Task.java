package src;

//***Provide a simple demonstration of the TodoList.
//* Sample data is taken from the Input .txt file
//* and a GUI view is provided.
//* 

public class Task {

private String taskName;
private String taskDesc;
private String status;
//private String taskDate;

public Task(String taskName,String taskDesc,String status)
{
	this.taskName = taskName;
	this.taskDesc = taskDesc;
	this.status = status;
}

/**
 * @return The Task name.
 */
public String getTaskName()
{
	return taskName;
}

/**
 * @return The Task Description
 */
public String getTaskDescription()
{
	return taskDesc;
}
/**
 * @return The Task Status
 */
public String getSatus()
{
	return status;
}
/**
 * @set/modify the Task satatus
 */
public void setStatus(String setStatus)
{
	status = setStatus;
}
/**
 * @set/modify the Task name
 */
public void setTaskName(String setTaskName)
{
	taskName = setTaskName;
}
/**
 * @set/modify the Task Description
 */
public void setTaskDesc(String setTaskDesc)
{
	taskName = setTaskDesc;
}
}