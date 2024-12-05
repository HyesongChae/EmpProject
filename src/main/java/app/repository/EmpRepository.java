package app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entity.Emp;

@Repository
public interface EmpRepository extends JpaRepository<Emp, Integer>{

	Optional<Emp> findById(int empno);
 
}
