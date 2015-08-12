package org.app.test;

import static org.junit.Assert.*;

import org.app.CommentService;
import org.app.CommentServiceImp;
import org.junit.Test;

public class CommentServiceTest {

	@Test
	public void test() {
		CommentService commentService = new CommentServiceImp();
		commentService.addComment(0, "test");
		
		String s1 = commentService.getComment(0);
		String s2 = commentService.getComment(1);
		assertNotNull(s1);
		assertNull(s2);
		assertTrue(s1.equals("test"));
	}

}
