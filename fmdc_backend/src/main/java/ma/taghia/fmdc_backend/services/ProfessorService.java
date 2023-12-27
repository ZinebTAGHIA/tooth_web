package ma.taghia.fmdc_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.taghia.fmdc_backend.dao.IDao;
import ma.taghia.fmdc_backend.entities.Professor;
import ma.taghia.fmdc_backend.repositories.ProfessorRepository;

@Service
public class ProfessorService implements IDao<Professor>{
	
	@Autowired
	private ProfessorRepository repository;

	@Override
	public Professor create(Professor o) {
		return repository.save(o);
	}

	@Override
	public boolean delete(Professor o) {
		try {
			repository.delete(o);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Professor update(Professor o) {
		return repository.save(o);
	}

	@Override
	public Professor findById(int id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public List<Professor> findAll() {
		return repository.findAll();
	}
}
