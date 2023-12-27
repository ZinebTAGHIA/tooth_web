package ma.taghia.fmdc_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.taghia.fmdc_backend.dao.IDao;
import ma.taghia.fmdc_backend.entities.StudentPW;
import ma.taghia.fmdc_backend.repositories.StudentPWRepository;

@Service
public class StudentPWService implements IDao<StudentPW>{
	
	@Autowired
	private StudentPWRepository repository;

	@Override
	public StudentPW create(StudentPW o) {
		return repository.save(o);
	}

	@Override
	public boolean delete(StudentPW o) {
		try {
			repository.delete(o);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public StudentPW update(StudentPW o) {
		return repository.save(o);
	}

	@Override
	public StudentPW findById(int id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public List<StudentPW> findAll() {
		return repository.findAll();
	}

}
