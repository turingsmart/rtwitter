/**
 * Created with IntelliJ IDEA.
 * User: maverick
 * Date: 29/7/13
 * Time: 4:59 PM
 * To change this template use File | Settings | File Templates.
 */
var tweetSeen = 0;
var userLoggedIn;
function refreshTweetsAjax(tweetUrl) {
    userLoggedIn = tweetUrl;
//    var tweetUrl = "${username}";
    var ext = "/newTweets"
    $.ajax({
        url: tweetUrl.concat(ext),
        type: "POST",
        data : "tweetSeen="+tweetSeen,
        success:function(data){
            var length = data.length;

            $('#tweetBox').empty();
            for(var i=0; i < data.length; i++){
                $('#tweetBox').append(
                    $('<div>').addClass('well')
                        .append($('<div>').addClass("pull-left").append($('<a>').text(data[i].username).attr("href","/"+data[i].username+"/timeline")))
                        .append($('<div>').addClass("pull-center").text(data[i].tweettext))
                );
            }
        }
    });
}

function loadMore()
{
    tweetSeen = tweetSeen+1;
    console.log("More loaded");
    var tweetUrl = userLoggedIn;
    var ext = "/newTweets";
    $.ajax({
        url: tweetUrl.concat(ext),
        type: "POST",
        data : "tweetSeen="+tweetSeen,
        success:function(data){
            var length = data.length;

            for(var i=0; i < data.length; i++){
                $('#tweetBox').append(
                    $('<div>').addClass('well')
                        .append($('<div>').addClass("pull-left").append($('<a>').text(data[i].username).attr("href","/"+data[i].username+"/timeline")))
                        .append($('<div>').addClass("pull-center").text(data[i].tweettext))
                );
            }
        }
    });
}

function bindScroll(){
    if((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
        loadMore();
    }
}
$(window).scroll(bindScroll);

//intervalId = setInterval(refreshTweetsAjax, 10000);


