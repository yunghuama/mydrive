
//初始化菜单操作
function initNav(){
	$(".menu-item").hover(function(){
		console.debug("in");
	$(".menu-item:visible").removeClass("bgover");
	if($('.sub-nav', this).length === 0) {
	      return;
	   }
	$('.sub-nav:visible').hide();
	$(this).addClass("bgover");
	$(".sub-nav li.sub-menu-item").hover(function(){
		$(this).addClass("blue");
	},function(){
		$(this).removeClass("blue");
	});
	$('.sub-nav',$(this)).show();
	},function(){
		console.debug("out");
		$(".sub-nav",$(this)).hide();
		$(this).removeClass("bgover");
	});
	
}

