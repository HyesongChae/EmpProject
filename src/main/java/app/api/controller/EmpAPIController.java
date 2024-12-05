package app.api.controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Emp;
import app.repository.EmpRepository;
import app.util.Util;

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

	@PutMapping("/api/emp/{empno}")
	public Emp updateEmp(@RequestBody Emp updateEmp, @PathVariable Integer empno) {
		
		Emp emp = empRepository.findById(empno)
							   .orElseThrow(() -> new EntityNotFoundException("Emp not found"));
				
		return empRepository.save(
			    Emp.builder()
			       .ename(Util.getOrDefault(updateEmp.getEname(), emp.getEname()))
			       .job(Util.getOrDefault(updateEmp.getJob(), emp.getJob()))
			       .mgr(Util.getOrDefault(updateEmp.getMgr(), emp.getMgr()))
			       .hiredate(Util.getOrDefault(updateEmp.getHiredate(), emp.getHiredate()))
			       .sal(Util.getOrDefault(updateEmp.getSal(), emp.getSal()))
			       .comm(Util.getOrDefault(updateEmp.getComm(), emp.getComm()))
			       .dept(Util.getOrDefault(updateEmp.getDept(), emp.getDept()))
			       .build()
			);
	}
	
}
