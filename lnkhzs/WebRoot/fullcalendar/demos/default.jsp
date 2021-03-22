<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />
<link href='../fullcalendar.min.css' rel='stylesheet' />
<link href='../fullcalendar.print.min.css' rel='stylesheet' media='print' />
<script src='../lib/moment.min.js'></script>
<script src='../lib/jquery.min.js'></script>
<script src='../fullcalendar.min.js'></script>

<script>
  $(document).ready(function() {
	var setting ={
/* 	    	buttonText: {
	            today: '今天',
	        },
	        monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
	        dayNames: ["星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"],
	        dayNamesShort: ["星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"], */
	        dayClick: function (date, allDay, jsEvent, view) {
	        	console.log('clicked on ' + date.format());
	        },
	      editable: true,
	      eventLimit: true, 
	      events: [
	        {
	          title: '吃香蕉Repeating Event',
	          start: '2018-12-09T16:00:00',
	          end: '2018-12-09T20:00:00'
	        },
	        {
	          title: '吃辣椒Repeating Event',
	          start: '2018-12-16T16:00:00',
	          end: '2018-12-16T20:00:00'
	        },
	        {
	          title: '不吃不吃Meetingsssssssssssssssssssssssssssssssssssssssssssssssssss',
	          start: '2018-12-12T10:30:00',
	          end: '2018-12-12T12:30:00'
	        },
	        {
	          title: '吃吃吃Lunch',
	          start: '2018-12-12T12:00:00'
	        },
	        {
	          title: 'Meeting',
	          start: '2018-12-12T14:30:00'
	        },
	        {
	          title: 'Happy Hour',
	          start: '2018-12-12T17:30:00'
	        },
	        {
	          title: 'Dinner',
	          start: '2018-12-12T20:00:00'
	        },
	        {
	          title: 'Birthday Party',
	          start: '2018-12-13T07:00:00'
	        }
	      ]
	    }  

    $('#calendar').fullCalendar(setting);
    

  });

</script>
<style>

  body {
    margin: 40px 10px;
    padding: 0;
    font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
    font-size: 14px;
  }

  #calendar {
    max-width: 900px;
    margin: 0 auto;
  }

</style>
</head>
<body>

  <div id='calendar'></div>

</body>
</html>
