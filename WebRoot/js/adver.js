function Adv(pathName,url,time){
	this.pathName = pathName;
	this.url = url;
	this.time = time;
}

var Adver = function(config){
	$.extend(this , Adver.defaults , config);
	this.renderTo = typeof this.renderTo=='string'?$("#"+this.renderTo):this.renderTo;
	this.init();
};

Adver.defaults = {
	width : 100,
	height: 200
};

Adver.prototype = {
	 init : function(){
		 var div = $("<div>");
		 	 div.height(this.height);
		 	 div.width(this.width);
		 	 div.css({position:"relative"});
		 var closeBar = $('<a href="javascript:void(0);">X</a>');
		     closeBar.css({position:"absolute",right:"2px",top:"2px","text-decoration": "none"});
		     closeBar.bind("click",function(){
		    	 div.remove();
		     });
		     div.append(closeBar);
		 var adv = this.getImage();
		 if(adv!=null){
			 var img = $("<img>");
			 img.css({"max-height":this.height,"max-width":this.width});
			 img.attr("src",projectImage+"/adver/"+adv.pathName);
			 if(adv.url!=null&&adv.url!=""){
				 img.click(function(){
					 window.open(adv.url);
				 });
			 }
			 div.append(img);
			 this.renderTo.append(div);
		 }
		 
	 },
	 show : function(){
		 
	 },
	 hide : function(){
		 
	 },
	 getImage : function(){
		 var entity = this;
		 var adv = null;
		 $.ajax({
			   type: "POST",
			   url: projectName+"/system/ajax/getAdvers.d",
			   data: {'adver.position':entity.position,'adver.page':entity.page},
			   async: false,
			   success: function(data){
				   if(data!=null&&data.length>0){
					   var img = data[0];
					   adv = new Adv(img.pathName,img.url,img.time);
				   }
			   }
			});
		 return adv;
	 }
};
