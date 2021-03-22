
var city = '全省';
function getmaparea(data){
	res = [];
	if(data){
		for (i = 0; i < data.length; i++) {
            res.push({name:data[i]});
	    }
	}
	return res;
}

function getmapcity(data){
	res = [];
	map = geoCoordMap[city];
	if(data){
		for (i = 0; i < data.length; i++) {
            res.push({value:map[data[i].name],name:data[i].name});
	    }
	}
	return res;
}



var mapJson = {
	    '全省':'liaoning',
	    '沈阳':'shenyang',
	    '大连':'dalian',
	    '鞍山':'anshan',
	    '抚顺':'fushun',
	    '本溪':'benxi',
	    '丹东':'dandong',
	    '锦州':'jinzhou',
	    '营口':'yinkou',
	    '阜新':'fuxin',
	    '辽阳':'liaoyang',
	    '盘锦':'panjin',
	    '铁岭':'tieling',
	    '朝阳':'chaoyang',
	    '葫芦岛':'huludao'
}

var geoCoordMap = {
	    '全省':{
	        '沈阳':[122.9,42.1],
	        '大连':[122.0,39.6],
	        '鞍山':[122.6,40.9],
	        '抚顺':[124.6,41.9],
	        '本溪':[123.9,41.2],
	        '丹东':[124.3,40.5],
	        '锦州':[121.3,41.4],
	        '营口':[122.3,40.4],
	        '阜新':[121.8,42.2],
	        '辽阳':[123.1,41.2],
	        '盘锦':[121.8,41.1],
	        '铁岭':[124.2,42.6],
	        '朝阳':[120.0,41.5],
	        '葫芦岛':[120.0,40.6]
	    }
	}

function getmapValue(){
	res = [];
	map = geoCoordMap[city];
	//for (i = 0; i < mapJson.length; i++) {
	for (var key in map) {
		res.push({value:map[key],name:key});
	}

	return res;
}

function getAllCity(){
	res = [];
	map = geoCoordMap[city];
	//for (i = 0; i < mapJson.length; i++) {
	for (var key in map) {
		res.push({name:key});
	}
	return res;
}



function deal_city_map(te,cityMapChart) {
	
	var url = $ctx + "/homePage/dataSource/ajaxGetComplaintsAmount.action";
	$.post(url).done(function(dataArr) {
		var number1=0;
		var number2=0;
		var number3=0;
		var number4=0;
		
		var data = dataArr;
		if(data.length==14){
			number1=getRandomInt(parseInt(data[2].value),parseInt(data[1].value)-1);
			number2=getRandomInt(parseInt(data[5].value),parseInt(data[4].value)-1);
			number3=getRandomInt(parseInt(data[9].value),parseInt(data[8].value)-1);
			number4=getRandomInt(parseInt(data[12].value),parseInt(data[11].value)-1);
		}else{
			data=[
				{name: "沈阳", value: "0"},
				{name: "大连", value: "0"},
				{name: "鞍山", value: "0"},
				{name: "营口", value: "0"},
				{name: "锦州", value: "0"},
				{name: "铁岭", value: "0"},
				{name: "朝阳", value: "0"},
				{name: "葫芦岛", value: "0"},
				{name: "辽阳", value: "0"}, 
				{name: "抚顺", value: "0"},
				{name: "阜新", value: "0"},
				{name: "丹东", value: "0"},
				{name: "盘锦", value: "0"},
				{name: "本溪", value: "0"}
				]
			for(var i=0;i<dataArr.length;i++){
				for(var j=0;j<data.length;j++){
					if(dataArr[i].name==data[j].name){
						data[j].value=dataArr[i].value;
						//data.splice(i,1,'a');
						break;
					}
				}
			}
		}
		console.log(data);
		//var number5=parseInt(data[12].value)+1;
		
	
	
	/*var data=[
		{name: "沈阳", value: "59770"},0
		{name: "大连", value: "36139"},1
		{name: "鞍山", value: "15931"},2
		{name: "营口", value: "10898"},3
		{name: "锦州", value: "10360"},4
		{name: "铁岭", value: "9768"},5
		{name: "朝阳", value: "8048"},6
		{name: "葫芦岛", value: "7524"},7
		{name: "辽阳", value: "7457"}, 8
		{name: "抚顺", value: "7416"},9
		{name: "阜新", value: "7208"},10
		{name: "丹东", value: "6533"},11
		{name: "盘锦", value: "6502"},12
		{name: "本溪", value: "6019"}13
]*/
option1 = {};
option1.backgroundColor= 'rgba(0,0,0,0)';
option1.series = [];
option1.tooltip = {
    trigger : 'item',
    position:'left'
}

option1.geo = {
    show : true,
    map : mapJson[city],
    label : {
        normal : {
            show : false
        },
        emphasis : {
            show : false,
        }
    },
    top:10,
    bottom:10,
    left:80,
    right:80,
    roam : false,
    selectedMode:'single',
    itemStyle : {
        areaColor : '#3daae5',
        borderColor : '#fff',
        borderWidth : 1,
        //color :'#006196',
        //shadowColor: 'rgba(13, 130, 255, 1)',
        //shadowBlur: 20,
        emphasis : {
            areaColor : '#637be3',
                shadowColor: '#374a9e',
                shadowBlur: 10,
                shadowOffsetX:10,
                shadowOffsetY:10,
                opacity:1
            //borderColor : '#2bbbc0',
            //borderWidth : 2
        }
    }
}

option1.title = {
		text : te,
		textStyle: {
			fontWeight: '400',
            color: '#fff',
            align: "center"
        },
		top : '1%',
		left:'50%'
}

option1.series.push({
    name: '',
    type: 'effectScatter',
    coordinateSystem: 'geo',
    zlevel: 2,
    rippleEffect: {
        brushType: 'stroke'
    },
    label: {
        normal: {
            show: true,
            position: 'right',
            formatter: '{b}',
            textStyle: {
                color: '#fff'
            }
        }
    },
    symbolSize: 0.1,
    itemStyle: {
        normal: {
            //color: '#57d9ff'
        	color: '#fff'
        }
    },
    data: getmapValue()     
})

 option1.series.push({
	 type: 'map',
     map: mapJson[city],
     geoIndex: 0,
     aspectScale: 0.75, //长宽比
     showLegendSymbol: false, // 存在legend时显示
    
     roam: true,
     itemStyle: {
         normal: {
             areaColor: '#031525',
             borderColor: '#3B5077',
         },
         emphasis: {
             areaColor: '#2B91B7'
         }
     },
     animation: false,
     data:  data
    
	 
 })
 
 option1.dataRange = {
	
	splitList: [
		/*{start: 35000, label: '80-100%', color:  '#f1868d'},
		{start: 7000, end: 35000,  label: '20-80%',color:  '#3bbadf'},
		{start: 0, end: 7000,  label: '0-20%',color:  '#22de91'}*/
		
		/*{start: 35000, label: '80-100%', color:  '#e04b4b'},
		{start: 7000, end: 35000,  label: '20-80%',color:  '#1a6fd7'},
		{start: 0, end: 7000,  label: '0-20%',color:  '#45af56'}
		*/
		/*{start: 16000,  color:  '#e04b4b'},
		{start: 10000, end: 16000,  color:  '#dd6920'},
		{start: 7420, end: 10000,  color:  '#ffce49'},
		{start: 6510, end: 7420,  color:  '#1a6fd7'},
		{start: 0, end: 6510,  color:  '#45af56'}*/
		
		{start: number1, label: '', color:  '#e04b4b'},
		{start: number2, label: '', end: number1,  color:  '#dd6920'},
		{start: number3,label: '', end: number2,  color:  '#ffce49'},
		{start: number4,label: '',end: number3,  color:  '#1a6fd7'},
		{start: 0, label: '',end: number4,  color:  '#45af56'}
		
		]

		
	}


 
 var visualMap = {
        min: 1,
        max: 50000,
        itemWidth:15,
        itemHeight:200,
        orient: 'horizontal',
        right:10,
        text: ['HIGH', 'LOW'],
        calculable: false,
        seriesIndex: [1],

        
        /*inRange: {
            color: ['#67b7dc', '#335f85']
        	//color: ['#00BB00', '#0066CC', '#FF0000']
        	//color: ['#00BB00', '#FF0000']
        }*/
    };

 //var option = deal_city_map();
option1.visualMap =visualMap;
cityMapChart.setOption(option1);

	
    

    

    //return option1;
	});
}


function getRandomInt(min, max) {
	  min = Math.ceil(min);
	  max = Math.floor(max);
	  return Math.floor(Math.random() * (max - min + 1)) + min; //不含最大值，含最小值
	}