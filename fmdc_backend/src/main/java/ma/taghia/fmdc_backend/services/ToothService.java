package ma.taghia.fmdc_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.taghia.fmdc_backend.dao.IDao;
import ma.taghia.fmdc_backend.entities.Tooth;
import ma.taghia.fmdc_backend.repositories.ToothRepository;

@Service
public class ToothService implements IDao<Tooth>{
	
	@Autowired
	private ToothRepository repository;

	@Override
	public Tooth create(Tooth o) {
		return repository.save(o);
	}

	@Override
	public boolean delete(Tooth o) {
		try {
			repository.delete(o);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Tooth update(Tooth o) {
		return repository.save(o);
	}

	@Override
	public Tooth findById(int id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public List<Tooth> findAll() {
		return repository.findAll();
	}
}
