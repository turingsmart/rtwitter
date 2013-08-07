package com.springapp.mvc.service;

import com.springapp.mvc.model.Tweet;
import com.springapp.mvc.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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


    public String changeFollowingStatus(String follower, String followed){
        String isFollowed = jdbcTemplate.queryForObject("select count(*) from following where follower = ? and followed = ?",
                new Object[]{follower,followed}, String.class);

        if(Integer.parseInt(isFollowed) == 1)
        {
            jdbcTemplate.update("Delete from following where follower = ? and followed = ?" , new Object[]{follower,followed});
            return follower+" has un-followed "+followed;
        }
        else
        {
            jdbcTemplate.update("insert into following (follower, followed) values(?,?)", new Object[]{follower,followed});
        }
        return follower+" has followed "+followed;
    }

    public List<Tweet> fetchTimeLine(String userName, int tweetSeen)
    {
        int tweetLowerLimit = tweetSeen *10;
        int tweetUpperLimit = tweetLowerLimit + 10;

        return  jdbcTemplate.query("select tweet.timestamp as ts, tweet.username as username, tweet.tweettext as tweet, tweet.tweetid as ti " +
                "from tweet join following ON following.follower=? and following.followed=tweet.username ORDER BY ts DESC LIMIT "+tweetUpperLimit+" OFFSET "+tweetLowerLimit,
                new Object[]{userName},
                new RowMapper<Tweet>() {
                    @Override
                    public Tweet mapRow(ResultSet resultSet, int i) throws SQLException {
                        Tweet t = new Tweet();
                        t.setUsername(resultSet.getString("username"));
                        t.setTweettext(resultSet.getString("tweet"));
                        t.setTimestamp(resultSet.getString("ts"));
                        t.setTweetid(resultSet.getInt("ti"));
                        return t;
                    }
                });
    }

    public Users fetchUser(String username) {
        try{
            Users u =  jdbcTemplate.queryForObject("select * from users where username = ?",
                new Object[]{username}, new BeanPropertyRowMapper<Users>(Users.class));
            return u;
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public Users fetchUserByAccessToken(String accessToken){
        System.out.print("Reaching to find user by accesstoken");
        return jdbcTemplate.queryForObject("select * from users where token = ?",
                new Object[]{accessToken}, new BeanPropertyRowMapper<Users>(Users.class));
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

//    public List<String> findFollowedList(String userName)
//    {
//        return  jdbcTemplate.query("select followed from following where follower = ?",
//                new Object[]{userName}, new RowMapper<String>() {
//            @Override
//            public String mapRow(ResultSet resultSet, int i) throws SQLException {
//                return resultSet.getString("followed");
//            }
//        });
//    }
//
//
//    public List<String> findFollowersList(String userName)
//    {
//        return  jdbcTemplate.query("select follower from following where followed = ?",
//                new Object[]{userName}, new RowMapper<String>() {
//            @Override
//            public String mapRow(ResultSet resultSet, int i) throws SQLException {
//                return resultSet.getString("follower");
//            }
//        });
//    }

    public String findIfThereAreNewTweets(String userName, String firstTweetOnPage){
        Tweet t = jdbcTemplate.queryForObject("select tweet.timestamp as timestamp, tweet.username as username, tweet.tweettext as tweettext, tweet.tweetid as tweetid " +
                "from tweet join following ON following.follower=? and following.followed=tweet.username ORDER BY timestamp DESC LIMIT 1",
                new Object[]{userName}, new BeanPropertyRowMapper<Tweet>(Tweet.class));
        System.out.println(jdbcTemplate.queryForObject("select tweet.timestamp as timestamp, tweet.username as username, tweet.tweettext as tweettext, tweet.tweetid as tweetid " +
                "from tweet join following ON following.follower=? and following.followed=tweet.username ORDER BY timestamp DESC LIMIT 1",
                new Object[]{userName}, new BeanPropertyRowMapper<Tweet>(Tweet.class)));
        System.out.println("Tweet User"+userName);
        System.out.println("Tweet Fetched"+t.getTweetid());
        System.out.println("Tweet on Page" + firstTweetOnPage);
        if (t.getTweetid() == Integer.parseInt(firstTweetOnPage))
            return "0";
        return "1";
    }


    //Kartik
    public int findRelation(String firstUser,String secondUser )
    {
        int result=0;

        if(jdbcTemplate.queryForObject("select count(*) from following where followed=? and follower=?",new Object[]{firstUser,secondUser},Integer.class)==1)
        {
            //following -so display unfollow button
            result=1;
        }
        else result=2;


        return result;

    }

    public void postTweet(Tweet tweet)
    {
        jdbcTemplate.update("insert into tweet(tweettext,username) values(?,?)",tweet.getTweettext(),tweet.getUsername());
        System.out.println("posted \n"+tweet);
    }
}