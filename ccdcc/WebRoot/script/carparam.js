			
			/**
			**	车载配置所用的js
			**/
			
			var query_timetask = null ;
			
			var query_isContitue = false ;	//此标记用来表标示用户是否已经取消了查询操作
			
			var update_timetask = null ;
			
			var defaultWaitTime = 30 ;		//默认等待时间
			
			var timecount = defaultWaitTime ;
			
			var update_waittimetask = null ;
			
			
			
			var failurl = '<img src="img/util/fail.gif" alt="Fail" align="middle" />' ;
			
			var successurl = '<img src="img/util/isok.gif" alt="Success" align="middle" />' ;
			
			/**
             * 隐藏、显示div
             * @param {Object} number
             * @param {Object} index
             */
            function change_option(number, index){
                $('current' + number).className = '';
                $('content' + number).style.display = 'none';
                $('current' + index).className = 'current';
                $('content' + index).style.display = 'block';
            }
            
            /**
             * 根据id获取dom对象
             * @param {Object} obj
             */
            function $(obj){  return document.getElementById(obj); }
            
            /**
            *	element object visable
            */
            function hideObject(obj){$(obj).style.display = "none" ; }
            
            /**
            *	element object inline  configOperate
            **/
            function showObject(obj){$(obj).style.display = "inline" ;}
            
            /**
            *	element object able
            */
            function ableObject(obj){$(obj).disabled = false ;}
            
            /**
            *	element object disable
            */
            function disableObject(obj){$(obj).disabled = true} ;
            /**
            *	获取车载参数预处理，执行获取参数动作
            */
            function getCarParam_pre(){
            	var p = $("proId").value ;
            	if(p==null ||p==""){
            		window.alert("请选择需要配置的车载!");
            		return ;
            	}
            	
            	configOperate.insertOperate(p,2,1,1,{
            		callback:query_insert_Handler,
            		errorHandler:query_inserterror_handler,
            		timeout:10000
            	});
            	
            	/** 查询、更新按钮都不可用，等待数据返回
            	disableObject('q_query');	
            	disableObject('u_update');
            	*/
            	
            	unEnableTwoButton();			//两按钮不可用
            	
            	showObject('c_query');		    	/**显示查询等待div*/
            	
            	hideObject('c_update'); 			/**隐藏一些提示文本、已经更新等待div*/
            	$('failpic_query').innerHTML = '' ;
            	$('successpic_query').innerHTML = '' ;
            	         	
            	dataInit();								/**清空提示文本*/
            	
            	query_isContitue = false ;				/**判断取消查询取消操作*/
            	
            	//hideObject('successpic_query');	          
            	//$('failpic_query').innerHTML = "" ;	/**清空提示文本*/
            }
            
            /**
            *	处理查询返回的车载返回的参数
            */
            
            function query_insert_Handler(result){
            	//判断返回的动作标示
            	if(result!=null && result>0){
            		//保存操作动作标示，获取车载参数配置信息
            		$('q_action_id').value = result  ;         
            		getParamState(result);      		     
            	}else{
            		query_inserterror_handler();
            	}
            }
            
            /**
            *	查询错误处理函数
            */
            function query_inserterror_handler(){
            	$("failpic_query").innerHTML =failurl+ "执行参数动作失败!" ;           	
            	doing_queryError() ;      	
            	//initAllTip();       	
            }
            
             /**
            *		获取动作状态
            **/
            function getParamState(aid){
            	configOperate.QueryOptStatus(aid,{
           			callback:query_status_handler,
           			errorHandler:query_statuserror_handler,
           			timeout:10000
           		});
           		
           		query_timetask = window.setTimeout(function(){
           			getParamState(aid);
           		},10000) ;
            }
            
            /**
            *		获取操作动作状态处理函数
            **/
            
            function query_status_handler(result){
            
            	if(result!=null){        		     								
            		if(result==1){			//request        			
            			return ;//undo ,go on waiting.... 				
            		}else if (result==2)	//ok
            		{
	         			query_isContitue = true ;					//用户至今没有取消操作、可以显示车载信息
            			
            			getCarParam($('q_action_id').value);		//获取车载的参数信息
            			       				
            		}else if (result==14)	//unconnection
            		{
            			
            			//initAllTip() ;
            			doing_queryError() ;
            			$("failpic_query").innerHTML =failurl+ "车载尚未启动，获取数据失败" ;
            			          			
            		}else if (result==4)	//cancel
            		{
            			//initAllTip();   
            			doing_queryError() ;  			
            			$("failpic_query").innerHTML =failurl+ "操作已经取消，获取数据失败" ;		
  			
            		}else	// if (result==13)	//unknow reason
            		{
            			//initAllTip();
            			doing_queryError() ;
            			$("failpic_query").innerHTML =failurl+ "未知原因，获取数据失败" ;		         			    				
            		}
            		
            		window.clearTimeout(query_timetask);		//停止实时获取状态的计时器 
            			
            	}else{
            		query_statuserror_handler() ;
            	}
            }
            
            /**
            *
            **/
            function query_statuserror_handler(){
            	//initAllTip(); 
            	doing_queryError() ;  
            	$("failpic_query").innerHTML = failurl+ "获取动作状态失败!" ;
            	window.clearInterval(query_timetask);         	   	
            }
            
            
            
            /**
           	*	执行获取参数操作
           	**/
           	
           	function getCarParam(aid){
           		configOperate.QueryCarParam(aid,{
           			callback:query_load_handler,
           			errorHandler:query_loaderror_handler,
           			timeout:10000
           		}) ;
           	}
           	
           	
           	/**
           	*	加载车载数据处理函数
           	*	result name,valid,value;name,valid,value
           	**/
           	function query_load_handler(result){
           		//如果用户没有取消操作、则继续显示车载数据
           		if(query_isContitue){
	           		if(result!=null && result!='N/A'){
	           			parseResult(result) ;
	           			doing_querySuccess() ;
	           			$('successpic_query').innerHTML = successurl + "数据获取成功!" ;
	           					//querySuccessHandler();	           			
	           					//initAllTip() ;	
	           					//showObject('s');           			
	           		}else{
	           			query_loaderror_handler();
	           		}
           		}
           	}
           	/**
           	*	加载车载数据错误处理函数
           	**/
           	function query_loaderror_handler(){
           	//	initAllTip() ;
           	//	dataInit();
           		doing_queryError() ;
           		$("failpic_query").innerHTML = failurl +  " 获取参数信息失败!" ;
           	}
           	
           	/**
           	*	结果字符串解析函数
           	**/
           	function parseResult(str){
           		var params = str.split(";");
           		//循环遍历每一个参数
           		for(var i=0;i<params.length;i++){
           			var para = params[i].split(",");
           			//如果当前参数可用、则显示出来
           			if(para[1]!="N/A")	
           				ableObject('q'+para[0]) ;
           						
           			$('q'+para[0]).value = para[2] ;	//显示值
           			$('qq'+para[0]).value = para[2] ;	// 值的拷贝用来判断是否已经更新了
           		}
           	}
           	
           	/**
           	*	点击查询取消操作
           	**/
           	function doCancle(){
           		configOperate.UpdateOptStatus($('q_action_id').value,4,{
           			callback:doCancleHandler,
           			errorHandler:doCancleErrorHandler,
           			timeout:10000
           		});
           		
           		//initAllTip();
           		hideObject('c_query');
           		hideObject('c_update');
           		
           		ableObject('q_query');
           		disableObject('u_update');
           		
           		
           		query_isContitue = false ;
           		
           		$('q_action_id').value = "" ;
           		
           		window.clearTimeout(query_timetask);
           	}
           	
           	
           	
           	
           	/**取消的处理函数*/
           	function doCancleHandler(result){}//暂时不处理
           	
           	/**取消的错误处理函数*/
           	function doCancleErrorHandler(){}//暂时不处理
           	
           	
           	/**
           	*	重新初始化状态
           
           	function initAllTip(){     
           	      	
           		ableObject('q_query');		//按钮复原
           		disableObject('u_update');	
           			
            	hideObject('successpic_query');	//显示div复原
            	hideObject('c_query');
            	hideObject('c_update');    
            	   								//动作操作复原
            	$('q_action_id').value = "" ;
            	$('u_action_id').value = "" ;
           	}
           	*/
           	/**
           	*	初始化数据
           	**/
           	function dataInit(){
           		for(var j=1;j<=16;j++){
           				disableObject('q'+j);
	           			$('q'+j).value = "****";
	           			$('qq'+j).value = "****" ;
           			}
           	}
           	
           	/***
           	*	查询完成之后、等待更新操作,也支持再次查询
           	**/
           	function doing_querySuccess(){
           		enableTwoButton() ;			/**查询按钮可用、更新按钮可用、隐藏等待查询页面*/
           		hideObject('c_query');
           		hideObject('c_update');
           		
           		$('q_action_id').value="" ;
           		
           	}
           	
           	/**两个按钮不可用**/
           	function unEnableTwoButton(){disableObject('q_query');disableObject('u_update');}
           	
           	/**显示两个按钮可用	**/
           	function enableTwoButton(){ableObject('q_query');ableObject('u_update');};
           	
           	/**查询失败时操作**/
           	function doing_queryError(){
           									/**两个等待div隐藏*/
           		hideObject('c_query');
           		hideObject('c_update');
           		
            	ableObject('q_query');		/**查询可用、更新不可用*/
            	disableObject('u_update');
            	
            	$('q_action_id').value = ""; /**查询动作标示为空*/
           	}
           	
           	
          
          
          
           	
           	
           	//************************************************下面是更新操作执行的函数
          
          
          
          /**
          *		更新车载参数
          **/
          function updateParams(){
          
          
          		if(!checkEmpty()){
					window.alert('请输入合法的参数值，不允许为空!');
					return;
				}
				
				if(!checkNumberic()){
					window.alert('请输入一个合法的数字值');
					return  ;
				}
				
				
				if(!checkUpDown()){
					window.alert('请输入合法的参数值、上限值必须大于下限值');
					return;
				}
				
				if(!checkUpDown_2()){
					window.alert('请输入合法的参数值!\n  1.报警上限必须大于预警上限\n  2.报警下限必须小于预警下限');
					return ;
				}
				
				if(!checkValue()){
					window.alert('请输入合法的参数值!\n  1.温度数值设置范围在 -25℃ --50℃\n  2.湿度数值设置范围在 0%--100%\n  3.延时时间设置范围在 0(s)--1800(s)');
					return ;
				}
          		
          		
          		configOperate.UpdateCarParam($('proId').value,buildInfo(),getPersonInfo(''),{
          			callback:update_params_Handler,
          			errorHandler:update_paramserror_Handler,
          			timeout:10000
          		});
          		
          		//显示更新等待div 、隐藏 查询等待div
          		showObject('c_update');
          		hideObject('c_query');
          		
          		timecount = defaultWaitTime ;
          		updateWaitingHandler();
          		
          		
          		//disableObject('q_query');
          		//disableObject('u_update');
          		
          		unEnableTwoButton() ;	//两按钮不可用
          		
          		$('failpic_query').innerHTML = '' ;
            	$('successpic_query').innerHTML = '' ;
          		
          		lockPageData(0) ;		//锁定数据不允许修改
          		
          		
          		//window.alert(buildInfo());
          }
          
          
          /**
          *		更新车载参数的处理函数
          **/
          function update_params_Handler(result){
          		if(result!=null && result!=0){
          			$('u_action_id').value = result ;
          			update_getStatus(result);
          		}else{
          			update_paramserror_Handler() ;
          		}
          }
          
          function update_paramserror_Handler(){
          		$("failpic_query").innerHTML = failurl +  " 更新参数信息失败!" ;	    		
          		
          		//hideObject('c_update'); 
          		//ableObject('u_update');
          		
          		            	          		  
          		 /**页面元素支持再次更新操作*/
          		   		
          		  	
          		
          		doing_updateError() ;	 
          }
          
          /**
          **	获取更新车载参数后的成功状态
          **/
          function update_getStatus(aid){       
          		configOperate.QueryOptStatus(aid,{
          			callback:update_getStatus_Handler,
          			errorHandler:update_getStatuserror_Handler,
          			timeout:10000
          		});
          		
          		update_timetask = window.setTimeout(function (){
          			update_getStatus(aid) ;
          		},10000);
          }
          
          /**
          *		更新车载参数后、等待ais交互，确定最终的执行结果
          **/
          function update_getStatus_Handler(result){
          	if(result!=null){
          		if(result==1){
          			return  ;			//undo waiting
          		}else if (result==2){         		
          								//操作成功
          			$('successpic_query').innerHTML = successurl + "数据更新成功!" ;
          			$("failpic_query").innerHTML = "" ;
          			doing_updateSuccess() ;
          			writeLog("车载"+$('proId').value+" 更新成功");	
          		}else if (result==4){        			
          								//用户自己取消操作
          			$("failpic_query").innerHTML = failurl +  "操作已取消,更新参数失败!" ;	
          			doing_updateError() ;
          			writeLog("车载"+$('proId').value+" 操作取消，更新失败");				
          		}else if (result==14){
          			   					//车载尚未连接
          			 $("failpic_query").innerHTML = failurl +  "车载尚未启动,更新参数失败!" ;	
          			 doing_updateError() ;  
          			 writeLog("车载"+$('proId').value+" 车载未启动，更新失败");				
          		}else{
          								//未知原因失败
          			 $("failpic_query").innerHTML = failurl +  "未知原因,更新参数失败!" ;	
          			 doing_updateError() ; 
          			 writeLog("车载"+$('proId').value+" 未知原因，更新失败");
          		}
          		window.clearTimeout(update_timetask);
          	}else{
          		update_getStatuserror_Handler() ;
          	}        	
          	window.clearTimeout(update_waittimetask);			//不管什么结果、只要不是继续等待、则关闭等待计时器.
          }
          
          function update_getStatuserror_Handler(){
          		$("failpic_query").innerHTML = failurl +  " 更新参数失败!" ;
          		window.clearTimeout(update_timetask);
          		doing_updateError() ;
          }
          
         /**更新失败处理函数*/
         function doing_updateError(){
         	enableTwoButton() ;
         	
         	hideObject('c_query');
         	hideObject('c_update');
         	
         	$('u_action_id').value = "" ;    
         	$('successpic_query').innerHTML ="" ;
         	
         	lockPageData(1); 		//数据解锁，可以继续更新   	
         }
         
         
         /**更新成功处理函数*/
         function doing_updateSuccess(){
         
         	hideObject('c_query');
         	hideObject('c_update');
         	
         	ableObject('q_query');
         	disableObject('u_update');    	
         	$('u_action_id').value = "" ;
        	
         }
         
         
          
          /**
          *		当取消更新时操作、暂时不提供更新取消
          **/
          function  doCancle_update(){
          
          		configOperate.UpdateOptStatus($('u_action_id').value,4,{
          			callback:function(){},
          			errorHandler:function(){},
          			timeout:10000
          		});
          
          		hideObject('c_update');
          		hideObject('c_query');
          		
          		enableTwoButton() ;
          		$('u_action_id').value = "" ;
          		
          		lockPageData(1) ;
       			window.clearTimeout(update_timetask);      			
          }
          
          /**更新等待处理程序*/
          function updateWaitingHandler(){
          	
          		if(timecount<0)		//如果时间到了，并没有更新成功，则超时失败
          		{
          				window.clearTimeout(update_waittimetask);
          		
          				doCancle_update();		//类似于取消          				        				       				
          				
          				$("failpic_query").innerHTML = failurl +  "操作超时,更新参数失败!" ;
          				
          				
          				timecount = defaultWaitTime ;	//设置
          				
          				writeLog("车载"+$('proId').value+" 操作超时，更新失败");  
          				 				
          				return  ;     				
          		}else{
          			var str = timecount<10?"0"+timecount+"s":timecount+"s" ;
          			$('havetime').innerHTML = str ;
          			timecount = timecount - 2 ;
          		
          			update_waittimetask = window.setTimeout('updateWaitingHandler()',2000);
          		}
          		
          		
          }
          
          
          
          /**
          *	当更新的时候、页面的数据锁定
          * 0 代表锁定数据
          *	非0 代表解开锁定
          */
          function lockPageData(order){
          	
          	if(order==0){
	          	for(var i=1;i<=16;i++)
	          		disableObject('q'+i);
          	}else{
          			for(var j=1;j<=16;j++)
          				if($('qq'+j).value != "N/A" )
          					ableObject('q'+j);
          		}
          }
          
          /**
          *		 根据页面变化的值，传递到服务器更新
          **/     
          function buildInfo(){
          	var str = "" ;
          	
          	//循环遍历每一个元素、判断是否已经修改
          	for(var k=1;k<=16;k++){
          						//先判断是否需要修改			
          		if($('qq'+k).value=="N/A"){
          			str= str+k+",N/A,N/A" ;
          		}else{
          						//再判断页面是否已经修改了参数设置
          			if($('qq'+k).value!=$('q'+k).value)
          			{
          				str = str+k+",1,"+$('q'+k).value;
          			}else{
          				str = str +k+",N/A,N/A";		//如果没有修改参数、则不需要系统更新
          			}
          			
          		}
          			if(k!=16)
          				str= str+";";
          	}
          	
          	str = str+"&" ;
          	//下面是增加了日志操作，需要的数据解析 eg: name,oldValue,newValue
          	for(var q=1;q<=16;q++){
          		str = str +q+","+$('qq'+q).value+","+$("q"+q).value ;
          		if(q!=16)
          			str = str+";";
          	}
          	
          	return str ;
          }
          
          /***
          **	确保所有更新后的值都是数字型
          **/
          function checkNumberic(){
          	var ispass = true ;
          		for(var i=1;i<=16;i++){
          			if(isNaN($('q'+i).value)){	
          				if($('q'+i).value!="N/A"){
          					ispass = false ;
          					$('q'+i).select();
          					break ;
          				}
          			}
          		}
          		return ispass ;
          }
          
          
        
   /**
   *	这个是检查上下限大小限制
   **/
  	function checkUpDown(){
  		
  		if($('q1').value!="N/A" && $('q3').value!="N/A")
  		{
  			if(parseFloat($('q1').value)<= parseFloat($('q3').value))
  			return false ;
  		}
  		
  		if($('q5').value!="N/A" && $('q7').value!="N/A")
  		{
  			if(parseFloat($('q5').value)<= parseFloat($('q7').value))
  			return false ;
  		}
  		
  		if($('q9').value!="N/A" && $('q11').value!="N/A")
  		{
  			if(parseFloat($('q9').value)<= parseFloat($('q11').value))
  			return false ;
  		}
  		
  		if($('q13').value!="N/A" && $('q15').value!="N/A")
  		{
  			if(parseFloat($('q13').value)<= parseFloat($('q15').value))
  			return false ;
  		}
  		return true ; 		
  	}
  	
  	 /**
   			 * 判断所有的文本框是否为空
   		    */
		   function checkEmpty(){
		   		for(var i=1;i<=16;i++){
					if($('q'+i).value==""  || $('q'+i).value.replace(/(^\s*)|(\s*$)/g, "")==""){
						return false ;
						$('q'+i).focus();
					}
				}
				return true ;
		   }
		
		/**
		 * 判断报警与预警的大小关系
		 */
		function checkUpDown_2(){
			//温度报警上限大于温度预警上限
			if($('q1').value!="N/A" && $('q9').value!="N/A")
	  		{
	  			if(parseFloat($('q9').value)<= parseFloat($('q1').value))
	  			return false ;
	  		}
			
			//温度报警下限小于温度预警下限
			if($('q3').value!="N/A" && $('q11').value!="N/A")
	  		{
	  			if(parseFloat($('q3').value)<= parseFloat($('q11').value))
	  			return false ;
	  		}
			
			//湿度报警上限大于湿度预警上限
			if($('q5').value!="N/A" && $('q13').value!="N/A")
	  		{
	  			if(parseFloat($('q13').value)<= parseFloat($('q5').value))
	  			return false ;
	  		}
			//湿度报警下限小于湿度预警下限
			if($('q7').value!="N/A" && $('q15').value!="N/A")
	  		{
	  			if(parseFloat($('q7').value)<= parseFloat($('q15').value))
	  			return false ;
	  		}
			
				return true ;
		}
		
		/**
		 * 检测温湿度值、延时时间是否合法
		 */
		function checkValue(){
			//判断温度 1 3 9 11
			for(var i=1;i<=2;i++){
				var v1 = parseFloat($("q"+(1+(i-1)*8)).value) ;
				var v3 = parseFloat($("q"+(3+(i-1)*8)).value) ;
				
				if(v1>50 || v1<-25 || v3>50 || v3<-25){
					return false ;
				}				
			}
			
			//判断湿度 5 7 13 15
			for(var j=1;j<=2;j++){
				var v5 = parseFloat($("q"+(5+(j-1)*8)).value) ;
				var v7 = parseFloat($("q"+(7+(j-1)*8)).value) ; 
				
				if(v5<0 || v5>100 || v7>100  || v7<0){
					return false ;
				}
					
			}
			
			//判断时间2 4 6 8 10 12 14 16
			for(var k=2;k<=16;k=k+2){
				var tk  = parseFloat($('q'+k).value) ;
				if(tk<0 || tk>1800){
					return false ;
				}
			}
			
			return true ;		 
		}
  	
  	
  	
	    
     /**获取用户信息，记录日志信息*/
     function getPersonInfo(argMessage){
     	var per = {
     		uid:$('uid').value,
     		uname:$('uname').value,
     		machineName:$('machine').value,
     		ext01:$('ext01').value,
     		ext02:$('ext02').value,
     		message:argMessage
     	} ;
     	return per ;
     }
     
     /**
     *	插入日志信息
     **/     	
        function writeLog(ms){
        	configOperate.addUpdateResultLog(getPersonInfo(ms),{
        		callback:writeLoghandler,
        		errorHandler:writeLogErrorhandler,
        		timeout:10000
        	})
        }
        function writeLoghandler(){}
        function writeLogErrorhandler(){}   	