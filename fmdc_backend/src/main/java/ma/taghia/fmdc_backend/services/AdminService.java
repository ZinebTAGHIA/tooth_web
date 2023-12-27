package ma.taghia.fmdc_backend.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ma.taghia.fmdc_backend.dao.IDao;
import ma.taghia.fmdc_backend.entities.Admin;
import ma.taghia.fmdc_backend.repositories.AdminRepository;

@Service
public class AdminService implements IDao<Admin> {

    @Autowired
    private AdminRepository repository;

    @Override
    public Admin create(Admin o) {
        return repository.save(o);
    }

    @Override
    public boolean delete(Admin o) {
        try {
            repository.delete(o);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Admin update(Admin o) {
        return repository.save(o);
    }

    @Override
    public Admin findById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Admin> findAll() {
        return repository.findAll();
    }
}
