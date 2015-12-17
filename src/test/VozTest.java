package test;
import Voz.controller.*;
import Voz.technique.A4.BST;
import Voz.technique.A4.Order;
import Voz.technique.A5.Heap;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

public class VozTest {

//	@Test
//	public void test() {
//		double test = RestController.getDistance("33.7550,84.3900", "Georgia");
//		System.out.println(test);
//		assertEquals(test, 156.0);
//	}
	
	@Test
	public void distanceTest(){
		Post post0 = new Post("name0", "", "lat: 34, lng: 45");
		Post post1 = new Post("name1", "", "lat: 56, lng: 34");
		Post post2 = new Post("name2", "", "lat: 78, lng: 22");
		Post post3 = new Post("name3", "", "lat: 24, lng: 63");
		Post post4 = new Post("name4", "", "lat: 66, lng: 53");
		
		BST<Post> bst = new BST();
		bst.add(post0);
		bst.add(post1);
		bst.add(post2);
		bst.add(post3);
		bst.add(post4);
		@SuppressWarnings("unchecked")
		ArrayList<Post> nodes = new ArrayList<Post>(bst.size());
		
		nodes.add(post0);
		nodes.add(post1);
		nodes.add(post2);
		nodes.add(post3);
		nodes.add(post4);

		Heap<Post> heap = new Heap<Post>(nodes.size());
		
		for(Post elem : nodes){
			heap.enqueue(elem);
			System.out.println(elem);
		}
			
		System.out.println(heap);
	}

}
