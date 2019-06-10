package kr.ac.hansung.cse.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity //db테이블로 실제로 저장이 된다.
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Customer implements Serializable { 
	//Customer 객체를 클라이언트로 보내기위해
	//객체를 Serializable하고, DeSerializable해야해서
	
	private static final long serialVersionUID = 4577239542252345341L;

	@Id//pk로 하겠다.
	@GeneratedValue(strategy = GenerationType.IDENTITY)//자동적으로 생성
	@Column(name="customer_id")
	private Long id;
	 
	@Column(name="firstname")
	private String firstName;
 
	@Column(name="lastname")
	private String lastName;
	
	public Customer(String firstName, String lastName) {
		//firstName과 lastName을 바탕으로 생성자 만든다.
		this.firstName = firstName;
		this.lastName = lastName;
	}

}