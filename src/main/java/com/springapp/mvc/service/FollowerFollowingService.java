package com.springapp.mvc.service;

import com.springapp.mvc.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: maverick
 * Date: 29/7/13
 * Time: 4:28 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class FollowerFollowingService {


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FollowerFollowingService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<String> fetchFollowingList(String userName)
    {
        return  jdbcTemplate.query("select followed from following where follower = ?",
                new Object[]{userName}, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("followed");
            }
        });
    }

    public List<String> fetchFollowersList(String userName)
    {
        return  jdbcTemplate.query("select follower from following where followed = ?",
                new Object[]{userName}, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("follower");
            }
        });
    }

    public List<Users> findUsersWithName(String searchname)
    {
      //  System.out.println(searchname);

        //String sql = "SELECT * FROM drawings WHERE name LIKE ?";
//
//        preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.setString(1, "%" + DT + "%");
//        resultSet = preparedStatement.executeQuery();


        return  jdbcTemplate.query("select * from users where users.name ILIKE ? or users.username ILIKE ?",
                new Object[]{'%'+searchname+'%','%'+searchname+'%'}, new RowMapper<Users>() {
            @Override
            public Users mapRow(ResultSet resultSet, int i) throws SQLException {
                Users user =new Users();
                user.setUsername(resultSet.getString("username"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                //System.out.println(user.getName());

                return user;
            }
        });
    }
}
