/****考试对象***/
var Timer = function(config){
	$.extend(this , Exam.defaults , config);
	this.renderTo = typeof this.renderTo=='string'?$("#"+this.renderTo):this.renderTo;
	this.init();
};
//默认设置
Timer.defaults = {
    
};

Timer.prototype = {
	init : function(){
		
	},
	start : function(){
		//开始倒计时
	},
	timeOver : function(){
		//倒计时结束执行
		
	}
}