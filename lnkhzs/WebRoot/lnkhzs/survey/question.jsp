<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head><script>if (typeof(Worker) !== "undefined") {}
  	else{
		alert("【尊敬的用户，您好！您的浏览器版本低，请进行浏览器升级或换成其他浏览器，多谢配合！】");
	}</script><meta charset=utf-8><meta http-equiv=X-UA-Compatible content="IE=edge"><meta name=viewport content="width=device-width,initial-scale=1"><link rel=icon href=/lnfwzl/lnkhzs/survey/dist_show/favicon.ico><link href=/lnfwzl/lnkhzs/survey/dist_show/bootstrap/css/bootstrap.css rel=stylesheet><link href=/lnfwzl/lnkhzs/survey/dist_show/bootstrapselect/css/bootstrap-select.css rel=stylesheet><script src=/lnfwzl/lnkhzs/survey/dist_show/jquery.min.js></script><script src=/lnfwzl/lnkhzs/survey/dist_show/xlsx.core.min.js></script><script src=/lnfwzl/lnkhzs/survey/dist_show/bootstrap/js/bootstrap.js></script><script src=/lnfwzl/lnkhzs/survey/dist_show/bootstrapselect/js/bootstrap-select.js></script><script src="//api.map.baidu.com/api?v=2.0&ak=XiqxCBEFe6GKwMPZC44lzmZeARBadigg"></script><title>移动客户满意度调研</title><script>//let mydata = [{"content":"位置","id":0,"comp":"weizhi","isanswer":"必答","remark":"0","name":"位置","type":"weizhi","ps":"","options":""}];
        //let mydata=[{"content":"单选","id":0,"comp":"danxuan","isanswer":"必答","hideitems":[{"optitem":"1."},{"optitem":"1."},{"optitem":"2.3."}],"remark":"1","name":"单选","type":"danxuan","hidenum":[{"optitem":"4"},{"optitem":"2"},{"optitem":"3"}],"ps":"","options":[{"optText":"选项1"},{"optText":"选项2"},{"optText":"选项3"},{"optText":"选项4"}]},
        //{"content":"多选","id":1,"comp":"duoxuan","isanswer":"必答","hideitems":[{"optitem":"1."},{"optitem":"1."},{"optitem":"2."},{"optitem":"3."}],"remark":"1","name":"多选","type":"duoxuan","hidenum":[{"optitem":"3"},{"optitem":"4"},{"optitem":"3"},{"optitem":"5"}],"ps":"","options":[{"optText":"选项1"},{"optText":"选项2"},{"optText":"选项3"},{"optText":"选项4"}]},{"content":"判断","id":2,"comp":"shifei","isanswer":"必答","hideitems":[{"optitem":"1."},{"optitem":"2."}],"remark":"0","name":"判断","type":"shifei","hidenum":[{"optitem":"4"},{"optitem":"5"}],"ps":"","options":[{"optText":"是"},{"optText":"否"}]},{"content":"简答","id":3,"comp":"wenda","isanswer":"必答","minnumber":"0","remark":"0","name":"简答","type":"wenda","maxnumber":"100","ps":"","options":"答："},{"comp":"danxiangdafen","mintext":"非常不满意","maxtext":"非常满意","remark":"0","type":"danxiangdafen","hidenum":[],"id":4,"content":"单项打分","isanswer":"必答","hideitems":[],"name":"单项打分","options":[{"optText":"1"},{"optText":"2"},{"optText":"3"},{"optText":"4"},{"optText":"5"},{"optText":"6"},{"optText":"7"},{"optText":"8"},{"optText":"9"},{"optText":"10"}],"ps":""},{"content":"填空简答","id":5,"comp":"tiankongjianda","isanswer":"必答","remark":"0","name":"填空简答","type":"tiankongjianda","ps":"","options":[{"optText":"填空项1"}]},{"content":"矩阵填空","id":6,"comp":"juzhentiankong","isanswer":"必答","remark":"0","optionsrow":[{"optText":"矩阵行1"},{"optText":"矩阵行2"}],"name":"矩阵填空","optionscol":[{"optText":"选项1"},{"optText":"选项2"}],"type":"juzhentiankong","ps":""},{name: "位置",content:"位置",type: "weizhi",comp: "weizhi",options: "",remark:0,isanswer:"必答",ps:"",id:7}];
        let mydata = [];
        let backgroundList = [
          {divid1:"pickup0",id1:0,src1:"/lnfwzl/lnkhzs/survey/dist/bgImage/bg0.jpg",style1:"",
            divid2:"pickup1",id2:1,src2:"/lnfwzl/lnkhzs/survey/dist/bgImage/bg01.jpg",style2:"border:3px solid #16A3FB;"},
          {divid1:"pickup2",id1:2,src1:"/lnfwzl/lnkhzs/survey/dist/bgImage/bg1.jpg",style1:"",
            divid2:"pickup3",id2:3,src2:"/lnfwzl/lnkhzs/survey/dist/bgImage/bg2.jpg",style2:""}];
        let mtop = {number:1,color:"#DEDEDE"};
        let mbottom={number:1,color:"#DEDEDE"};//下边框
        let mleft={number:1,color:"#DEDEDE"};//左边框
        let mright={number:1,color:"#DEDEDE"};//右边框
        let fontFamily=[{name:"默认",id:0,pick:true},{name:"serif",id:1,pick:false},{name:"sans-serif",id:2,pick:false},{name:"cursive",id:3,pick:false},{name:"fantasy",id:4,pick:false},{name:"monospace",id:5,pick:false}];
        let fontSize=28;//标题字号
        let fontLocation=[{name:"靠左",id:0,inname:"left",pick:false},{name:"居中",id:1,inname:"center",pick:true},{name:"靠右",id:2,inname:"right",pick:false}];//标题位置
        let fontWeight=[{name:"否",id:0,inname:"normal",pick:true},{name:"是",id:1,inname:"bolder",pick:false}];//标题是否加粗
        let fontFamily_com=[{name:"默认",id:0,pick:true},{name:"serif",id:1,pick:false},{name:"sans-serif",id:2,pick:false},{name:"cursive",id:3,pick:false},{name:"fantasy",id:4,pick:false},{name:"monospace",id:5,pick:false}];
        let fontSize_com=14;//各题目字号
        let fontLocation_com=[{name:"靠左",id:0,inname:"left",pick:true},{name:"居中",id:1,inname:"center",pick:false},{name:"靠右",id:2,inname:"right",pick:false}];//各题目位置
        let fontWeight_com=[{name:"否",id:0,inname:"normal",pick:true},{name:"是",id:1,inname:"bolder",pick:false}];

        getlist2data();//获取题的详细信息
        getothersetdata();//获取其他设置的详细信息
        var background="";

        function getDESUtilID(){
            var retid="";
            $.ajax({
                url : "/lnfwzl/khzs/vue/surveys/getDESUtil.action",
                type : "GET",
                data : {
                    "wjid" : getQueryVariable("id"),
                    "isen" : '2',
                },
                async:false,
                success : function(unid) {
                    if(unid==''||unid==null){
                        alert("【尊敬的用户，您好，您尚未获得参与调研资格，多谢参与！】");
                    }
                    //return unid;
                    retid = unid;
                },
                error:function(retdata){
                }
            });

            return retid;
        }


        function getlist2data() {
            $.ajax({
                url : "/lnfwzl/khzs/vue/surveys/seleVueByid.action",
                type : "GET",
                dataType : "jsonp",
                data : {
                    "myAsk" : getQueryVariable("id")
                    //"myAsk" : getDESUtilID()
                },
                async : false,
                jsonp : "callback",
                success : function(json) {
                    mydata = json;
                }
            });
        }

        function getothersetdata() {
          $.ajax({
            url : "/lnfwzl/khzs/vue/surveys/seleOtherSetVueByid.action",
            type : "GET",
            dataType : "jsonp",
            data : {
              "myAsk" : getQueryVariable("id")
              //"myAsk" : getDESUtilID()
            },
            async : false,
            jsonp : "callback",
            success : function(json){
              if(!(json==null||json=="")){
                backgroundList = json[0].backgroundList;
                mtop = json[0].mtop;
                mbottom = json[0].mbottom;
                mleft = json[0].mleft;
                mright = json[0].mright;
                fontFamily = json[0].fontFamily;
                fontLocation = json[0].fontLocation;
                fontWeight = json[0].fontWeight;
                fontFamily_com = json[0].fontFamily_com;
                fontLocation_com = json[0].fontLocation_com;
                fontWeight_com = json[0].fontWeight_com;
                fontSize = json[0].fontSize;
                fontSize_com = json[0].fontSize_com;
              }

            }
          });
        }



        function getQueryVariable(variable) {
            var query = window.location.search.substring(1);
            var vars = query.split("&");
            for (var i = 0; i < vars.length; i++) {
                var pair = vars[i].split("=");
                if (pair[0] == variable) {
                    return pair[1];
                }
            }
            return "";
        }

        function putlist2(result) {
            for (var i = 0; i < result.length; i++) {
                /*var jsonstr =JSON.stringify(result[i]);
                var json = $.parseJSON( jsonstr );*/
                let item = JSON.parse(JSON.stringify(result[i]));
                item.id=mydata.length+1;
                mydata.push(item);
            }
        }

        function putlist2WJ(result) {
            for (var i = 0; i < result.length; i++) {
                /*var jsonstr =JSON.stringify(result[i]);
                var json = $.parseJSON( jsonstr );*/
                let item = JSON.parse(JSON.stringify(result[i]));
                item.id=mydata.length+1;
                mydata.push(item);
            }
        }


        function openwindow(url, name, iWidth, iHeight) {
            var resurl = encodeURI(url);
            var name = null;
            name = name || new Date().getTime();
            iWidth = iWidth || 1200;
            iHeight = iHeight || 800;
            var iTop = (window.screen.availHeight - 30 - iHeight) / 2;
            var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;
            return window.open(resurl, name, 'height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=yes');
        }

        function getOther() {
            //window.open('/lnfwzl/lnkhzs/survey/addTiku.jsp');
            openwindow('/lnfwzl/lnkhzs/survey/addTiku.jsp');
        }

        function getresult(obj,id,num){
            var item = JSON.parse(JSON.stringify({"optitem":obj}));
            mydata[id].hideitems[num].optitem=obj;
        }

        //EXCEL导入
        window.onload=function(){
            $('#i-file').change(function(e) {
                var files = e.target.files;

                var fileReader = new FileReader();
                fileReader.onload = function(ev) {
                    try {
                        var data = ev.target.result,
                            workbook = XLSX.read(data, {
                                type: 'binary'
                            }), // 以二进制流方式读取得到整份excel表格对象
                            persons = []; // 存储获取到的数据
                    } catch (e) {
                        //console.log('文件类型不正确');
                        alert('文件类型不正确');
                        return;
                    }

                    // 表格的表格范围，可用于判断表头是否数量是否正确
                    var fromTo = '';
                    // 遍历每张表读取
                    for (var sheet in workbook.Sheets) {
                        if (workbook.Sheets.hasOwnProperty(sheet)) {
                            fromTo = workbook.Sheets[sheet]['!ref'];
                            //console.log(fromTo);
                            persons = persons.concat(XLSX.utils.sheet_to_json(workbook.Sheets[sheet]));
                            // break; // 如果只取第一张表，就取消注释这行
                        }
                    }

                    console.log(persons);
                    var execldata=[];
                    var execlrow={};
                    var rowname="";
                    var rowcontent="";
                    var rowtype="";
                    var rowcomp="";
                    var optionsrow=[];
                    for (var i = 0; i < persons.length; i++) {
                        execlrow={};
                        console.log(persons[i]);
                        rowname=persons[i]['题型（只能是“单选”，“多选”，“判断”，“简答”，“单项打分”中的一种）'];
                        optionsrow=[];
                        for (var j=1;j<=16;j++){
                            //console.log(persons[i]['选项'+j])
                            var optionsitem=persons[i]['选项'+j];
                            if(persons[i]['选项'+j]!=undefined){
                                optionsrow.push({optText:optionsitem});
                            }
                        }
                        if(rowname=="单选"){
                            rowcontent="单选";
                            rowtype="danxuan";
                            rowcomp="danxuan";
                            execlrow ={
                                name:rowcontent,
                                content:rowcontent,
                                type:rowtype,
                                comp: rowcomp,
                                options: optionsrow,
                                hideitems:[],
                                hidenum:[],
                                remark:0,
                                isanswer:"必答",
                                id:mydata.length};
                            mydata.push(execlrow);
                        }else if(rowname=="多选"){
                            rowcontent="多选";
                            rowtype="duoxuan";
                            rowcomp="duoxuan";
                            execlrow ={
                                name:rowcontent,
                                content:rowcontent,
                                type:rowtype,
                                comp: rowcomp,
                                options: optionsrow,
                                remark:0,
                                isanswer:"必答",
                                id:mydata.length};
                            mydata.push(execlrow);
                        }else if(rowname=="判断"){
                            rowcontent="判断";
                            rowtype="shifei";
                            rowcomp="shifei";
                            execlrow ={
                                name:rowcontent,
                                content:rowcontent,
                                type:rowtype,
                                comp: rowcomp,
                                options: optionsrow,
                                hideitems:[],
                                hidenum:[],
                                remark:0,
                                isanswer:"必答",
                                id:mydata.length};
                            mydata.push(execlrow);
                        }else if(rowname=="简答"){
                            rowcontent="简答";
                            rowtype="wenda";
                            rowcomp="wenda";
                            execlrow ={
                                name:rowcontent,
                                content:rowcontent,
                                type:rowtype,
                                comp: rowcomp,
                                options: optionsrow,
                                remark:0,
                                isanswer:"必答",
                                id:mydata.length};
                            mydata.push(execlrow);
                        }else if(rowname=="单项打分"){
                            rowcontent="单项打分";
                            rowtype="danxiangdafen";
                            rowcomp="danxiangdafen";
                            execlrow ={
                                name:rowcontent,
                                content:rowcontent,
                                type:rowtype,
                                comp: rowcomp,
                                options: optionsrow,
                                hideitems:[],
                                hidenum:[],
                                remark:0,
                                isanswer:"必答",
                                id:mydata.length};
                            mydata.push(execlrow);
                        }


                        /*let item = JSON.parse(JSON.stringify(persons[i]));
                        item.id=mydata.length+1;
                        mydata.push(item);*/
                    }
                    //将persons赋值给要展示的表格数组里即可展示在界面
                };

                // 以二进制方式打开文件
                fileReader.readAsBinaryString(files[0]);
            });
        }

        function putMapPick(pointx,pointy,pointAddress){
          for (var i = 0; i < mydata.length; i++) {
            /*var jsonstr =JSON.stringify(result[i]);
            var json = $.parseJSON( jsonstr );*/
          /*let item = JSON.parse(JSON.stringify(mydata[i]));
            item.type="weizhi";
            item.options=pointAddress;
            mydata.push(item);*/
            if(mydata[i].type=="weizhi"){
              mydata[i].options=pointAddress;
              $("textarea[name=myMap]").attr("pointlat",pointx+","+pointy);
            }
          }

        }</script><link href=/lnfwzl/lnkhzs/survey/dist_show/css/chunk-b005e3ac.cd051c1b.css rel=prefetch><link href=/lnfwzl/lnkhzs/survey/dist_show/js/chunk-2d0d2eb5.30ee822a.js rel=prefetch><link href=/lnfwzl/lnkhzs/survey/dist_show/js/chunk-b005e3ac.31bb76ad.js rel=prefetch><link href=/lnfwzl/lnkhzs/survey/dist_show/css/app.d0d658c3.css rel=preload as=style><link href=/lnfwzl/lnkhzs/survey/dist_show/js/app.83cb01f8.js rel=preload as=script><link href=/lnfwzl/lnkhzs/survey/dist_show/js/chunk-vendors.63c3ec75.js rel=preload as=script><link href=/lnfwzl/lnkhzs/survey/dist_show/css/app.d0d658c3.css rel=stylesheet></head><body><noscript><strong>We're sorry but vueqa doesn't work properly without JavaScript enabled. Please enable it to continue.</strong></noscript><div id=app></div><script src=/lnfwzl/lnkhzs/survey/dist_show/js/chunk-vendors.63c3ec75.js></script><script src=/lnfwzl/lnkhzs/survey/dist_show/js/app.83cb01f8.js></script></body></html>
        



        
        
                                