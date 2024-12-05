package app.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Emp;
import app.repository.EmpRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class EmpAPIController {
 
	private final EmpRepository empRepository;
	
	@GetMapping("/emps")
	public List<Emp> getAllEmployees() {
	    return empRepository.findAll();
	}

	@GetMapping("/emp/{empno}")
	public ResponseEntity<Emp> getEmployeeById(@PathVariable int empno) {
	    return empRepository.findById(empno)
	            .map(ResponseEntity::ok)
	            .orElse(ResponseEntity.notFound().build());
	}


	
}
