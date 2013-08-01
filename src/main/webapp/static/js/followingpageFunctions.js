/**
 * Created with IntelliJ IDEA.
 * User: maverick
 * Date: 29/7/13
 * Time: 4:59 PM
 * To change this template use File | Settings | File Templates.
 */
var followingSeen;
var userLoggedIn;
function refreshFollowingAjax(tweetUrl) {
    console.log("Following getting called !!!")
    followingSeen = 0;
    userLoggedIn = tweetUrl;
//    var tweetUrl = "${username}";
    var ext = "/following"
    $.ajax({
        url: tweetUrl.concat(ext),
        type: "POST",
        data : "followingSeen="+followingSeen,
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
    $(window).scroll(bindScrollFollowing);
}

function loadMoreFollowing()
{
    followingSeen = followingSeen+1;
    console.log("More loaded");
    var tweetUrl = userLoggedIn;
    var ext = "/following";
    $.ajax({
        url: tweetUrl.concat(ext),
        type: "POST",
        data : "followingSeen="+followingSeen,
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

function bindScrollFollowing(){
    if((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
        loadMoreFollowing();
    }
}

//intervalId = setInterval(refreshFollowingAjax, 10000);



