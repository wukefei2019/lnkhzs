$(document).ready(function() {
  //设置Iframe高度，自适应
  window.setInterval("reinitIframe('iframe1')", 200);
  window.setInterval("reinitIframe('iframe2')", 200);
  window.setInterval("reinitIframe('iframe3')", 200);
  window.setInterval("reinitIframe('iframe4')", 200);
  window.setInterval("reinitIframe('iframe5')", 200);
  window.setInterval("reinitIframe('iframe6')", 200);
  window.setInterval("reinitIframe('iframe7')", 200);
  window.setInterval("reinitIframe('iframe8')", 200);
  window.setInterval("reinitIframe('iframe9')", 200);
});

window.onload=function(){
	$("#iframe4").contents().find(".js-tt-details-btn").click(function(){
			layer.open({
			 	type: 2,
			 	shadeClose: true,
			 	title: ['铁塔共享价格详单', 'font-size:12px;color:#666666;'],
			 	area: ['740px', '460px'],
			  content: ['tietaModal.html', 'no']//这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
			});
		});
}
