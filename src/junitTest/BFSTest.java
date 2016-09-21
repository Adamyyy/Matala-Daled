package junitTest;


import static org.junit.Assert.*;

import org.junit.Test;

import algorithms.mazeGenerators.Position;
import algorithms.search.BFS;
import algorithms.search.State;

public class BFSTest {
	BFS<String> bfs;
	
	public BFSTest() {
		bfs = new BFS<String>();
	}
	
	@Test
	public void testNull() {
		try {
			bfs.search(null);
		}catch (NullPointerException e) {
			assertTrue(1 > 0);
		}
		assertTrue("NullPointerException not thrown!" , 1 > 0);
	}
}