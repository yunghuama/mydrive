/****考试对象***/
var Timer = function(config){
	$.extend(this , Exam.defaults , config);
	this.renderTo = typeof this.renderTo=='string'?$("#"+this.renderTo):this.renderTo;
	this.init();
};
//默认设置
Timer.defaults = {
    time : 45*60 //默认的分钟数
};

Timer.prototype = {
	init : function(){
		var timeBody = $("<span></span>");
		this.timeBody = timeBody;
		this.renderTo.append(timeBody);
	},
	start : function(){
	//开始倒计时
	var entity = this;
	this.inId = setInterval(function(){entity.refresh()}, 1000);
	},
	refresh : function(){
		console.debug("refresh");
		this.time = parseInt(this.time) - 1;
		var minutes = Math.floor(this.time / 60);
		var seconds = Math.floor(this.time % 60);
		this.timeBody.empty();
		this.timeBody.text(minutes+":"+seconds);
		if(this.time == 0)
			this.timeOver();
	},
	timeOver : function(){
		//倒计时结束执行
		alert("倒计时结束");
		clearInterval(this.inId);
	}
}
