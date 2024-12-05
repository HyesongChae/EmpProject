package app.dto;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class EmpDto {
	private Integer empno;
	private String ename;
	private String job;
	private Integer mgr;
	private LocalDate hiredate;
	private Double sal;
	private Double comm;
	private Integer deptno; 
}
