/**
   	这个文件是用来Excel预览移动车载的数据
   */
   
   
   
    function printData(time1,time2){
    	//实例化Excel对象
		
		var objxls ;
		var objworkbooks ;
		var objsheet  ;
		try{
			 objxls = new ActiveXObject("Excel.Application");
        
         objworkbooks = objxls.Workbooks.Add();
        
         objsheet = objworkbooks.Worksheets(1);
			
		}catch(e){
			alert("您的浏览器已经阻止了打印,请按照步骤设置:");
			window.open("common/setUp.htm","setup","scrollable=yes,status=yes,scrollbars=yes,width=700,height=600");
			return  ;
		}
		
        
		
		
        
        // 获取表单数据对象
       	var table = document.getElementById("data"); 
       
       //获取打印的工程名字
       var project = document.getElementById("proId") ;
        var proName = project.options[project.selectedIndex].text ;
       	  
       //获取行列的数量
   	   var hang = table.rows.length-5;
	   var lie = table.rows(0).cells.length+1;  
	   
	   var flag = true ; 
	   if(hang>500)
	     flag =  window.confirm("您打印的数量太大了，将花费一段时间.你确定吗?")  ;
	   if(!flag)
	       return ;
	   
	   //设置探头数量(移动车载3个温度、1个湿度;小批零;冷库是动态的探头)
	   var Tcount = 3 ;
	   var Hcount = 1 ;
	   
	   //设置第一个列数字列的宽度
	   
	   objxls.Columns(1).ColumnWidth = 4 ;
  
  		//设置经纬度列头的宽度
        objxls.Columns(3+Tcount+Hcount).ColumnWidth = 13 ;
        objxls.Columns(3+Tcount+Hcount+1).ColumnWidth = 13 ;
        
        //设置温湿度列头的宽度
        for(var i=0;i<Tcount+Hcount;i++){
            objxls.Columns(3+i).ColumnWidth = 6
        }
	   
	   	// objxls.Columns(i).Font.Size = 9 ;
	    // objxls.Columns(i).ColumnWidth = 7 ;
	   
	   //设置Excel单元格的标题
	   for( var i=1;i<=lie ;i++){
	   
	   //设置表头的对齐方式、字体大小、列宽
	     objxls.Columns(i).HorizontalAlignment = 3 ;

	     
            if(i==1)
                objxls.Cells(1,i).value = "编号" ;
             else{
             
                 //设置时间字段的宽度为19.13
                  if(i==2){ 
                    objxls.Cells(1,i).ColumnWidth = 19.13 ;
                  }
                  objxls.Cells(1,i).value = table.rows(0).cells(i-2).innerText ;
                }
                
       }
	   /**
	    * 就是最后一列有点问题长度总是为5，所以这里直接复制、页面预览显示就没有什么问题了
	    */
	   objxls.Cells(1,lie).value = "报警状态";
	 
      
       //设置Excel单元格里面的值
	   
	   for(var i = 1 ;i<hang ;i++){
            for(var j = 0 ;j<lie;j++){   
			
                if(j==0){
                    objxls.Cells(i+1,j+1).value = i  ;
                }else{
                   if(j==1)//设置时间单元格里面格式      		
                        objxls.Cells(i+1,j+1).NumberFormatLocal = "yyyy-mm-dd hh:mm:ss" ;
						
                    if(j==lie-1){
                    	//设置最后一列如果为报警，则颜色为红色
                      if(table.rows(i).cells(j-1).innerText.toString().replace(/(^\s*)|(\s*$)/g, "")=="报警")                                 
                         objxls.Cells(i+1,j+1).Font.ColorIndex = 3   //if the text is Aram ,it color is Red
                    }                
                    objxls.Cells(i+1,j+1).value = table.rows(i).cells(j-1).innerText ;
                }
                
            }
       }
       
       
       //设置统计信息
       
        objxls.Range(objxls.Cells(hang+1,1),objxls.Cells(1+hang+2,1)).Select;
        objxls.Selection.HorizontalAlignment = 3 ;
        objxls.Selection.MergeCells = true ;
        objxls.Selection.value = "统计" ;
        
//        Excel.XlHAlign.xlHAlignCenter=-4108
//        Excel.XlVAlign.xlVAlignCenter=-4108

        objxls.Selection.HorizontalAlignment = -4108;
        objxls.Selection.VerticalAlignment = -4108;
        
        //设置统计值的项
        objxls.Cells(hang+1,2).value = "最大值" ;
        objxls.Cells(hang+2,2).value = "最小值" ;
        objxls.Cells(hang+3,2).value = "平均值" ;           
     
     //设置统计值
     
     /** like this 
     *aa bb cc
     *------------
     统计 min value
     */
        
        for(var i = 1 ;i<=3 ;i++){
            for(var j =1 ;j<=Tcount+Hcount ;j++){
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
        		
        		
        		//设置打印的参数值
        
                var  ws = objxls.ActiveSheet;  
                             
                ws.PageSetup.LeftHeader = " 监控时间:\n" + time1 + "到" + time2 ;   
                 // & new Date().toDateString()& " - "& newDate().toDateString();
               
                ws.PageSetup.CenterHeader = proName + " 温/湿度查询";
                ws.PageSetup.RightHeader = "共&N页,第&P页" ;
                ws.PageSetup.LeftFooter = "养护员:";
                ws.PageSetup.CenterFooter = "";
                ws.PageSetup.RightFooter = "打印日期:&D &T";
                
                objxls.ActiveSheet.PageSetup.LeftMargin= 2/0.035; 
                //页边距 左2厘米 
                 objxls.ActiveSheet.PageSetup.RightMargin = 2/0.035; 
                //页边距右3厘米 
                objxls.ActiveSheet.PageSetup.TopMargin = 4/0.035; 
                   //页边距上4厘米 
                objxls.ActiveSheet.PageSetup.BottomMargin = 5/0.035; 
                //页边距下5厘米 
                objxls.ActiveSheet.PageSetup.HeaderMargin = 1/0.035; 
                //页边距页眉1厘米 
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
            //预览Excel的数据
            objxls.ActiveWindow.SelectedSheets.PrintPreview(); 
       
        }catch(error){
            alert(error.description);
        }finally{
            try{
                  //  Excel.XlWindowView.xlNormalView=1
                if(objxls.ActiveWindow.View = 1){       // 分页预览
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