import Connection.ConnectionPool;
import dao.JobDao;
import dao.impl.JobDaoImpl;
import model.entity.Job;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class Test {
    public static void main(String[] args) throws Exception {
//        ConnectionPool connectionPool = new ConnectionPool();
//        try (Connection conn = connectionPool.getConnection()) {
//            System.out.println("Connection to Store DB succesfull!");
//            int salaryBound = 10000;
//
//            String sqlQuery1 = "SELECT * FROM person " +
//                    "WHERE salary < ?";
//
//            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery1);
//
//            preparedStatement.setInt(1, salaryBound);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                int salary = resultSet.getInt("salary");
//                int id = resultSet.getInt("id");
//                String name = resultSet.getString("name");
//                System.out.println(id + " | " + name + " | " + salary);
//            }
//
//        } catch (Exception ex) {
//            System.out.println("Connection failed...");
//
//            System.out.println(ex);
//        }
        JobDao jobDao = new JobDaoImpl();
        jobDao.create(new Job(333, "succeed"));
        jobDao.update(new Job(333, "succeedUpdate"));

        System.out.println(jobDao.getById(333));
        System.out.println(jobDao.deleteById(333));
        List<Job> jobList = jobDao.findAll();
        for(Job job:jobList){
            System.out.println(job);
        }

    }
}
