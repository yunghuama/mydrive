
//初始化菜单操作
function initNav(){
	/***为一级菜单添加***/
	
	$(".menu-item").hover(function(){
	$(".menu-item:visible").removeClass("active");
	if($('.sub-nav', this).length === 0) {
	      return;
	   }
	$('.sub-nav:visible').hide();
	$(this).addClass("active");
	$(".sub-nav li.sub-menu-item").hover(function(){
		$(this).addClass("blue");
	},function(){
		$(this).removeClass("blue");
	});
	$('.sub-nav',$(this)).show();
	},function(){
		$(".sub-nav",$(this)).hide();
		$(this).removeClass("active");
	});
	
}

