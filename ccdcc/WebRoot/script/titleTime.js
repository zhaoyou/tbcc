 //这个文件主要是用来显示标题时间
 function showtime () {
		var now = new Date();
		var year =now.getFullYear();
		var month =now.getMonth()+1;
		var date =now.getDate();
		var day =now.getDay();
		if(day==0){
		day="日";
		}else if(day==1){
		day="一";
		}else if(day==2){
		day="二";
		}else if(day==3){
		day="三";
		}else if(day==4){
		day="四";
		}else if(day==5){
		day="五";
		}else{
		day="六";
		}
		var hours = now.getHours();
		var minutes = now.getMinutes();
		var seconds = now.getSeconds()
		var timeValue = year+"年"+month+"月"+date+"日 星期"+day+" ";
		timeValue += ((hours >24) ? hours -24 :hours)
		timeValue += ((minutes < 10) ? ":0" : ":") + minutes
		timeValue += ((seconds < 10) ? ":0" : ":") + seconds
		//document.clock.time.value = timeValue;
		document.getElementById('time').innerHTML=timeValue;
		timerID = setTimeout("showtime()",1000);
		}
		
		
		
		
		
		
	