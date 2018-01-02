package com.learning.design.proxy.dynamicproxy;

public class PersonBeanImpl implements PersonBean
{
	private String loginName;
	private String name;
	private String gender;
	private String interest;
	private int rating;
	private int ratingCount;
	
	public String getLoginName ()
	{
		return loginName;
	}
	
	public String getGender()
	{
		return gender;
	}

	public int getHotOrNot()
	{
		return (ratingCount == 0) ? 0 : (rating/ratingCount);
	}

	public String getInterest()
	{
		return interest;
	}

	public String getName()
	{
		return name;
	}

	public void setLoginName (String loginName)
	{
		this.loginName = loginName;
	}
	
	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public void setHotOrNot(int rating)
	{
		this.rating += rating;		
		ratingCount ++;
	}

	public void setInterest(String interest)
	{
		this.interest = interest;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	
	public String toString ()
	{
		StringBuffer sb = new StringBuffer (1000);
		sb.append("\tLogin Name : ").append(loginName);
		sb.append("\tName : ").append (name);
		sb.append("\tgender : ").append (gender);
		sb.append("\tInterest : ").append (interest);
		sb.append("\tHotOrNot : ").append (getHotOrNot());
		
		return sb.toString();
	}
}
