
/**
	����ļ���Ҫ��������ʾһЩͬ�õ�javascript����
*/

function reload(){
	window.location.reload();
}
function help(msg){
	alert('1'+msg);
}

function submit(){
	windows.location.submit();
}
function to(url){
	window.location.href=url;
}

/**
	������Ŀԭ�������еĶ������Ǻ��˰�ťִ�еĴ���
*/
function back(){
	history.go(-1);
}


function save(url){
	alert('2');
	to(url);
}
function add(url){
	alert('3');
	to(url);
}
function del(msg){
	if (window.confirm("3"+msg+"3")){
		reload();
	}
}
function setCurTime(oid){
	var now=new Date();
	var year=now.getYear();
	var month=now.getMonth()+1;
	var day=now.getDate();
	var hours=now.getHours();
	var minutes=now.getMinutes();
	var seconds=now.getSeconds();
	var timeString = year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
	var oCtl = document.getElementById(oid);
	oCtl.value = timeString;
}


/**����ķ����������ϵ�ʱ����ӵ�*/
	
	/**�û������˳�ʱִ�еĺ���*/
	 function sessionClose(){
			var flag =	confirm('ȷ��Ҫ�˳�,�뿪��������');
			if(flag)
				window.close();
		}
	
	/**
	*	��ȡϵͳ��ǰ�����������
	*   IE	 	1
	*   fireFox 2
	*	chrome	3
	*	opera	4
	*	safari	5
	*/	
	function getBrowser(){
		 var Sys = {};
        var ua = navigator.userAgent.toLowerCase();
        var s;
        (s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] :
        (s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] :
        (s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] :
        (s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] :
        (s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;      
        if (Sys.ie) {
      		return 1 ;	// document.write('IE: ' + Sys.ie);
        }        	
        if (Sys.firefox){
        	return 2 ;  // document.write('Firefox: ' + Sys.firefox);
        }        
        if (Sys.chrome){
        	return 3 ;  // document.write('Chrome: ' + Sys.chrome);
        }       
        if (Sys.opera){
        	return 4 ; //document.write('Opera: ' + Sys.opera);
        } 
        if (Sys.safari){
        	return 5 ;	//document.write('Safari: ' + Sys.safari); 
        }     
        return 0 ;
	}
	
		/**
		 * �жϰ����Ƿ������ּ�
		 * @param {Object} e	FireFox���¼�ȫ�ֶ���
		 */
		function isNumber(e){	
			var n = getBrowser()==1?event.keyCode:e.which ;
		   		 if(n>57 || n<48)
				 	if(n!=8)
				 		return  false;   			
		    		return true ;   
	    }
	    
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
		
		
		
		