/**
 * Created with IntelliJ IDEA.
 * User: karthik
 * Date: 31/7/13
 * Time: 8:10 PM
 * To change this template use File | Settings | File Templates.
 */
var followersSeen;
var userLoggedIn;
function refreshFollowersAjax(tweetUrl) {
    followersSeen = 0;
    userLoggedIn = tweetUrl;
//    var tweetUrl = "${username}";
    var ext = "/followers"
    $.ajax({
        url: tweetUrl.concat(ext),
        type: "POST",
        data : "followersSeen="+followersSeen,
        success:function(data){
            var length = data.length;

            $('#tweetBox').empty();
            for(var i=0; i < data.length; i++){
                $('#tweetBox').append(
                    $('<div>').addClass('well')
                        .append($('<div>').addClass("pull-left").append($('<a>').text(data[i]).attr("href","/"+data[i]+"/timeline")))
                );


            }
        }
    });
    $(window).scroll(bindScrollFollowers);
}

function loadMoreFollowers()
{
    followersSeen = followersSeen+1;
    console.log("More loaded");
    var tweetUrl = userLoggedIn;
    var ext = "/followers";
    $.ajax({
        url: tweetUrl.concat(ext),
        type: "POST",
        data : "followersSeen="+followersSeen,
        success:function(data){
            var length = data.length;
            for(var i=0; i < data.length; i++){
                $('#tweetBox').append(
                    $('<div>').addClass('well')
                        .append($('<div>').addClass("pull-left").append($('<a>').text(data[i]).attr("href","/"+data[i]+"/timeline")))
                );


            }
        }
    });
}

function bindScrollFollowers(){
    if((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
        loadMoreFollowers();
    }
}

//intervalId = setInterval(refreshFollowersAjax, 10000);