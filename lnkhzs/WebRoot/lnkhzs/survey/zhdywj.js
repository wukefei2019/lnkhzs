function abc(){
	$.bs.table.refresh('table0');
}


function fmt_operate(value, row, index) {
	var html = [];
	if(row.STATUS=="已发布"){

		html.push("<a title='预览' class='yulan btn-link fontsize14' >预览</a>");
		html.push("<a title='筛选条件' class='tiaojian btn-link fontsize14' >筛选条件</a>");
		html.push("<a title='问卷调查统计' class='wjCount btn-link fontsize14' >问卷调查统计</a>");
		html.push("<a title='题库维护' class='weihu btn-link fontsize14' >题库维护</a>");
	}else{
		html.push("<a id='fabu' title='发布' class='publish btn-link fontsize14' >发布</a>");
		html.push("<a title='问卷维护' class='wjwh btn-link fontsize14' >问卷维护</a>");
		html.push("<a title='删除' class='delete btn-link fontsize14' >删除</a>");
		html.push("<a title='预览' class='yulan btn-link fontsize14' >预览</a>");
		html.push("<a title='指定号码发起' class='roundad btn-link fontsize14' >指定号码发起</a>");
		//html.push("<a title='问卷调查统计' class='wjCount btn-link fontsize14' >问卷调查统计</a>");
	}

	/*html.push("<a title='题库' class='tiku btn-link fontsize14' >题库</a>");*/
	return html.join("");
}

fn_evnt_name_look = {
	'click a' : function(e, value, row, index) {
		openwindow($ctx + '/lnkhzs/survey/addDywj.jsp?id=' + row.ID + "&msg=" + 1, '');
	}
}

var fn_operate_events = {
	'click a.delete' : function(e, value, row, index) {
		if (confirm("您确认删除吗？")) {
			$.post($ctx + '/khzs/survey/deleteDywj.action?khzsQuestionnaire.id=' + row.ID +"&khzsQuestionnaire.bsdicselectid="+row.BSDICSELECTID+"&khzsQuestionnaire.bsdicseleinfoid="+row.BSDICSELEINFOID).done(function(result) {
				if (result == 'success') {
					alert("删除成功")
					$.bs.table.refresh("table0");
				}
			});
		}
		;
	},

	'click a.update' : function(e, value, row, index) {
		openwindow($ctx + '/lnkhzs/survey/addDywj.jsp?id=' + row.ID, '');
	},

	'click a.wjwh' : function(e, value, row, index) {

		openwindow($ctx + '/lnkhzs/survey/dist/index.html?myAsk=' + row.ID + '&myName='+row.NAME+'&ispublic='+row.ISPUBLIC+ '&myNameSub='+row.NAMESUB+'&isreward='+row.ISREWARD, '');
	},
	
	
	'click a.publish' : function(e, value, row, index) {
		openwindow($ctx + '/lnkhzs/survey/zhdywjbind.jsp?id=' + row.ID+'&isreward='+row.ISREWARD, '');
		/*if (confirm("您确认发布吗？")) {
			$.post($ctx + '/khzs/survey/publish.action?khzsQuestionnaire.id=' + row.ID).done(function(result) {
				if (result == 'success') {00
					alert("发布成功");
					$.bs.table.refresh("table0");

				}
			});
		};*/

	},
	'click a.tiku' : function(e, value, row, index) {
		openwindow($ctx + '/lnkhzs/survey/addTiku.jsp', '');
	},
	'click a.yulan' : function(e, value, row, index) {
		//openwindow($ctx + '/lnkhzs/survey/question.jsp?id=' + row.ID, '');
		//openwindow($ctx + '/lnkhzs/survey/questionNew.jsp?id=' + row.ID, '');
		//openwindow($ctx + '/lnkhzs/survey/dist_show/index.html?myAsk=' + row.ID + '&myName='+row.NAME+'&ispublic='+row.ISPUBLIC+'&isreward='+row.ISREWARD+ '&myNameSub='+row.NAMESUB, '');
		/*$.post($ctx + '/khzs/vue/surveys/getDESUtil.action?wjid=' + row.ID +"&isen=1").done(function(result) {
			//console.log(result);
			openwindow($ctx + '/lnkhzs/survey/dist_show/index.html?id=' + result , '');
		});*/
		openwindow($ctx + '/lnkhzs/survey/dist_show/index.html?id=' + row.ID , '');
		//alert(row.ISREWARD)
	},
	'click a.tiaojian' : function(e, value, row, index) {
		openwindow($ctx + '/lnkhzs/survey/zhdywjbindshow.jsp?id=' + row.ID, '');
	},
	'click a.wjCount' : function(e, value, row, index) {
		openwindow($ctx + '/lnkhzs/survey/wjCount.jsp?id=' + row.ID, '');
	},
	'click a.weihu' : function(e, value, row, index) {
		openwindow($ctx + '/lnkhzs/survey/weihu.jsp?id=' + row.ID, '');
	},
	'click a.roundad' : function(e, value, row, index) {
		openwindow($ctx + '/lnkhzs/survey/appointNum.jsp?id=' + row.ID+'&isreward='+row.ISREWARD, '');
	}
	
};


