package com.springapp.mvc.service;

import com.springapp.mvc.model.Tweet;
import com.springapp.mvc.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Users: maverick
 * Date: 15/7/13
 * Time: 12:35 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class DbRepositoryService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DbRepositoryService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Users fetchUser(String username) {
        return jdbcTemplate.queryForObject("select * from users where username = ?",
                new Object[]{username}, new BeanPropertyRowMapper<Users>(Users.class));

    }

    public List<Tweet> fetchUsersTweets(String username) {
        return jdbcTemplate.query("select * from  tweet where username = ?",
                new Object[]{username}, new RowMapper<Tweet>(){
            @Override
            public Tweet mapRow(ResultSet rs, int rowNumber) throws SQLException{
                Tweet t = new Tweet();
                t.setTweetid(rs.getInt(1));
                t.setTweettext(rs.getString(2));
                t.setTimestamp(rs.getTimestamp(3).toString());
                t.setUsername(rs.getString(4));
                return t;
            }
        });
    }



    public List<String> findFollowingList(String userName)
    {
        //System.out.println(userName);
        return  jdbcTemplate.query("select Followed from following where Follower = ?",
                new Object[]{userName}, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                Users user=new Users();
                String s=resultSet.getString("Followed");
                //System.out.println(s);
              //  user.setUsername(s);
                return s;
            }
        });
    }


    public List<String> findFollowersList(String userName)
    {
        //System.out.println(userName);
        return  jdbcTemplate.query("select Follower from following where Followed = ?",
                new Object[]{userName}, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                Users user=new Users();
                String s=resultSet.getString("Follower");
                //System.out.println(s);
                //  user.setUsername(s);
                return s;
            }
        });
    }
}