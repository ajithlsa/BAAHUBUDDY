$(document).ready(function(){
    var ret = HomeInterface.onLoad();
    $("body").html(ret);
    $(".data").click(function(){
        HomeInterface.showToast($(this).attr("ref"));
    });
});