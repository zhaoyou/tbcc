package org.tbcc.struts.form;

import org.apache.struts.action.ActionForm;
import org.tbcc.entity.TbccUser;

/**
 *  ��װ�û���ʵ���ActionForm
 * @author Administrator
 *
 */
public class UserForm extends ActionForm {
	private TbccUser item = new TbccUser();

	public TbccUser getItem() {
		return item;
	}

	public void setItem(TbccUser item) {
		this.item = item;
	}
}
