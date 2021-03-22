package com.ultrapower.eoms.common.portal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="BS_T_SM_ENTRIES_NUMBER")	
public class EntriesNumber {
	private String pid;
	private int peopleNumber;
	
	public EntriesNumber()
	{
		
	}
	
	public EntriesNumber(String pid)
	{
		this.pid = pid;
	}
	
	public EntriesNumber(String pid,  int peopleNumber) 
	{
		this.pid = pid;
		this.peopleNumber = peopleNumber;
	}
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "assigned")
	@Column(name = "PID", unique = true, nullable = false, insertable = true, updatable = true, length = 50)
	public String getPid() {
		return this.pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
	
	@Column(name = "PEOPLENUMBER")
	public int getPeopleNumber() {
		return peopleNumber;
	}

	public void setPeopleNumber(int peopleNumber) {
		this.peopleNumber = peopleNumber;
	}
	
}
