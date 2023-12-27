package ma.taghia.fmdc_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.taghia.fmdc_backend.dao.IDao;
import ma.taghia.fmdc_backend.entities.PW;
import ma.taghia.fmdc_backend.repositories.PWRepository;

@Service
public class PWService implements IDao<PW> {
	
	@Autowired
	private PWRepository repository;

	@Override
	public PW create(PW o) {
		return repository.save(o);
	}

	@Override
	public boolean delete(PW o) {
		try {
			repository.delete(o);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public PW update(PW o) {
		return repository.save(o);
	}

	@Override
	public PW findById(int id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public List<PW> findAll() {
		return repository.findAll();
	}

}
