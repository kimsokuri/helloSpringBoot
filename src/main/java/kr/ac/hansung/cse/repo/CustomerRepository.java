package kr.ac.hansung.cse.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import kr.ac.hansung.cse.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>{//id가 Long 타입이라
	//없어도 crud기능 이루어지고 추가적으로 넣은거임 
	List<Customer> findByLastName(String lastName);

}
