package app.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.dto.EmpDto;
import app.entity.Dept;
import app.entity.Emp;
import app.repository.DeptRepository;
import app.repository.EmpRepository;
import app.util.Util;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class EmpAPIController {
 
	private final EmpRepository empRepository;
	private final DeptRepository deptRepository;
	
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

	@PostMapping("/emp/{empno}")
	public Emp createEmp(@RequestBody EmpDto empDto, @PathVariable Integer empno) {
		Dept dept = deptRepository.findById(empDto.getDeptno()).orElseThrow(null);
		
		Emp emp = Emp.builder()
			       .empno(empno)
			       .ename(empDto.getEname())
			       .job(empDto.getJob())
			       .mgr(empDto.getMgr())
			       .hiredate(empDto.getHiredate())
			       .sal(empDto.getSal())
			       .comm(empDto.getComm())
			       .dept(dept)
			       .build();
		
		return empRepository.save(emp);
	}

	@DeleteMapping("/emp/{empno}")
	public Emp deleteEmpByEmpno(@PathVariable Integer empno) {
		Emp emp = empRepository.findById(empno)
				.orElseThrow(() -> new EntityNotFoundException("Not Found the Employee"));
		empRepository.delete(emp);
		return emp;
	}

	@PutMapping("/emp/{empno}")
	public Emp updateEmp(@RequestBody Emp updateEmp, @PathVariable Integer empno) {
		
		Emp emp = empRepository.findById(empno)
							   .orElseThrow(() -> new EntityNotFoundException("Emp not found"));
				
		return empRepository.save(
			    Emp.builder()
			       .empno(empno)
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
