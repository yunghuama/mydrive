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


/****题号对象***/
var Num = function(config){
	$.extend(this , Num.defaults , config);
}

Num.defaults = {
	width : 30,
	height: 30
}

Num.prototype = {
	 createDiv : function(){
		 var div  = $('<div class="selectGrid normal-border" id="'+this.num+'"><div class="top"><span>'+this.num+'</span></div><div class="center"><span id="result"></span></div></div>');
		 console.debug("width:"+this.width);
		 div.width(this.width);
		 div.height(this.height);
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
	this.selectGridRenderTo = typeof this.selectGridRenderTo=='string'?$("#"+this.selectGridRenderTo):this.selectGridRenderTo;
	this.questionRenderTo = typeof this.questionRenderTo=='string'?$("#"+this.questionRenderTo):this.questionRenderTo;
	this.init();
};
//默认设置
Exam.defaults = {
    
};

Exam.prototype = {
	init : function(){
		this.qa = [];
		this.aa = [];//创建存放答案的集合
		this.na = [];//创建存放序号的集合
		this.qa_index = 0;
		this.aa_index = 0;
		this.na_index = 0;
		this.tempDiv = null;
	},
	add : function(question){
		//添加试题
		this.qa.push(question);
		console.debug(question.num);
	},
	getQuestion : function(index){
		//获得一个试题
		if(index<this.qa.length)
			return this.qa[index];
	},
	getNum : function(index){
		//获得一个数字对象
		if(index<this.na.length)
			return this.na[index];
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
	},
	setClickBorder : function(num){
		if(this.tempDiv != null)
			this.tempDiv.removeClass("click-border").addClass("normal-border");
		num.removeClass("normal-border").addClass("click-border");
		this.tempDiv =num;
	},
	drawQuestion : function(question){
		var content = [];
		//获得问题
		var questionDiv = '<div ><span class="question">'+question.question+'</span></div>';
		content.push(questionDiv);
		//获得答案
		var answer = question.answer;
		//单选题
		if(answer.length==1){
			var a = '<div><input type="radio" name="answer" value="a"><span class="answer">'+question.answer_a+'</span><div>';
			content.push(a);
			var b = '<div><input type="radio" name="answer" value="b"><span class="answer">'+question.answer_b+'</span><div>';
			content.push(b);
			if(question.answer_c!=""&&question.answer_c!=null){
				var c = '<div><input type="radio" name="answer" value="c"><span class="answer">'+question.answer_c+'</span><div>';
				content.push(c);
			}
			if(question.answer_d!=""&&question.answer_d!=null){
				var d = '<div><input type="radio" name="answer" value="d"><span class="answer">'+question.answer_d+'</span><div>';
				content.push(d);
			}
			
		}else if(answer.length>1){
		//多选题	
			var a = '<div><input type="checkbox" name="answer" value="a"><span class="answer">'+question.answer_a+'</span><div>';
			content.push(a);
			var b = '<div><input type="checkbox" name="answer" value="b"><span class="answer">'+question.answer_b+'</span><div>';
			content.push(b);
			var c = '<div><input type="checkbox" name="answer" value="c"><span class="answer">'+question.answer_c+'</span><div>';
			content.push(c);
			var d = '<div><input type="checkbox" name="answer" value="d"><span class="answer">'+question.answer_d+'</span><div>';
			content.push(d);
		}
		
		this.questionRenderTo.empty();
		this.questionRenderTo.prepend(content.join(''));
		//如果有答案就将答案选中
		if(question.myanswer!=null&&question.myanswer!=undefined){
			var rr = question.myanswer.split('');
			for(var i=0;i<rr.length;i++){
				this.questionRenderTo.find("input[value='"+rr[i]+"']").attr("checked","checked");
			}
		}
		var entity = this;
		this.questionRenderTo.find("input").bind("click",function(){
			var rs = "";
			entity.questionRenderTo.find("input:checked").each(function(){
				rs = rs+$(this).val();
			})
			//为相应的问题设置回答
			question.myanswer = rs;
			var numObj = entity.getNum(question.num);
			numObj.find("#result").text(question.myanswer);
			numObj.addClass("answered");
			console.debug(question.myanswer);
		});
	},
	drawSelectGrid : function(){
		var entity = this;
		var divarray = [];
		for(var i=0;i<entity.qa.length;i++){
			var num = new Num({'num':i+1});
			var div = num.createDiv();
			div.data("num",i);
			div.bind("click",function(){
				entity.drawQuestion(entity.getQuestion($(this).data("num")));
				entity.setClickBorder($(this));
			});
			entity.selectGridRenderTo.append(div);
			this.na.push(div);
		}
	
	}
	
		
}