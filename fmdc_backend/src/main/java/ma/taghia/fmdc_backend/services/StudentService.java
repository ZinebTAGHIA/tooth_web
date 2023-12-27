package ma.taghia.fmdc_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.taghia.fmdc_backend.dao.IDao;
import ma.taghia.fmdc_backend.entities.Student;
import ma.taghia.fmdc_backend.repositories.StudentRepository;

@Service
public class StudentService implements IDao<Student>{
	
	@Autowired
	private StudentRepository repository;

	@Override
	public Student create(Student o) {
		return repository.save(o);
	}

	@Override
	public boolean delete(Student o) {
		try {
			repository.delete(o);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Student update(Student o) {
		return repository.save(o);
	}

	@Override
	public Student findById(int id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public List<Student> findAll() {
		return repository.findAll();
	}
}
