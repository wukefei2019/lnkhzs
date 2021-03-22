
(function($) {

    var _options = {};
	var _container = {};
	var _nowtime;
	var _timedifference;

	jQuery.fn.DigitClock = function(options) {
		var id = $(this).get(0).id;
		_options[id] = $.extend({}, $.fn.DigitClock.defaults, options);
		_nowtime = new Date();
		if(_options[id].servertime>'0'){
			_timedifference = _nowtime.getTime() - parseInt(_options[id].servertime);
		}else{
			_timedifference = 0;
		}
		//console.log(_nowtime)

		return this.each(function()
		{
			_container[id] = $(this);
			showClock(id);
		});
		
		function showClock(id)
		{
			var nowdate = new Date();
			nowdate.setTime(nowdate.getTime() - _timedifference);
			//console.log(nowdate)
			
			var year = nowdate.getFullYear(),
				month = nowdate.getMonth()+1,
				day = nowdate.getDay(),
				hours = nowdate.getHours(),
				minutes = nowdate.getMinutes(),
				seconds = nowdate.getSeconds(),
				date = nowdate.getDate();
			var weekday =["星期日","星期一","星期二","星期三","星期四","星期五","星期六"];
			
			var templateStr = _options[id].timeFormat;
			//'{Year}-{Month}-{Day}&nbsp;{Hours}:{Minutes}:{Seconds}'
			templateStr = templateStr.replace("{Year}", year);
			templateStr = templateStr.replace("{Month}",getDD(month));
			templateStr = templateStr.replace("{Day}", getDD(date));
			templateStr = templateStr.replace("{Hours}", getDD(hours));
			templateStr = templateStr.replace("{Minutes}", getDD(minutes));
			templateStr = templateStr.replace("{Seconds}", getDD(seconds));
			templateStr = templateStr.replace("{Week}", weekday[day]);
		
			var obj = $("#"+id);
			obj.css("fontSize", _options[id].fontSize);
			obj.css("fontFamily", _options[id].fontFamily);
			obj.css("color", _options[id].fontColor);
			obj.css("background", _options[id].background);
			obj.css("fontWeight", _options[id].fontWeight);
		
			//change reading
			obj.html(templateStr)
			setTimeout(function(){showClock(id)}, 1000);
		}
		
		function getDD(num)
		{
			return (num>=10)?num:"0"+num;
		}
		
		function refreshClock()
		{
			setupClock();
		}
	}
	
	//default values
	jQuery.fn.DigitClock.defaults = {
		fontSize: '',
		fontFamily: '',
		fontColor: '#0086d0',
		fontWeight: '',
		background: '',
		timeFormat: '{Year}年{Month}月{Day}日&nbsp;{Hours}时{Minutes}分{Seconds}秒 {Week}',
		servertime : '0'
	};

})(jQuery);
