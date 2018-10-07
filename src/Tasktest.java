package src;

import org.junit.*;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Tasktest {

	@Test
	void test() {
		Task tasktest = new Task("Washing","book Laundry slot","open");
		assertEquals("Washing",tasktest.getTaskName()); 
		assertEquals("book Laundry slot",tasktest.getTaskDescription());
		assertEquals("open",tasktest.getSatus());
	}

}
