/**
  *		������ʷ���ݴ�ӡ
  **/
  
   function printData(time1,time2){
   
   		if(getBrowser()!=1)
    	{
    		window.alert("��ӡ����,��ֻ֧��IE�����!");
    		return ;
    	}
    	//ʵ����Excel����		
		var objxls ;
		var objworkbooks ;
		var objsheet  ;
		try{
		 objxls = new ActiveXObject("Excel.Application");
        
         objworkbooks = objxls.Workbooks.Add();
        
         objsheet = objworkbooks.Worksheets(1);
			
		}catch(e){
			alert("����������Ѿ���ֹ�˴�ӡ,�밴�ղ�������:");
			window.open("common/setUp.htm","setup","scrollable=yes,status=yes,scrollbars=yes,width=700,height=600");
			return  ;
		}
		
	
		
		
        
        // ��ȡ�����ݶ���
       	var table = document.getElementById("tb"); 
       	
       	//��ȡ���е�����
   	   var hang = table.rows.length-5;
	   var lie = table.rows(0).cells.length+1;  
	   
	  var flag = true ; 
	   if(hang>150){
	   
	     flag =  window.confirm("����ӡ������̫��,������15(S)����.��ȷ����?")  ; 
	     
	  	 if(!flag)
	       return ;
	   }else{
	   	window.alert("����ӡ��Щ����,������10(S)����.");
	   }
	   
	   
	   
       
       //��ȡ��ӡ�Ĺ�������
      //  var project = document.all.proId ;
      	var  p = document.getElementById("myrid");	
        var proName = p.options[p.selectedIndex].text ;		//��ȡ��Ŀ����
        var carrier = " " ;																//���½ǵ�����Ա
		var len = document.getElementById("aiSize").value ;											//̽ͷ���ܸ���
       	  
		//���������С��������ͷ���Զ�����
		
		  objxls.Cells.Font.Size = 7.5 ;
		  objxls.Range(objxls.Cells(1,1),objxls.Cells(1,20)).WrapText = true ;
		 
		  
       
	   
	  //����̽ͷ����(�ƶ�����3���¶ȡ�1��ʪ��;С����;����Ƕ�̬��̽ͷ)
//	   var Tcount = 6 ;
//	   var Hcount = 6 ;
	   
	   //���õ�һ���������еĿ��
	   
	   objxls.Columns(1).ColumnWidth = 3 ;
  
//  		//���þ�γ����ͷ�Ŀ��
//        objxls.Columns(3+Tcount+Hcount).ColumnWidth = 10 ;
//        objxls.Columns(3+Tcount+Hcount+1).ColumnWidth = 10 ;
        
        //������ʪ�ȵ����ֵ����Сֵ��ƽ��ֵ���п�
//        for(var o = 0 ;o<7 ;o++)
//        	objxls.Columns(5+o+Tcount+Hcount).ColumnWidth = 6.5 ;
//        
		
		//�����¶ȡ�ʪ�ȵ����ֵ����Сֵ��ƽ��ֵ���Լ�״̬����ͷ���
		for(var n=3;n<10;n++){
			//objxls.Columns(3+n+len).ColumnWidth = 5.5 ;
			objxls.Columns(n+parseInt(len)).ColumnWidth = 6 ;
		}
		
        //������ʪ����ͷ�Ŀ��
        for(var i=0;i<len;i++){
            objxls.Columns(3+i).ColumnWidth = 4 ;
        }
		
	   
	   //����Excel��Ԫ��ı���
	   for( var i=1;i<=lie ;i++){
	   
	   //���ñ�ͷ�Ķ��뷽ʽ�������С���п�
	     objxls.Columns(i).HorizontalAlignment = 3 ;

	     
            if(i==1)
                objxls.Cells(1,i).value = "���" ;
             else{
             
                 //����ʱ���ֶεĿ��Ϊ19.13
                  if(i==2){ 
                    objxls.Cells(1,i).ColumnWidth = 16.15 ;
                  }
                  objxls.Cells(1,i).value = table.rows(0).cells(i-2).innerText ;
                }
                
       }
	   /**
	    * �������һ���е����ⳤ������Ϊ5����������ֱ�Ӹ��ơ�ҳ��Ԥ����ʾ��û��ʲô������
	    */
	   objxls.Cells(1,lie).value = "����״̬";
	 
      
	  
       //����Excel��Ԫ�������ֵ
	   
	   for(var i = 1 ;i<hang ;i++){
            for(var j = 0 ;j<lie;j++){   
			
                if(j==0){
                    objxls.Cells(i+1,j+1).value = i  ;
                }else{
                   if(j==1)//����ʱ�䵥Ԫ�������ʽ      		
                        objxls.Cells(i+1,j+1).NumberFormatLocal = "yyyy-mm-dd hh:mm:ss" ;
						
                    if(j==lie-1){
                    	//�������һ�����Ϊ����������ɫΪ��ɫ
                      if(table.rows(i).cells(j-1).innerText.toString().replace(/(^\s*)|(\s*$)/g, "")=="����")                                 
                         objxls.Cells(i+1,j+1).Font.ColorIndex = 3   //if the text is Aram ,it color is Red
                    }                
                    objxls.Cells(i+1,j+1).value = table.rows(i).cells(j-1).innerText ;
                }
                
            }
       }
       
       
       //����ͳ����Ϣ
       
        objxls.Range(objxls.Cells(hang+1,1),objxls.Cells(1+hang+2,1)).Select;
        objxls.Selection.HorizontalAlignment = 3 ;
        objxls.Selection.MergeCells = true ;
        objxls.Selection.value = "ͳ��" ;
        
//        Excel.XlHAlign.xlHAlignCenter=-4108
//        Excel.XlVAlign.xlVAlignCenter=-4108

        objxls.Selection.HorizontalAlignment = -4108;
        objxls.Selection.VerticalAlignment = -4108;
        
        //����ͳ��ֵ����
        objxls.Cells(hang+1,2).value = "���ֵ" ;
        objxls.Cells(hang+2,2).value = "��Сֵ" ;
        objxls.Cells(hang+3,2).value = "ƽ��ֵ" ;           
     
     //����ͳ��ֵ
     
     /** like this 
     *aa bb cc
     *------------
     ͳ�� min value
     */
        
        for(var i = 1 ;i<=3 ;i++){
            for(var j =1 ;j<=len ;j++){
                objxls.Cells(hang +i,j+2).value = table.rows(hang+1+i).cells(j).innerText ;
            }
        }   
        
        
        
//        Excel.Constants.xlCenter=-4108
//        Excel.XlInsertShiftDirection.xlShiftToRight=-4161
        
        //set Printout pattarm
        try{
              
//            Excel.XlLineStyle.xlContinuous=1
//            Excel.XlBordersIndex.xlEdgeBottom=9
//            Excel.XlBordersIndex.xlEdgeLeft=7
//            Excel.XlBordersIndex.xlEdgeRight=10
//            Excel.XlBordersIndex.xlEdgeTop=8
//            Excel.XlBordersIndex.xlInsideHorizontal=12
//            Excel.XlBordersIndex.xlInsideVertical=11

//            range.Borders.Item(9).LineStyle = 1;
//            range.Borders.Item(7).LineStyle = 1;
//            range.Borders.Item(10).LineStyle = 1;
//            range.Borders.Item(8).LineStyle = 1;
//            range.Borders.Item(12).LineStyle = 1;
//            range.Borders.Item(11).LineStyle = 1; 
        		
        		
        		//���ô�ӡ�Ĳ���ֵ
        
                var  ws = objxls.ActiveSheet;  
                             
                ws.PageSetup.LeftHeader = " ���ʱ��:\n" + time1 + "��" + time2 ;   
                 // & new Date().toDateString()& " - "& newDate().toDateString();
               
                ws.PageSetup.CenterHeader = proName + " ��/ʪ�Ȳ�ѯ";
                ws.PageSetup.RightHeader = "��&Nҳ,��&Pҳ" ;
                ws.PageSetup.LeftFooter = "����Ա:"+carrier;
                ws.PageSetup.CenterFooter = "";
                ws.PageSetup.RightFooter = "��ӡ����:&D &T";
                
                objxls.ActiveSheet.PageSetup.LeftMargin= 0.8/0.035; 
                //ҳ�߾� ��2���� 
                 objxls.ActiveSheet.PageSetup.RightMargin = 2/0.035; 
                //ҳ�߾���3���� 
                objxls.ActiveSheet.PageSetup.TopMargin = 4/0.035; 
                   //ҳ�߾���4���� 
                objxls.ActiveSheet.PageSetup.BottomMargin = 5/0.035; 
                //ҳ�߾���5���� 
                objxls.ActiveSheet.PageSetup.HeaderMargin = 1/0.035; 
                //ҳ�߾�ҳü1���� 
                objxls.ActiveSheet.PageSetup.FooterMargin = 2/0.035; 
                
                
                ws.PageSetup.LeftMargin = objxls.Application.InchesToPoints(0.4);
                ws.PageSetup.RightMargin = objxls.Application.InchesToPoints(0.2);
                ws.PageSetup.TopMargin = objxls.Application.InchesToPoints(1.08);
                ws.PageSetup.BottomMargin = objxls.Application.InchesToPoints(0.68);
                ws.PageSetup.HeaderMargin = objxls.Application.InchesToPoints(0.22);
                ws.PageSetup.FooterMargin = objxls.Application.InchesToPoints(0.5);
                
                
                ws.PageSetup.PrintHeadings = false;
                ws.PageSetup.PrintGridlines = true;
                ws.PageSetup.CenterHorizontally = false;
                ws.PageSetup.CenterVertically = false;
                ws.PageSetup.Draft = false;
             // Excel.XlPageOrientation.xlLandscape=2 
                ws.PageSetup.Orientation = 2 
                ws.PageSetup.Zoom = 100;
                ws.PageSetup.FitToPagesWide = 1;
                ws.PageSetup.FitToPagesTall = 1;
                ws.PageSetup.PrintTitleRows = "$1:$1";  
                
                
            objxls.Visible = true;
			
			
			
            //Ԥ��Excel������
            objxls.ActiveWindow.SelectedSheets.PrintPreview(); 
       
	   		
        }catch(error){
            alert(error.description);
        }finally{
            try{
                  //  Excel.XlWindowView.xlNormalView=1
                if(objxls.ActiveWindow.View = 1){       // ��ҳԤ��
                    objworkbooks.Close(false, false);
                    objxls.Workbooks.Close();
                    objxls.DisplayAlerts = false;
                    objxls.Quit();
                    objworkbooks = "";
                    objxls = "";
                    
                }
               }
            catch(er){
              alert(er.description);
            }
        }
    }