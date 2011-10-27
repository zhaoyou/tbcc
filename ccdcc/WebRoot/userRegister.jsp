<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
        <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
    <html xmlns="http://www.w3.org/1999/xhtml">
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>ע�����û�</title>
    
    <link rel="Shortcut Icon" href="img/add/logo.ico" />
    
    <link href="css/admin/sign.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="script/jquery-1.4.min.js"></script>
    
    <script type="text/javascript">
	    function checkW(str){
			var w1 = /^([a-zA-Z_][\w]*)$/;
			return !w1.test(str);
		}
    
	    function checkmail()
		{
			var temp = $("#email");
			if(temp.val()==""){	
				$("#tip").html("�����ַ����Ϊ��");
				temp.css('border-color','red');
				return false;
			}
			//�Ե����ʼ�����֤
			var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
			if(!myreg.test(temp.val())||temp.val().length>30)
			{
				$("#tip").html("��������Ч��E_mail");
				temp.css('border-color','red');
				return false;
			}else{
				$("#tip").html("&nbsp;");
				temp.css('border-color','#8ea8c1');
				return true;
			}
		}
		
		
		
		function checkphone(str){
			//var mobile=/^((13[0-9]{1})|150|151|159|153|187|189){1}\d{8}$/;
			var phone = /^(\d{3,4})(\d{7,8})$/;
			if(!phone.test(str)){
				return false;
			}else{
				return true;
			}
		}
		
		function checkmobile(str){
			var mobile=/^((13[0-9]{1})|150|151|159|153|187|189){1}\d{8}$/;
			//var phone = /^(\d{3,4})(\d{7,8})$/;
			if(!mobile.test(str)){
				return false;
			}else{
				return true;
			}
		}
		
		function checkEmpty(obj){
			if(obj.value==""){
				obj.style.borderColor = "red";
				$("#"+obj.name+"Tip").css('color','red');
			}else{
				obj.style.borderColor = "#8ea8c1";
				$("#"+obj.name+"Tip").css('color','black');
			}
		}
		
		function submitForm(){
			var flag = true;
			var $input = $("form input:text");
			
			$input.each(function(){
				if($(this).attr("name")!="remark"&&$(this).attr("name")!="sex"){
					if($(this).val()==""){
						flag = false ;
						$(this).css('borderColor','red');
						$("#"+this.name+"Tip").css('color','red');
						
					}else{
						$(this).css('borderColor','#8ea8c1');
						$("#"+this.name+"Tip").css('color','black');
					}
				}
			});
			
			//��Ϣ�Ƿ�������������֤�Ƿ�ͨ��
			if(!flag){
				$('#tip').html( "��ȷ����Ϣ����!" ) ;
				return ;
			}
			
			
			var $rname = $("#rname");
			if(checkW($rname.val())||$rname.val().length>25||$rname.val().length<5){
				$("#tip").html("�û�������ĸ�����ֺ��»������,����5�����25���ַ�,�������ֿ�ͷ");
				$rname.css('border-color','red');
				return;
			}else{
				$rname.css('border-color','#8ea8c1');
			}
			
			if($("#position").val().length>25){
				$("#tip").html("ְλ���Ϊ25���ַ�");
				$("#position").css('border-color','red');
				return;
			}
			$("#position").css('border-color','#8ea8c1');
			
			if($("#profession").val().length>25){
				$("#tip").html("��ҵ���Ϊ25���ַ�");
				$("#profession").css('border-color','red');
				return;
			}
			$("#profession").css('border-color','#8ea8c1');
			
			if($("#companyname").val().length>25){
				$("#tip").html("��˾�������Ϊ25���ַ�");
				$("#companyname").css('border-color','red');
				return;
			}
			$("#companyname").css('border-color','#8ea8c1');
			
			if($("#companyAddress").val().length>25){
				$("#tip").html("��˾��ַ���Ϊ25���ַ�");
				$("#companyAddress").css('border-color','red');
				return;
			}
			$("#companyAddress").css('border-color','#8ea8c1');
			//û��ͨ�������ʽ��֤���򷵻�
			if(!checkmail()){
				return;
			}
			
			var $phone = $("#phone");
			var $fax = $("#fax");
			var $mobilephone = $("#mobilephone");
			
			if(!checkphone($phone.val())){
				$("#tip").html("��������Ч�İ칫�绰");
				$phone.css('border-color','red');
				return;
			}else{
				$("#tip").html("&nbsp;");
				$phone.css('border-color','#8ea8c1');
			}
			
			
			if(!checkphone($fax.val())){
				$("#tip").html("��������Ч�Ĵ������");
				$fax.css('border-color','red');
				return;
			}else{
				$("#tip").html("&nbsp;");
				$fax.css('border-color','#8ea8c1');
			}
			
			if(!checkmobile($mobilephone.val())){
				$("#tip").html("��������Ч���ֻ�����");
				$mobilephone.css('border-color','red');
				return;
			}else{
				$("#tip").html("&nbsp;");
				$mobilephone.css('border-color','#8ea8c1');
			}
			
			regiterForm.submit();
		}
		
		
    </script>
    
    </head>
    <body>
    <div>
      <iframe scrolling="no" src="common/header_register.jsp" width=100% height=126 frameborder=0></iframe >
    </div>
    <div id="right">
      <div id="top"><a href="#"><img src="images/canku/icon_c.gif" width="16" height="15" / class="tb4"><strong>λ��:��ҳ</strong>&lt;�����ڽ����û�ע��</a></div>
      <div id="center">
        <div>�û�ע��</div>
      </div>
      <div id="bottom">
      <form name="regiterForm" id="regiterForm" action="userRegister.do" method="post" style="width:40%; height:100%; margin:0 auto;">
      	<input type="hidden" id="ope" name="ope" value="doUserRegister" />
        <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0" id="tb">
          <tr style="background-color:#ebf4fd">
            <td width="105" height="31" style="text-align:right;">�û�����</td>
            <td width="274" height="31" align="left"><input name="rname" value=""  
                                id="rname" type="text" class="input_kk" style="height: 20px" tabindex="1"
                                onfocus="changeFocus(this);" onblur="checkEmpty(this);" />
          			<span id="rnameTip" style="font-weight: bold;color: black;">*</span>
                </td>
          </tr>
          <tr>
            <td height="31" style="text-align:right;">�Ա�</td>
            <td height="31" align="left"> ��
              <input name="sex" type="radio" id="sex" value="1" checked="checked" />
              ��
              <input type="radio" name="sex" id="sex" value="2" />
              Ů</td>
          </tr>
          <tr style="background-color:#ebf4fd">
            <td height="31" style="text-align:right;">ְλ��</td>
            <td height="31" align="left" id="clientName2"><input name="position"
                                id="position" type="text" class="input_kk" style="height: 20px" tabindex="2"
                                onfocus="changeFocus(this);" onblur="checkEmpty(this);" />
            <span id="positionTip" style="font-weight: bold;color: black;">*</span>
            </td>
          </tr>
          <tr>
            <td height="31" style="text-align:right;">��ҵ��</td>
            <td height="31" align="left"><input name="profession"
                                id="profession" type="text" class="input_kk" style="height: 20px" tabindex="3"
                                onfocus="changeFocus(this);" onblur="checkEmpty(this);" />
            	<span id="professionTip" style="font-weight: bold;color: black;">*</span>
            </td>
          </tr>
          <tr style="background-color:#ebf4fd">
            <td height="31"  style="text-align:right;">��˾���ƣ�</td>
            <td height="31" align="left" ><input name="companyname"  
                                id="companyname" type="text" class="input_kk" style="height: 20px" tabindex="4"
                                onfocus="changeFocus(this);" onblur="checkEmpty(this);" />
            <span id="companynameTip" style="font-weight: bold;color: black;">*</span></td>
          </tr>
          <tr>
            <td height="31" style="text-align:right;">��˾��ַ��</td>
            <td height="31" align="left"><input name="companyAddress"  
                                id="companyAddress" type="text" class="input_kk" style="height: 20px" tabindex="5"
                                onfocus="changeFocus(this);" onblur="checkEmpty(this);" />
            <span id="companyAddressTip" style="font-weight: bold;color: black;">*</span></td>
          </tr>
          <tr style="background-color:#ebf4fd">
            <td height="31"  style="text-align:right;">E-mail��</td>
            <td height="31" align="left" ><input name="email"  
                                id="email" type="text" class="input_kk" style="height: 20px" tabindex="6"
                                onfocus="changeFocus(this);" onblur="checkEmpty(this);" />
            <span id="emailTip" style="font-weight: bold;color: black;">*</span></td>
          </tr>
          <tr>
            <td height="31" style="text-align:right;">�칫�绰��</td>
            <td height="31" align="left"><input name="phone" 
                                id="phone" type="text" class="input_kk" style="height: 20px" tabindex="7"
                                onfocus="changeFocus(this);" onblur="checkEmpty(this);" />
           <span id="phoneTip" style="font-weight: bold;color: black;">*</span></td>
          </tr>
          <tr style="background-color:#ebf4fd">
            <td height="31"  style="text-align:right;">���棺</td>
            <td height="31" align="left" ><input name="fax"  
                                id="fax" type="text" class="input_kk" style="height: 20px" tabindex="8"
                                onfocus="changeFocus(this);" onblur="checkEmpty(this);" />
            <span id="faxTip" style="font-weight: bold;color: black;">*</span></td>
          </tr>
          <tr>
            <td height="31" style="text-align:right;">�ֻ���</td>
            <td height="31" align="left"><input name="mobilephone"  
                                id="mobilephone" type="text" class="input_kk" style="height: 20px" tabindex="9"
                                onfocus="changeFocus(this);" onblur="checkEmpty(this);" />
            <span id="mobilephoneTip" style="font-weight: bold;color: black;">*</span></td>
          </tr>
          <tr style="background-color:#ebf4fd">
            <td height="31"  style="text-align:right;">��ע��</td>
            <td height="31" align="left"><textarea name="remark"
                                id="remark" class="input_kk" style="height:40px;" tabindex="10"
                                onfocus="changeFocus(this);" onblur="changeBlur(this);" ></textarea></td>
          </tr>
          <tr>
            <td height="31" colspan="2"><font color="#FF0000"><span id="tip">${tip }</span></font></td>
          </tr>
          <tr>
            <td height="31" colspan="2"><div id="center">
                <div><img src="images/admin/an0061.gif" style="cursor: pointer;" onclick="javascript:submitForm();" width="70" height="19" tabindex="11" />
                <img src="images/admin/an0062.gif" style="cursor: pointer;" onclick="javascipt: regiterForm.reset();" width="45" height="19" tabindex="11"/>
                <img src="images/admin/an0063.gif" style="cursor: pointer;" onclick="window.location.href='index.jsp';" width="45" height="19" tabindex="11"/><br />
                </div>
              </div></td>
          </tr>
        </table>
       </form>
      </div>
    </div>
    <div class="clear"></div>
    <div>
      <iframe scrolling="no" src="common/footer2.jsp" width=100% height=43 frameborder=0></iframe >
    </div>
    </body>
    </html>




