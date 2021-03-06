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
		this.time = parseInt(this.time);
		var timeBody = $("<span></span>");
		this.timeBody = timeBody;
		this.timeBody.text(this.timeConvert(this.time));
		this.renderTo.append(timeBody);
		this.useTime = 0;
	},
	start : function(){
	//开始倒计时
	var entity = this;
	this.inId = setInterval(function(){entity.refresh()}, 1000);
	},
	refresh : function(){
		this.time = parseInt(this.time) - 1;
		this.useTime += 1;
		this.timeBody.empty();
		this.timeBody.text(this.timeConvert(this.time));
		if(this.time == 0)
			this.timeOver();
	},
	timeConvert : function(time){
		var minutes = Math.floor(this.time / 60);
		var seconds = Math.floor(this.time % 60);
		if(minutes<10)
			minutes = "0"+minutes;
		if(seconds<10)
			seconds = "0"+seconds;
		return minutes+":"+seconds;
	},
	timeOver : function(){
		//倒计时结束执行
		this.complete(this.getUsedTime());
		clearInterval(this.inId);
	},
	stop : function(){
		clearInterval(this.inId);
	},
	getUsedTime : function(){
		var minutes = Math.floor(this.useTime / 60);
		var seconds = Math.floor(this.useTime % 60);
		if(minutes<10)
			minutes = "0"+minutes;
		if(seconds<10)
			seconds = "0"+seconds;
		return minutes+":"+seconds;
	}
	
}
