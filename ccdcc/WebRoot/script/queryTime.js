/**
*����ļ���Ҫ������������ѯʱ������
*/


    
    /**
    *	version 1.0 ��ʱ����ֻ�ܹ�������7�췶Χ�ڵ�����
    *	������ѯ��ť���ж�������ʱ���Ƿ��������
    */
    function Interval(){
    
        //��ȡ��ʼʱ��
		 var startTime = document.myform.time1.value;
		 var endTime = document.myform.time2.value;
		 
		 if(startTime=="" || endTime==""){
            alert("��ѡ����ʼʱ��!") ;
            return  ;
         }
		 var tempDate,dayDate1,dayDate2,intervalDay ;
		
            //ת�����ڸ�ʽ 04-19-2007 ʹ֮�ܹ�����������
		   tempDate =startTime.split('-'); 
		   dayDate1 = new Date(tempDate[1]+'-'+tempDate[2]+'-'+tempDate[0]) ;
	       
		   tempDate = endTime.split('-'); 
		   dayDate2 = new Date(tempDate[1]+'-'+ tempDate[2]+'-'+tempDate[0]); 
		   
		   //�����ĺ�����ת��������
		   intervalDay = parseInt(Math.abs(dayDate1 -dayDate2)/1000/60/60/24); 
		   				   
		   
		   //�ж��������
		   if(intervalDay>7){
		    alert("��ѯ���������ܹ���������!");
		    document.myform.time2.focus();
			return ;
		   }
    
            //��ȡʱ����ֵ
	       	var intervalValue = document.myform.timevalue.value;
		
		      if(intervalValue == ""){
		          	alert("�������Ϊ��!");
		          	document.myform.timevalue.focus();
			         return ;
	       	  }else{
                 	      if(isNaN(intervalValue)){
                 	          alert("���ֵ�Ƿ�!");
        	                  document.myform.timevalue.focus();
			                  return ;
                            }else{
                                if(intervalValue<1){
                                     alert("���ֵ�Ƿ�!");
        	                         document.myform.timevalue.focus();
			                         return ;
                                }
                            }
                  
                  }
	       	  
	       	  
	       	  
                
          //��ȡ�������
		
        var timeType = document.myform.timeType.value ;
        
        //��λΪ3��ʾ��
		if(timeType== 3){
			if(intervalValue % 10 != 0){
				alert("��������Ϊ10����������");
				document.myform.timevalue.focus();
				return ;
			}
			if(intervalValue > 59){
				alert("����������1-59��");
				document.myform.timevalue.focus();
				return ;
			}
		}
		
		//��λΪ2��ʾ����
		if(timeType == 2){
			if(intervalValue < 1  || intervalValue > 59){
				alert("������������1-59��");
				document.myform.timevalue.focus();
				return ;
			}
		}
		
		//��λΪ1��ʾΪСʱ
		if(timeType == 1){
			if(intervalValue < 1  || intervalValue > 23){
				alert("Сʱ������Ϊ1-23");
				document.myform.timevalue.focus();
				return ;
			}
		}	
					
			//�ύ��
			
			document.myform.submit();
						
		}
		
		
		/**
		*	versin2.0�޸��������ʷ���ݲ�ѯ�ķ�ʽ����������ͣ�ĸ�������ԭ����10s������˼���ɸ�����ͣ��̬���á�
		*	������webҳ����ʾ����Ϊ2880����Ϊһ���綨����Լ����4-5(s)
		*	���ڵ�������ݲ�ѯ��30SΪ��������ѯһ��պ�Ϊ2880��
		*	ѡ��ʽΪ30��ʱ  ��   ����ʱ�䷶ΧΪ1�� ��ļ����ֵΪ 30 60 90 120 ... 
		*   ѡ��ʽΪ1 ����ʱ:	 ����ʱ�䷶ΧΪ2�� ���ӵļ����ֵΪ 1 2 3 4 5 6 7 ...
		*	ѡ��ʽΪ1 Сʱ�ģ�	����ʱ�䷶ΧΪ4���� Сʱ�ļ����ֵΪ 1 2 3 4 5 6 7 ...
		*/
		
		function queryData(){
			   //��ȡ��ʼʱ��
			 var startTime = document.myform.time1.value;
			 var endTime = document.myform.time2.value;
			 
			 //��������ʱ�����
			 var s,t ;
			 
          	//��ȡ�������
      		 var timeType = document.myform.timeType.value ;
        
            //��ȡʱ����ֵ
	       	var intervalValue = document.myform.timevalue.value;
			
			//�������ڵļ������
			var intervalDays = 0 ;
	       	
			//��֤��ʼʱ���Ƿ�Ϸ�
	       	if(startTime=="" || endTime==""){
				window.alert("��ѡ���ѯ����ʼʱ��!");
				return ;
			}else{
				if(startTime==endTime){
					window.alert("����ʱ�������ڿ�ʼʱ��!")
					return ;
				}
			}
			
			if(!(isvalid(startTime)&& isvalid(endTime))){
				window.alert("������Ϸ�������ֵ!");
				return ;
			}
			
			//��֤ʱ�����Ƿ�Ϸ�
			 if(intervalValue == ""){
		          	window.alert("��ѯ�������Ϊ��!");
		          	document.myform.timevalue.focus();
			         return ;
	       	  }else{
                 	      if(isNaN(intervalValue)){
                 	          window.alert("��ѯ���ֵ�Ƿ�!");
        	                  document.myform.timevalue.focus();
			                  return ;
                            }else{
                                if(intervalValue<1){
                                     window.alert("��ѯ���ֵ�Ƿ�!");
        	                         document.myform.timevalue.focus();
			                         return ;
                                }
                            }
                  
                  }
                  
          var ref = document.getElementById("myrid").value ;
		  
		  if(ref==null || ref==""){
		  	window.alert("��ѡ��һ����Ч�����");	
		  	return ;
		  }
		  
		  
				
	       s = buildDate(startTime) ;
		   e = buildDate(endTime) ;
		   
		   //��ȡ��ѯ��ʱ������������
		   intervalDays = getIntervalDays(s,e) ;
		   
		   
		   if(timeType=="3"){		//ѡ��������鿴
		   		if(intervalDays>1){
					window.alert("��������ѯʱ��ֻ�ܹ���ѯ1���������!");
					return ;
				}
				
				if(intervalValue%30!=0){
					window.alert("�����ѯ�ļ��������30(s)������������");
					 document.myform.timevalue.select();
					return  ;
				}		
		   }else if(timeType=="2"){	// ѡ����Ƿ��Ӳ鿴
				if(intervalDays>2){
						var atleast = Math.ceil(getIntervalMinutes(s,e)/2880) ;
					if(intervalValue<atleast){
						window.alert("���շ��Ӳ�ѯʱ,ֻ�ܹ���ѯ 2 ���������\nȷ����ѯ,��������Ҫ�� "+atleast+" ��������");
						 document.myform.timevalue.select();
						return ;
					}	
				}
		   	
		   }else if(timeType=="1"){					//ѡ���Сʱ�鿴
		   		if(intervalDays>120){
					var atleast = Math.ceil(getIntervalHours(s,e)/2880);
					if(intervalValue<atleast){
						window.alert("����Сʱ��ѯʱ,ֻ�ܹ���ѯ 4 ���µ�������\nȷ����ѯ,��������Ҫ�� "+atleast+" Сʱ����");
						 document.myform.timevalue.select();
						return ;
					}
				}
		   }
		  
		  
		  document.myform.submit();
		}
		
		/**
		 * �����ַ����������ڶ��� 
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
		 * ��ȡ�������ڶ������������
		 * @param {Object} startTime	��һ��ʱ��
		 * @param {Object} endTime		�ڶ���ʱ��
		 */
		function getIntervalDays(startTime,endTime){   
		  return Math.ceil(Math.abs(startTime - endTime)/1000/60/60/24); 
		}
		
		/**
		 * ��ȡ�������ڶ�������ķ�����
		 * @param {Object} startTime
		 * @param {Object} endTime
		 */
		function getIntervalMinutes(startTime,endTime){
			return Math.ceil(Math.abs(startTime - endTime)/1000/60); 
		}
		
		/**
		 * ��ȡ�������ڶ��������Сʱ��
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
		*	�ж����ڶ����ַ����Ƿ���ȷyyyy-MM-dd HH:mm:ss
		*/
		function isvalid(str){
			if(str.charAt(4)=="-" && str.charAt(7)=="-" &&
				   str.charAt(10)==" " && str.charAt(13)==":" && str.charAt(16)==":")
						return true ;
				return false ;
		}
		
		
		