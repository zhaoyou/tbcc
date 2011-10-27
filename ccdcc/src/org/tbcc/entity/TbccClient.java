package org.tbcc.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 这个实体类用来描述的是用户所属的客户
 * @author Administrator
 *
 */
public class TbccClient implements java.io.Serializable {
	
	private Long id ;
	
	private String clientName ;
	
	private Long transactionId  ;
	
	private TbccTransactionRoles transactionRole = new TbccTransactionRoles() ;
	
	private Set<TbccUser> users = new HashSet<TbccUser>();
	
	public TbccClient(){
		
	}
	
	public TbccClient(Long id, String clientName) {
		super();
		this.id = id;
		this.clientName = clientName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	


	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TbccBranchType))
			return false;
		TbccClient castOther = (TbccClient) other;
		if(this.getId().equals(castOther.getId()))
				return true ;
		return false ;
	}


	public int hashCode() {
		return this.id.intValue();
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public Set<TbccUser> getUsers() {
		return users;
	}

	public void setUsers(Set<TbccUser> users) {
		this.users = users;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public TbccTransactionRoles getTransactionRole() {
		return transactionRole;
	}

	public void setTransactionRole(TbccTransactionRoles transactionRole) {
		this.transactionRole = transactionRole;
	}
	
	
	
	
}
