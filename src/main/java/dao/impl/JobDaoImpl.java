package dao.impl;

import Connection.ConnectionPool;
import dao.JobDao;
import model.entity.Job;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobDaoImpl implements JobDao {
    private final ConnectionPool connectionPool = new ConnectionPool();
    private final String CREATE_QUERY = "insert jobs(id, title) values(?,?)";
    private final String FIND_ALL_QUERY = "select * from jobs";
    private final String UPDATE_QUERY = "update jobs set title = ? where id = ?";
    private final String DELETE_QUERY = "delete from jobs where id = ?";
    private final String GET_BY_ID_QUERY = "select * from jobs where id = ?";

    public JobDaoImpl() {
    }

    @Override
    public boolean update(Job job) {
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
            preparedStatement.setString(1, job.getTitle());
            preparedStatement.setInt(2, job.getId());
            if (preparedStatement.executeUpdate() == 0) {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Connection failed...");
            System.out.println(e);
        }
        return true;
    }

    @Override
    public boolean deleteById(Integer id) {
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() == 0) {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Connection failed...");
            System.out.println(e);
        }
        return true;
    }

    @Override
    public void create(Job job) {
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY);
            preparedStatement.setInt(1, job.getId());
            preparedStatement.setString(2, job.getTitle());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Connection failed...");
            System.out.println(e);
        }

    }

    @Override
    public List<Job> findAll() {
        List<Job> jobs = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                jobs.add(new Job(resultSet.getInt("id"), resultSet.getString("title")));
            }
        } catch (SQLException e) {
            System.out.println("Connection failed...");
            System.out.println(e);
        }
        return jobs;
    }

    @Override
    public Job getById(Integer id) {
        Job job = new Job();
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                job.setId(resultSet.getInt("id"));
                job.setTitle(resultSet.getString("title"));
            }
        } catch (SQLException e) {
            System.out.println("Connection failed...");
            System.out.println(e);
        }
        return job;
    }
}
