package ma.taghia.fmdc_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.taghia.fmdc_backend.dao.IDao;
import ma.taghia.fmdc_backend.entities.Group;
import ma.taghia.fmdc_backend.repositories.GroupRepository;

@Service
public class GroupService implements IDao<Group> {

    @Autowired
    private GroupRepository repository;

    @Override
    public Group create(Group o) {
        return repository.save(o);
    }

    @Override
    public boolean delete(Group o) {
        try {
            repository.delete(o);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Group update(Group o) {
        return repository.save(o);
    }

    @Override
    public Group findById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Group> findAll() {
        return repository.findAll();
    }
}
