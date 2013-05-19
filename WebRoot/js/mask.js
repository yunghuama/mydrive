
/****遮罩***/
var Mask = function(config){
	$.extend(this , Mask.defaults , config);
};
//默认设置
Mask.defaults = {
    
};

Mask.prototype = {
	show : function(){
		$("#commiting").show();
		$("#commiting").fadeTo(0,0.5);
		$("#commitingInfo").show();
		$("#commiting").bind("click",function(){return});
		$('#commitingInfo').css('left', (($(document.body).width()/2) - ($('#commitingInfo').width()/2)));
	    $('#commitingInfo').css('top', (($(document.body).height()/2) - ($('#commitingInfo').height()/2)));
	    var entity = this;
	    $("#commitingButtonClose").bind("click",function(){
	    	entity.hide();
	    });
	},
	hide : function(){
		$("#commiting").hide();
		$('#commitingInfo').hide();
		$("#commiting").unbind("click");
		$("#commitingButtonClose").unbind("click");
	},
	refreshMessage : function(msg){
		$("#commitingInfoTitle span").empty();
		$("#commitingInfoTitle span").text(msg);
	}
}