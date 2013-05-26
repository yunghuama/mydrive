/********科目一练习模式********/

/**问题对象**/
function Question(num,id,question,answer_a,answer_b,answer_c,answer_d,answer,img,video,type) {
	this.num = num;
	this.id = id;
	this.question = question;
	this.answer_a = answer_a;
	this.answer_b = answer_b;
	this.answer_c = answer_c;
	this.answer_d = answer_d;
	this.answer = answer;
	this.img = img;
	this.video = video;
	this.type = type;
}

/**回答错误的问题**/
function ErrorQuestion(num,id,answer){
	this.num = num;
	this.id = id;
	this.answer = answer;
}

/****题号对象***/
var Num = function(config){
	$.extend(this , Num.defaults , config);
}

Num.defaults = {
	width : 25,
	height: 28
}

Num.prototype = {
	 createDiv : function(){
		 var div  = $('<div class="selectGrid normal-border" id="'+this.num+'"><div class="top"><span>'+this.num+'</span></div><div class="center"><span id="result"></span></div></div>');
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
	this.richMediaRenderTo = typeof this.richMediaRenderTo=='string'?$("#"+this.richMediaRenderTo):this.richMediaRenderTo;
	this.init();
};
//默认设置
Exam.defaults = {
    qScore:1
};

Exam.prototype = {
	init : function(){
		this.qa = [];
		this.aa = [];//创建存放答案的集合
		this.na = [];//创建存放序号的集合
		this.ea = [];//创建存放错题的集合
		this.qa_index = 0;
		this.aa_index = 0;
		this.na_index = 0;
		this.tempDiv = null;
		this.isScore = false;//是否已经交卷。交卷后可以查看答案
	},
	add : function(question){
		//添加试题
		this.qa.push(question);
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
		if(this.isScore){
			alert("请勿重复交卷");
			return;
		}
		//显示遮罩层
		var mask = new Mask();
		mask.show();
		mask.refreshMessage("正在计算分数...请稍后...");
		//计算分数
		var qa = this.qa;
		var score = 0;
		for(var i=0;i<qa.length;i++){
			var question = qa[i];
			if(question.answer.toLowerCase()==question.myanswer){
				score+=this.qScore;
			}else {
				var errorQuestion = new ErrorQuestion(question.num,question.id,question.myanswer);
				this.ea.push(errorQuestion);
				var numObj = this.getNum(question.num);
				numObj.find("#result").text(question.myanswer);
				numObj.removeClass("answered").addClass("error");
			}
		}
		//将错题和分数提交到服务器
		var isSuccess = false;
		$.ajax({
			   type: "POST",
			   url: projectName+"/system/ajax/countDownOne.d",
			   async: false,
			   success: function(msg){
			    if(msg=='success')
			    	isSuccess = true;
			   }
		});
		if(isSuccess){
			mask.refreshMessage("您本次练习得分 : "+score);
			this.isScore = true;
		}else {
			mask.refreshMessage("分数提交失败,您本次模拟考试得分 : "+score);
		}
		
	},
	getNext : function(){
		//获得下一个问题
		this.qa_index = this.qa_index+1;
		if(this.hasNext(this.qa_index))
			return this.getQuestion(this.qa_index);
		else
			this.qa_index = this.qa_index-1;
	},
	getPrev : function(){
		//获得上一个问题
		this.qa_index = this.qa_index-1;
		if(this.hasPrev(this.qa_index))
			return this.getQuestion(this.qa_index);
		else
			this.qa_index = this.qa_index+1;
	},
	hasNext : function(index){
		//是否有下一个
		return index < this.qa.length ? true : false;
	},
	hasPrev : function(index){
		//是否有上一个
		return index >= 0 ? true : false;
	},
	getIndex : function(){
		return this.qa_index;
	},
	setClickBorder : function(num){
		if(this.tempDiv != null)
			this.tempDiv.removeClass("click-border").addClass("normal-border");
		num.removeClass("normal-border").addClass("click-border");
		this.tempDiv =num;
	},
	//绘制题目
	drawQuestion : function(question){
		if(question==null||question==undefined)
			return;
		var content = [];
		this.qa_index = parseInt(question.num);
		//获得问题
		var questionDiv = '<div id="questions"><span class="question">'+(parseInt(question.num)+1)+"."+question.question+'</span></div>';
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
			//如果已经交卷，则显示答案
			if(this.isScore){
				var ans = '<div id="rightAnswer"><span>正确答案:'+question.answer+'</span></div>';
				content.push(ans);
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
			//如果已经交卷，则显示答案
			if(this.isScore){
				var ans = '<div><span>正确答案:'+question.answer+'</span></div>';
				content.push(ans);
			}
		}
		
		this.questionRenderTo.empty();
		this.richMediaRenderTo.empty();
		this.questionRenderTo.prepend(content.join(''));
		//如果有图片就显示图片
		if(question.img!=""&&question.img!=null){
			var img = '<img src='+projectImage+'/'+question.type+'/'+question.img.toLowerCase()+'>';
			this.richMediaRenderTo.append(img);
		}
		//如果有视频则显示视频
		if(question.video!=""&&question.video!=null){
			var video = '<embed src="'+projectName+'/js/ckplayer/ckplayer.swf" flashvars="f='+projectImage+'/'+question.type+'/'+question.video.toLowerCase()+'&p=0" quality="high" width="700" height="300" align="middle" allowScriptAccess="always" allowFullscreen="true" type="application/x-shockwave-flash"></embed>';
			this.richMediaRenderTo.append(video);
		}
		
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
	//绘制数字选题格子
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