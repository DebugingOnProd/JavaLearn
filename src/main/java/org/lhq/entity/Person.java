package org.lhq.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@AllArgsConstructor
public class Person implements Cloneable {
	private Address address;

	@Override
	public Person clone()  {
		try {
			Person clone =(Person) super.clone();
			Address address = clone.getAddress().clone();
			clone.setAddress(address);
			return clone;
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}
}
