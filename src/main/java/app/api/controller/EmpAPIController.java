package app.api.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Emp;
import app.repository.EmpRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class EmpAPIController {
 
	private final EmpRepository empRepository;
	
	@DeleteMapping("/api/emp/{empno}")
	public Emp deleteEmpByEmpno(@PathVariable Integer empno) {
		Emp emp = empRepository.findById(empno)
				.orElseThrow(() -> new EntityNotFoundException("Not Found the Employee"));
		empRepository.delete(emp);
		return emp;
	}
	
}
