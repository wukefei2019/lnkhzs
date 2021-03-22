
window.onload=function(){
	
		// 将所有.ui-select实例化
$('.ui-select').ui_select();

// 获取已经实例化的select对象
var obj = $('#sel_api').data('ui-select');

$().onclick = function(value)  {
switch (value) {
    case '2':
        $('#sel_03').data('ui-select').enable();
        break;
    case '3':
        $('#sel_03').data('ui-select').disable();
        break;
    case '4':
        $('#sel_01').data('ui-select').val('3');
        break;
    case '5':
        $('#sel_02').data('ui-select').toggle();
        break;
    case '6':
        $('#sel_02').data('ui-select').visible();
        break;
    case '7':
        $('#sel_02').data('ui-select').visible(true);
        break;
}
}

	
	
	
	
	
};


