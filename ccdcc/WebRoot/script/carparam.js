			
			/**
			**	�����������õ�js
			**/
			
			var query_timetask = null ;
			
			var query_isContitue = false ;	//�˱���������ʾ�û��Ƿ��Ѿ�ȡ���˲�ѯ����
			
			var update_timetask = null ;
			
			var defaultWaitTime = 30 ;		//Ĭ�ϵȴ�ʱ��
			
			var timecount = defaultWaitTime ;
			
			var update_waittimetask = null ;
			
			
			
			var failurl = '<img src="img/util/fail.gif" alt="Fail" align="middle" />' ;
			
			var successurl = '<img src="img/util/isok.gif" alt="Success" align="middle" />' ;
			
			/**
             * ���ء���ʾdiv
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
             * ����id��ȡdom����
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
            *	��ȡ���ز���Ԥ����ִ�л�ȡ��������
            */
            function getCarParam_pre(){
            	var p = $("proId").value ;
            	if(p==null ||p==""){
            		window.alert("��ѡ����Ҫ���õĳ���!");
            		return ;
            	}
            	
            	configOperate.insertOperate(p,2,1,1,{
            		callback:query_insert_Handler,
            		errorHandler:query_inserterror_handler,
            		timeout:10000
            	});
            	
            	/** ��ѯ�����°�ť�������ã��ȴ����ݷ���
            	disableObject('q_query');	
            	disableObject('u_update');
            	*/
            	
            	unEnableTwoButton();			//����ť������
            	
            	showObject('c_query');		    	/**��ʾ��ѯ�ȴ�div*/
            	
            	hideObject('c_update'); 			/**����һЩ��ʾ�ı����Ѿ����µȴ�div*/
            	$('failpic_query').innerHTML = '' ;
            	$('successpic_query').innerHTML = '' ;
            	         	
            	dataInit();								/**�����ʾ�ı�*/
            	
            	query_isContitue = false ;				/**�ж�ȡ����ѯȡ������*/
            	
            	//hideObject('successpic_query');	          
            	//$('failpic_query').innerHTML = "" ;	/**�����ʾ�ı�*/
            }
            
            /**
            *	�����ѯ���صĳ��ط��صĲ���
            */
            
            function query_insert_Handler(result){
            	//�жϷ��صĶ�����ʾ
            	if(result!=null && result>0){
            		//�������������ʾ����ȡ���ز���������Ϣ
            		$('q_action_id').value = result  ;         
            		getParamState(result);      		     
            	}else{
            		query_inserterror_handler();
            	}
            }
            
            /**
            *	��ѯ��������
            */
            function query_inserterror_handler(){
            	$("failpic_query").innerHTML =failurl+ "ִ�в�������ʧ��!" ;           	
            	doing_queryError() ;      	
            	//initAllTip();       	
            }
            
             /**
            *		��ȡ����״̬
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
            *		��ȡ��������״̬������
            **/
            
            function query_status_handler(result){
            
            	if(result!=null){        		     								
            		if(result==1){			//request        			
            			return ;//undo ,go on waiting.... 				
            		}else if (result==2)	//ok
            		{
	         			query_isContitue = true ;					//�û�����û��ȡ��������������ʾ������Ϣ
            			
            			getCarParam($('q_action_id').value);		//��ȡ���صĲ�����Ϣ
            			       				
            		}else if (result==14)	//unconnection
            		{
            			
            			//initAllTip() ;
            			doing_queryError() ;
            			$("failpic_query").innerHTML =failurl+ "������δ��������ȡ����ʧ��" ;
            			          			
            		}else if (result==4)	//cancel
            		{
            			//initAllTip();   
            			doing_queryError() ;  			
            			$("failpic_query").innerHTML =failurl+ "�����Ѿ�ȡ������ȡ����ʧ��" ;		
  			
            		}else	// if (result==13)	//unknow reason
            		{
            			//initAllTip();
            			doing_queryError() ;
            			$("failpic_query").innerHTML =failurl+ "δ֪ԭ�򣬻�ȡ����ʧ��" ;		         			    				
            		}
            		
            		window.clearTimeout(query_timetask);		//ֹͣʵʱ��ȡ״̬�ļ�ʱ�� 
            			
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
            	$("failpic_query").innerHTML = failurl+ "��ȡ����״̬ʧ��!" ;
            	window.clearInterval(query_timetask);         	   	
            }
            
            
            
            /**
           	*	ִ�л�ȡ��������
           	**/
           	
           	function getCarParam(aid){
           		configOperate.QueryCarParam(aid,{
           			callback:query_load_handler,
           			errorHandler:query_loaderror_handler,
           			timeout:10000
           		}) ;
           	}
           	
           	
           	/**
           	*	���س������ݴ�����
           	*	result name,valid,value;name,valid,value
           	**/
           	function query_load_handler(result){
           		//����û�û��ȡ���������������ʾ��������
           		if(query_isContitue){
	           		if(result!=null && result!='N/A'){
	           			parseResult(result) ;
	           			doing_querySuccess() ;
	           			$('successpic_query').innerHTML = successurl + "���ݻ�ȡ�ɹ�!" ;
	           					//querySuccessHandler();	           			
	           					//initAllTip() ;	
	           					//showObject('s');           			
	           		}else{
	           			query_loaderror_handler();
	           		}
           		}
           	}
           	/**
           	*	���س������ݴ�������
           	**/
           	function query_loaderror_handler(){
           	//	initAllTip() ;
           	//	dataInit();
           		doing_queryError() ;
           		$("failpic_query").innerHTML = failurl +  " ��ȡ������Ϣʧ��!" ;
           	}
           	
           	/**
           	*	����ַ�����������
           	**/
           	function parseResult(str){
           		var params = str.split(";");
           		//ѭ������ÿһ������
           		for(var i=0;i<params.length;i++){
           			var para = params[i].split(",");
           			//�����ǰ�������á�����ʾ����
           			if(para[1]!="N/A")	
           				ableObject('q'+para[0]) ;
           						
           			$('q'+para[0]).value = para[2] ;	//��ʾֵ
           			$('qq'+para[0]).value = para[2] ;	// ֵ�Ŀ��������ж��Ƿ��Ѿ�������
           		}
           	}
           	
           	/**
           	*	�����ѯȡ������
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
           	
           	
           	
           	
           	/**ȡ���Ĵ�����*/
           	function doCancleHandler(result){}//��ʱ������
           	
           	/**ȡ���Ĵ�������*/
           	function doCancleErrorHandler(){}//��ʱ������
           	
           	
           	/**
           	*	���³�ʼ��״̬
           
           	function initAllTip(){     
           	      	
           		ableObject('q_query');		//��ť��ԭ
           		disableObject('u_update');	
           			
            	hideObject('successpic_query');	//��ʾdiv��ԭ
            	hideObject('c_query');
            	hideObject('c_update');    
            	   								//����������ԭ
            	$('q_action_id').value = "" ;
            	$('u_action_id').value = "" ;
           	}
           	*/
           	/**
           	*	��ʼ������
           	**/
           	function dataInit(){
           		for(var j=1;j<=16;j++){
           				disableObject('q'+j);
	           			$('q'+j).value = "****";
	           			$('qq'+j).value = "****" ;
           			}
           	}
           	
           	/***
           	*	��ѯ���֮�󡢵ȴ����²���,Ҳ֧���ٴβ�ѯ
           	**/
           	function doing_querySuccess(){
           		enableTwoButton() ;			/**��ѯ��ť���á����°�ť���á����صȴ���ѯҳ��*/
           		hideObject('c_query');
           		hideObject('c_update');
           		
           		$('q_action_id').value="" ;
           		
           	}
           	
           	/**������ť������**/
           	function unEnableTwoButton(){disableObject('q_query');disableObject('u_update');}
           	
           	/**��ʾ������ť����	**/
           	function enableTwoButton(){ableObject('q_query');ableObject('u_update');};
           	
           	/**��ѯʧ��ʱ����**/
           	function doing_queryError(){
           									/**�����ȴ�div����*/
           		hideObject('c_query');
           		hideObject('c_update');
           		
            	ableObject('q_query');		/**��ѯ���á����²�����*/
            	disableObject('u_update');
            	
            	$('q_action_id').value = ""; /**��ѯ������ʾΪ��*/
           	}
           	
           	
          
          
          
           	
           	
           	//************************************************�����Ǹ��²���ִ�еĺ���
          
          
          
          /**
          *		���³��ز���
          **/
          function updateParams(){
          
          
          		if(!checkEmpty()){
					window.alert('������Ϸ��Ĳ���ֵ��������Ϊ��!');
					return;
				}
				
				if(!checkNumberic()){
					window.alert('������һ���Ϸ�������ֵ');
					return  ;
				}
				
				
				if(!checkUpDown()){
					window.alert('������Ϸ��Ĳ���ֵ������ֵ�����������ֵ');
					return;
				}
				
				if(!checkUpDown_2()){
					window.alert('������Ϸ��Ĳ���ֵ!\n  1.�������ޱ������Ԥ������\n  2.�������ޱ���С��Ԥ������');
					return ;
				}
				
				if(!checkValue()){
					window.alert('������Ϸ��Ĳ���ֵ!\n  1.�¶���ֵ���÷�Χ�� -25�� --50��\n  2.ʪ����ֵ���÷�Χ�� 0%--100%\n  3.��ʱʱ�����÷�Χ�� 0(s)--1800(s)');
					return ;
				}
          		
          		
          		configOperate.UpdateCarParam($('proId').value,buildInfo(),getPersonInfo(''),{
          			callback:update_params_Handler,
          			errorHandler:update_paramserror_Handler,
          			timeout:10000
          		});
          		
          		//��ʾ���µȴ�div ������ ��ѯ�ȴ�div
          		showObject('c_update');
          		hideObject('c_query');
          		
          		timecount = defaultWaitTime ;
          		updateWaitingHandler();
          		
          		
          		//disableObject('q_query');
          		//disableObject('u_update');
          		
          		unEnableTwoButton() ;	//����ť������
          		
          		$('failpic_query').innerHTML = '' ;
            	$('successpic_query').innerHTML = '' ;
          		
          		lockPageData(0) ;		//�������ݲ������޸�
          		
          		
          		//window.alert(buildInfo());
          }
          
          
          /**
          *		���³��ز����Ĵ�����
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
          		$("failpic_query").innerHTML = failurl +  " ���²�����Ϣʧ��!" ;	    		
          		
          		//hideObject('c_update'); 
          		//ableObject('u_update');
          		
          		            	          		  
          		 /**ҳ��Ԫ��֧���ٴθ��²���*/
          		   		
          		  	
          		
          		doing_updateError() ;	 
          }
          
          /**
          **	��ȡ���³��ز�����ĳɹ�״̬
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
          *		���³��ز����󡢵ȴ�ais������ȷ�����յ�ִ�н��
          **/
          function update_getStatus_Handler(result){
          	if(result!=null){
          		if(result==1){
          			return  ;			//undo waiting
          		}else if (result==2){         		
          								//�����ɹ�
          			$('successpic_query').innerHTML = successurl + "���ݸ��³ɹ�!" ;
          			$("failpic_query").innerHTML = "" ;
          			doing_updateSuccess() ;
          			writeLog("����"+$('proId').value+" ���³ɹ�");	
          		}else if (result==4){        			
          								//�û��Լ�ȡ������
          			$("failpic_query").innerHTML = failurl +  "������ȡ��,���²���ʧ��!" ;	
          			doing_updateError() ;
          			writeLog("����"+$('proId').value+" ����ȡ��������ʧ��");				
          		}else if (result==14){
          			   					//������δ����
          			 $("failpic_query").innerHTML = failurl +  "������δ����,���²���ʧ��!" ;	
          			 doing_updateError() ;  
          			 writeLog("����"+$('proId').value+" ����δ����������ʧ��");				
          		}else{
          								//δ֪ԭ��ʧ��
          			 $("failpic_query").innerHTML = failurl +  "δ֪ԭ��,���²���ʧ��!" ;	
          			 doing_updateError() ; 
          			 writeLog("����"+$('proId').value+" δ֪ԭ�򣬸���ʧ��");
          		}
          		window.clearTimeout(update_timetask);
          	}else{
          		update_getStatuserror_Handler() ;
          	}        	
          	window.clearTimeout(update_waittimetask);			//����ʲô�����ֻҪ���Ǽ����ȴ�����رյȴ���ʱ��.
          }
          
          function update_getStatuserror_Handler(){
          		$("failpic_query").innerHTML = failurl +  " ���²���ʧ��!" ;
          		window.clearTimeout(update_timetask);
          		doing_updateError() ;
          }
          
         /**����ʧ�ܴ�����*/
         function doing_updateError(){
         	enableTwoButton() ;
         	
         	hideObject('c_query');
         	hideObject('c_update');
         	
         	$('u_action_id').value = "" ;    
         	$('successpic_query').innerHTML ="" ;
         	
         	lockPageData(1); 		//���ݽ��������Լ�������   	
         }
         
         
         /**���³ɹ�������*/
         function doing_updateSuccess(){
         
         	hideObject('c_query');
         	hideObject('c_update');
         	
         	ableObject('q_query');
         	disableObject('u_update');    	
         	$('u_action_id').value = "" ;
        	
         }
         
         
          
          /**
          *		��ȡ������ʱ��������ʱ���ṩ����ȡ��
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
          
          /**���µȴ��������*/
          function updateWaitingHandler(){
          	
          		if(timecount<0)		//���ʱ�䵽�ˣ���û�и��³ɹ�����ʱʧ��
          		{
          				window.clearTimeout(update_waittimetask);
          		
          				doCancle_update();		//������ȡ��          				        				       				
          				
          				$("failpic_query").innerHTML = failurl +  "������ʱ,���²���ʧ��!" ;
          				
          				
          				timecount = defaultWaitTime ;	//����
          				
          				writeLog("����"+$('proId').value+" ������ʱ������ʧ��");  
          				 				
          				return  ;     				
          		}else{
          			var str = timecount<10?"0"+timecount+"s":timecount+"s" ;
          			$('havetime').innerHTML = str ;
          			timecount = timecount - 2 ;
          		
          			update_waittimetask = window.setTimeout('updateWaitingHandler()',2000);
          		}
          		
          		
          }
          
          
          
          /**
          *	�����µ�ʱ��ҳ�����������
          * 0 ������������
          *	��0 ����⿪����
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
          *		 ����ҳ��仯��ֵ�����ݵ�����������
          **/     
          function buildInfo(){
          	var str = "" ;
          	
          	//ѭ������ÿһ��Ԫ�ء��ж��Ƿ��Ѿ��޸�
          	for(var k=1;k<=16;k++){
          						//���ж��Ƿ���Ҫ�޸�			
          		if($('qq'+k).value=="N/A"){
          			str= str+k+",N/A,N/A" ;
          		}else{
          						//���ж�ҳ���Ƿ��Ѿ��޸��˲�������
          			if($('qq'+k).value!=$('q'+k).value)
          			{
          				str = str+k+",1,"+$('q'+k).value;
          			}else{
          				str = str +k+",N/A,N/A";		//���û���޸Ĳ���������Ҫϵͳ����
          			}
          			
          		}
          			if(k!=16)
          				str= str+";";
          	}
          	
          	str = str+"&" ;
          	//��������������־��������Ҫ�����ݽ��� eg: name,oldValue,newValue
          	for(var q=1;q<=16;q++){
          		str = str +q+","+$('qq'+q).value+","+$("q"+q).value ;
          		if(q!=16)
          			str = str+";";
          	}
          	
          	return str ;
          }
          
          /***
          **	ȷ�����и��º��ֵ����������
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
   *	����Ǽ�������޴�С����
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
   			 * �ж����е��ı����Ƿ�Ϊ��
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
		 * �жϱ�����Ԥ���Ĵ�С��ϵ
		 */
		function checkUpDown_2(){
			//�¶ȱ������޴����¶�Ԥ������
			if($('q1').value!="N/A" && $('q9').value!="N/A")
	  		{
	  			if(parseFloat($('q9').value)<= parseFloat($('q1').value))
	  			return false ;
	  		}
			
			//�¶ȱ�������С���¶�Ԥ������
			if($('q3').value!="N/A" && $('q11').value!="N/A")
	  		{
	  			if(parseFloat($('q3').value)<= parseFloat($('q11').value))
	  			return false ;
	  		}
			
			//ʪ�ȱ������޴���ʪ��Ԥ������
			if($('q5').value!="N/A" && $('q13').value!="N/A")
	  		{
	  			if(parseFloat($('q13').value)<= parseFloat($('q5').value))
	  			return false ;
	  		}
			//ʪ�ȱ�������С��ʪ��Ԥ������
			if($('q7').value!="N/A" && $('q15').value!="N/A")
	  		{
	  			if(parseFloat($('q7').value)<= parseFloat($('q15').value))
	  			return false ;
	  		}
			
				return true ;
		}
		
		/**
		 * �����ʪ��ֵ����ʱʱ���Ƿ�Ϸ�
		 */
		function checkValue(){
			//�ж��¶� 1 3 9 11
			for(var i=1;i<=2;i++){
				var v1 = parseFloat($("q"+(1+(i-1)*8)).value) ;
				var v3 = parseFloat($("q"+(3+(i-1)*8)).value) ;
				
				if(v1>50 || v1<-25 || v3>50 || v3<-25){
					return false ;
				}				
			}
			
			//�ж�ʪ�� 5 7 13 15
			for(var j=1;j<=2;j++){
				var v5 = parseFloat($("q"+(5+(j-1)*8)).value) ;
				var v7 = parseFloat($("q"+(7+(j-1)*8)).value) ; 
				
				if(v5<0 || v5>100 || v7>100  || v7<0){
					return false ;
				}
					
			}
			
			//�ж�ʱ��2 4 6 8 10 12 14 16
			for(var k=2;k<=16;k=k+2){
				var tk  = parseFloat($('q'+k).value) ;
				if(tk<0 || tk>1800){
					return false ;
				}
			}
			
			return true ;		 
		}
  	
  	
  	
	    
     /**��ȡ�û���Ϣ����¼��־��Ϣ*/
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
     *	������־��Ϣ
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