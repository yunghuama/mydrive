/**问题对象**/
function Question(num,id,question,answer_a,answer_b,answer_c,answer_d,answer,img,vedio) {
	this.num = num;
	this.id = id;
	this.question = question;
	this.answer_a = answer_a;
	this.answer_b = answer_b;
	this.answer_c = answer_c;
	this.answer_d = answer_d;
	this.answer = answer;
	this.img = img;
	this.vedio = vedio;
}

/**答案对象**/
function Answer(num,questionid,answer){
	this.id = id;
	this.answer = answer;
}

/****题号对象***/
var Num = function(config){
	$.extend(this , Exam.defaults , config);
}

Num.defaults = {
	width : 20,
	height: 20
}

Num.prototype = {
	 init : function(){
		 var div  = $("<div></div>");
		 div.attr("id",this.num);
		 div.html(this.num);
		 div.width(this.width);
		 div.heigth(this.height);
		 this.div = div;
		 return div;
	 },
	 setError : function(answer){
		 //显示正确答案
		 
	 },
	 setRight : function(){
		 //设置为回答正确
	 },
	 setComplete : function(){
		 //设置已经完成该题目
	 },
	 setOnClick : function(){
		 //设置点击效果
		 
	 },
	 setDefault : function(){
		 //设置为默认效果
		 
	 }
	 
}

/****考试对象***/
var Exam = function(config){
	$.extend(this , Exam.defaults , config);
	this.renderTo = typeof this.renderTo=='string'?$("#"+this.renderTo):this.renderTo;
	this.init();
};
//默认设置
Exam.defaults = {
    
};

Exam.prototype = {
	init : function(){
		this.qa = [];
		this.aa = [];//创建存放答案的集合
		this.qa_index = 0;
		this.aa_index = 0;
	},
	add : function(question){
		//添加试题
		this.qa.push(question);
		console.debug(question.num);
	},
	get : function(index){
		//获得一个试题
		if(index<this.qa.length)
			return this.qa[index];
	},
	addAnswer : function(){
		//添加一个答案
	},
	score : function(){
		//计算分数
		
	},
	next : function(){
		//获得下一个问题
		this.qa_index = this.qa_index+1;
		if(hasNext(this.qa_index))
			return get(this.qa_index);
	},
	prev : function(){
		//获得上一个问题
		this.qa_index = this.qa_index-1;
		if(hasPrev(this.qa_index))
			return get(this.qa_index);
	},
	hasNext : function(){
		//是否有下一个
		return this.qa_index<this.qa.length?true:false;
	},
	hasPrev : function(){
		//是否有上一个
		return this.qa_index>=0 ? true :false;
	}
	
		
}