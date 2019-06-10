package kr.ac.hansung.cse.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.hansung.cse.model.Customer;
import kr.ac.hansung.cse.repo.CustomerRepository;

@RestController
@RequestMapping("/api")//클래스의 리퀘스트 맵핑은 /api
public class CustomerController {
	
	//로깅메세지 출력하게할 로거
	static Logger logger = LoggerFactory.getLogger(CustomerController.class);
 
	@Autowired
	//@Repository가 CustomerRepository에 있기때문에 가능한거
	CustomerRepository repository;//repository주입받는다.
 	
	//customers url로 get 메소드가 들어올때 -> 조회
	// produces=MediaType.APPLICATION_JSON_VALUE 여기서 생성되는 body가 json포맷이다. get쓸때만
	//클라이언트에 accept 라는 해더가 있는데 거기에 json이라고 되어있어야 수행된다.
	@GetMapping(value="/customers", produces=MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<List<Customer>>  getAll() {
		
		logger.debug("Calling getAll( )" );
				
		List<Customer> list = new ArrayList<>();
		//CrudRepository의 인터페이스를 보면 findAll이라는 메소드가 있음.->모든 레코드 조회
		Iterable<Customer> customers = repository.findAll();
		
		//customers를 돌면서 list에 저장한다.
		customers.forEach(list::add);
		
		//이 메소드 수행시 list를 body에 넣고 httpStatus메세지로 ok를 보낸다.
		return new ResponseEntity<List<Customer>>(list, HttpStatus.OK);
		
	}
	
	//customers url로 POST 메소드가 들어올때 -> customer 추가
	@PostMapping(value="/customers")
	//@RequestBody -> form(body부분)에서 데이터를 가져오겠다는것 -> model에서 serialized 덕에
	public ResponseEntity<Void> postCustomer(@RequestBody Customer customer) {
 
		logger.debug("Calling postCustomer( )" );
		//가져온 customer
		String firstName = customer.getFirstName();
		String lastName = customer.getLastName();
		
		//그 내용을 바탕으로 새로운 customer 객체를 만들고 
		//repository에 저장 == db에 저장//id는 자동으로 저장
		repository.save(new Customer(firstName, lastName));
	
		//리ㅡ폰스 body에 아무내용없음
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
 
	//customers/{lastName} url로 get 메소드가 들어올때 
	//->lastName을 바탕으로 customer 조회
	// produces=MediaType.APPLICATION_JSON_VALUE 여기서 생성되는 body가 json포맷이다. get쓸때만
	@GetMapping(value="/customers/{lastName}",  produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Customer>> findByLastName(@PathVariable String lastName) {
 
		logger.debug("Calling findByLastName( )" );
		//CustomerRepository에 CrudRepository에 추가로 만든 메소드 findByLastName
		List<Customer> customers = repository.findByLastName(lastName);
		//응답메세지에 조회한 customers리스트 담고, ok보냄
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}
	
	//customers/{lastName} url로 delete 메소드가 들어올때 
	@DeleteMapping(value="/customers/{id}")
	//url에 있는 id를 가져와서 id에저장할거임
	public ResponseEntity<Void> deleteCustomer(@PathVariable long id){
		
		logger.debug("Calling deleteCustomer( )" );
		//id에 해당하는 customer 삭제
		repository.delete(id);
		
		//응답 메세지에 아무것도 넣지않음
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}