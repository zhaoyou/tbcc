/**
*这个文件主要是用来操作查询时间条件
*/


    
    /**
    *	version 1.0 此时搜索只能够搜索到7天范围内的数据
    *	单击查询按钮、判断搜索的时间是否符合条件
    */
    function Interval(){
    
        //获取起始时间
		 var startTime = document.myform.time1.value;
		 var endTime = document.myform.time2.value;
		 
		 if(startTime=="" || endTime==""){
            alert("请选择起始时间!") ;
            return  ;
         }
		 var tempDate,dayDate1,dayDate2,intervalDay ;
		
            //转换日期格式 04-19-2007 使之能够求出天数间隔
		   tempDate =startTime.split('-'); 
		   dayDate1 = new Date(tempDate[1]+'-'+tempDate[2]+'-'+tempDate[0]) ;
	       
		   tempDate = endTime.split('-'); 
		   dayDate2 = new Date(tempDate[1]+'-'+ tempDate[2]+'-'+tempDate[0]); 
		   
		   //把相差的毫秒数转换成天数
		   intervalDay = parseInt(Math.abs(dayDate1 -dayDate2)/1000/60/60/24); 
		   				   
		   
		   //判断天数间隔
		   if(intervalDay>7){
		    alert("查询的天数不能够超过七天!");
		    document.myform.time2.focus();
			return ;
		   }
    
            //获取时间间隔值
	       	var intervalValue = document.myform.timevalue.value;
		
		      if(intervalValue == ""){
		          	alert("间隔不能为空!");
		          	document.myform.timevalue.focus();
			         return ;
	       	  }else{
                 	      if(isNaN(intervalValue)){
                 	          alert("间隔值非法!");
        	                  document.myform.timevalue.focus();
			                  return ;
                            }else{
                                if(intervalValue<1){
                                     alert("间隔值非法!");
        	                         document.myform.timevalue.focus();
			                         return ;
                                }
                            }
                  
                  }
	       	  
	       	  
	       	  
                
          //获取间隔类型
		
        var timeType = document.myform.timeType.value ;
        
        //单位为3表示秒
		if(timeType== 3){
			if(intervalValue % 10 != 0){
				alert("秒数必须为10的整数倍！");
				document.myform.timevalue.focus();
				return ;
			}
			if(intervalValue > 59){
				alert("秒数必须是1-59！");
				document.myform.timevalue.focus();
				return ;
			}
		}
		
		//单位为2表示分钟
		if(timeType == 2){
			if(intervalValue < 1  || intervalValue > 59){
				alert("分钟数必须是1-59！");
				document.myform.timevalue.focus();
				return ;
			}
		}
		
		//单位为1表示为小时
		if(timeType == 1){
			if(intervalValue < 1  || intervalValue > 23){
				alert("小时数必须为1-23");
				document.myform.timevalue.focus();
				return ;
			}
		}	
					
			//提交表单
			
			document.myform.submit();
						
		}
		
		
		/**
		*	versin2.0修改了冷库历史数据查询的方式、引用了启停的概念、间隔由原来的10s，变成了间隔可根据启停动态设置。
		*	经测试web页面显示数据为2880条，为一个界定。大约花费4-5(s)
		*	现在的冷库数据查询以30S为基数。查询一天刚好为2880条
		*	选择方式为30秒时  ：   最大的时间范围为1天 秒的间隔数值为 30 60 90 120 ... 
		*   选择方式为1 分钟时:	 最大的时间范围为2天 分钟的间隔数值为 1 2 3 4 5 6 7 ...
		*	选择方式为1 小时的：	最大的时间范围为4个月 小时的间隔数值为 1 2 3 4 5 6 7 ...
		*/
		
		function queryData(){
			   //获取起始时间
			 var startTime = document.myform.time1.value;
			 var endTime = document.myform.time2.value;
			 
			 //定义两个时间变量
			 var s,t ;
			 
          	//获取间隔类型
      		 var timeType = document.myform.timeType.value ;
        
            //获取时间间隔值
	       	var intervalValue = document.myform.timevalue.value;
			
			//两个日期的间隔天数
			var intervalDays = 0 ;
	       	
			//验证起始时间是否合法
	       	if(startTime=="" || endTime==""){
				window.alert("请选择查询的起始时间!");
				return ;
			}else{
				if(startTime==endTime){
					window.alert("结束时间必须大于开始时间!")
					return ;
				}
			}
			
			if(!(isvalid(startTime)&& isvalid(endTime))){
				window.alert("请输入合法的日期值!");
				return ;
			}
			
			//验证时间间隔是否合法
			 if(intervalValue == ""){
		          	window.alert("查询间隔不能为空!");
		          	document.myform.timevalue.focus();
			         return ;
	       	  }else{
                 	      if(isNaN(intervalValue)){
                 	          window.alert("查询间隔值非法!");
        	                  document.myform.timevalue.focus();
			                  return ;
                            }else{
                                if(intervalValue<1){
                                     window.alert("查询间隔值非法!");
        	                         document.myform.timevalue.focus();
			                         return ;
                                }
                            }
                  
                  }
                  
          var ref = document.getElementById("myrid").value ;
		  
		  if(ref==null || ref==""){
		  	window.alert("请选择一个有效的冷库");	
		  	return ;
		  }
		  
		  
				
	       s = buildDate(startTime) ;
		   e = buildDate(endTime) ;
		   
		   //获取查询的时间段相隔的天数
		   intervalDays = getIntervalDays(s,e) ;
		   
		   
		   if(timeType=="3"){		//选择的秒数查看
		   		if(intervalDays>1){
					window.alert("按秒数查询时，只能够查询1天的数据量!");
					return ;
				}
				
				if(intervalValue%30!=0){
					window.alert("按秒查询的间隔必须是30(s)的正整数倍！");
					 document.myform.timevalue.select();
					return  ;
				}		
		   }else if(timeType=="2"){	// 选择的是分钟查看
				if(intervalDays>2){
						var atleast = Math.ceil(getIntervalMinutes(s,e)/2880) ;
					if(intervalValue<atleast){
						window.alert("按照分钟查询时,只能够查询 2 天的数据量\n确定查询,则间隔至少要在 "+atleast+" 分钟以上");
						 document.myform.timevalue.select();
						return ;
					}	
				}
		   	
		   }else if(timeType=="1"){					//选择的小时查看
		   		if(intervalDays>120){
					var atleast = Math.ceil(getIntervalHours(s,e)/2880);
					if(intervalValue<atleast){
						window.alert("按照小时查询时,只能够查询 4 个月的数据量\n确定查询,则间隔至少要在 "+atleast+" 小时以上");
						 document.myform.timevalue.select();
						return ;
					}
				}
		   }
		  
		  
		  document.myform.submit();
		}
		
		/**
		 * 根据字符串构造日期对象 
		 * @param {Object} str	yyyy-MM-dd mm:HH:ss
		 */
		function buildDate(str){
			var temp ,tempDate,tempDate2 ;
			temp =str.split(' '); 
		 	tempDate = temp[0].split('-');
		    tempDate2 = temp[1].split(':')  ;   
		    return new Date(tempDate[0],tempDate[1],tempDate[2],tempDate2[0],tempDate2[1],tempDate2[2]) ;
		}
		
		/**
		 * 获取两个日期对象相隔的天数
		 * @param {Object} startTime	第一个时间
		 * @param {Object} endTime		第二个时间
		 */
		function getIntervalDays(startTime,endTime){   
		  return Math.ceil(Math.abs(startTime - endTime)/1000/60/60/24); 
		}
		
		/**
		 * 获取两个日期对象相隔的分钟数
		 * @param {Object} startTime
		 * @param {Object} endTime
		 */
		function getIntervalMinutes(startTime,endTime){
			return Math.ceil(Math.abs(startTime - endTime)/1000/60); 
		}
		
		/**
		 * 获取两个日期对象相隔的小时数
		 * @param {Object} startTime
		 * @param {Object} endTime
		 */
		function getIntervalHours(startTime,endTime){
			return Math.ceil(Math.abs(startTime - endTime)/1000/60/60); 
		}
		
		
		
		function changeTimeType(obj){
			if(obj.value=="3")
				document.myform.timevalue.value = "30" ;
			else 
				document.myform.timevalue.value = "1" ;
				
		}
		
		/**
		*	判断日期对象字符串是否正确yyyy-MM-dd HH:mm:ss
		*/
		function isvalid(str){
			if(str.charAt(4)=="-" && str.charAt(7)=="-" &&
				   str.charAt(10)==" " && str.charAt(13)==":" && str.charAt(16)==":")
						return true ;
				return false ;
		}
		
		
		