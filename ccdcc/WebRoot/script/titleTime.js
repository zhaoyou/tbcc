 //����ļ���Ҫ��������ʾ����ʱ��
 function showtime () {
		var now = new Date();
		var year =now.getFullYear();
		var month =now.getMonth()+1;
		var date =now.getDate();
		var day =now.getDay();
		if(day==0){
		day="��";
		}else if(day==1){
		day="һ";
		}else if(day==2){
		day="��";
		}else if(day==3){
		day="��";
		}else if(day==4){
		day="��";
		}else if(day==5){
		day="��";
		}else{
		day="��";
		}
		var hours = now.getHours();
		var minutes = now.getMinutes();
		var seconds = now.getSeconds()
		var timeValue = year+"��"+month+"��"+date+"�� ����"+day+" ";
		timeValue += ((hours >24) ? hours -24 :hours)
		timeValue += ((minutes < 10) ? ":0" : ":") + minutes
		timeValue += ((seconds < 10) ? ":0" : ":") + seconds
		//document.clock.time.value = timeValue;
		document.getElementById('time').innerHTML=timeValue;
		timerID = setTimeout("showtime()",1000);
		}
		
		
		
		
		
		
	