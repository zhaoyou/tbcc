/**
*		一些通用的js函数
**/

/**用户单击退出时执行的函数*/
	 function sessionClose(){
			var flag =	confirm('确定要退出,离开本界面吗？'); 
			if(flag){
				window.close();
			}			
		}
	
	/**
	*	获取系统当前的浏览器类型
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
		 * 判断按键是否是数字键
		 * @param {Object} e	FireFox中事件全局对象
		 */
		function isNumber(e){	
			var n = getBrowser()==1?event.keyCode:e.which ;
		   		 if(n>57 || n<48)
				 	if(n!=8)
				 		return  false;   			
		    		return true ;   
	    }
	    
	    function createTime(){
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
				var seconds = now.getSeconds() ;
				var timeValue = year+"年"+month+"月"+date+"日 星期"+day+" ";
				timeValue += ((hours >24) ? hours -24 :hours) ;
				timeValue += ((minutes < 10) ? ":0" : ":") + minutes ;
				timeValue += ((seconds < 10) ? ":0" : ":") + seconds ;
				return timeValue ;
	    }
	    
	     //这个文件主要是用来显示标题时间
 		function showtime () {
			document.getElementById('time').innerHTML=createTime();
			timerID = setTimeout("showtime()",1000);
		}
		
		
		
		//后台页面的菜单和时间显示通过动态加载而成
		function showInfo(){
 		window.setInterval(function(){
				window.top.document.getElementById('time').innerHTML = createTime() ;
			},1000) ;
		}
		
		