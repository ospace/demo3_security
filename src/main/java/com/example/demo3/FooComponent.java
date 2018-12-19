package com.example.demo3;

import org.springframework.stereotype.Component;

//@Component
public class FooComponent {
	
	FooComponent() {
		throw new RuntimeException("foo");
	}

}
