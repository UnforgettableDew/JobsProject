package dao;

import model.entity.Job;

import java.util.List;

public interface Dao<Entity, id> {
    public boolean update(Entity entity);

    public boolean deleteById(id id);

    public void create(Entity entity);

    public List<Job> findAll();

    public Job getById(id id);
}
