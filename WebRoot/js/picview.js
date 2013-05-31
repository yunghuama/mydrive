/****图片显示***/
var ImageView = function(config){
	$.extend(this , ImageView.defaults , config);
	this.renderTo = typeof this.renderTo=='string'?$("#"+this.renderTo):this.renderTo;
	this.show();
};
//默认设置
ImageView.defaults = {
    maxImageWidth:800,
	maxImageHeight:300,
	width:800,
	height:300
};

ImageView.prototype = {
	show : function(){
		var maskDiv = $("<DIV>");//创建遮罩DIV
		maskDiv.width(this.renderTo.width());
		maskDiv.height(this.renderTo.height());
		maskDiv.css({"background-color":"#000000","filter":"alpha(opacity=50)","-moz-opacity":"0.5","opacity":"0.5","position":"absolute","left":"0px","top":"0px"});
		var contentDiv = $("<DIV>");
		contentDiv.width(this.renderTo.width());
		contentDiv.height(this.renderTo.height());
		contentDiv.css({"position":"absolute","left":"0px","top":"0px"});
		var centerDiv = $("<DIV>");//创建中间div
		centerDiv.width(this.width);
		centerDiv.height(this.height);
		//计算显示位置
		var left = (this.renderTo.width()-this.width)/2;
		var top = (this.renderTo.height()-this.height)/2;
		centerDiv.css({"border":"none","text-align":"center","margin":"0px auto","position":"absolute","left":left,"top":top});
		var img = $("<img>");//创建图片
		img.attr("src",this.src);
		img.css({"max-width":this.maxImageWidth,"max-height":this.maxImageHeight});
		centerDiv.append(img);
		contentDiv.append(centerDiv);
		this.renderTo.append(maskDiv);
		this.renderTo.append(contentDiv);
		contentDiv.bind("click",function(){
			maskDiv.remove();
			contentDiv.remove();
		});
	}
}