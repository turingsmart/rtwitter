/**
 * Created with IntelliJ IDEA.
 * User: maverick
 * Date: 29/7/13
 * Time: 4:59 PM
 * To change this template use File | Settings | File Templates.
 */
function refreshTweetsAjax(tweetUrl) {
//    var tweetUrl = "${username}";
    var ext = "/newTweets"
    $.ajax({
        url: tweetUrl.concat(ext),
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

intervalId = setInterval(refreshTweetsAjax, 10000);


