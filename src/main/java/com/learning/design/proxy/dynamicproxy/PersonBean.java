package com.learning.design.proxy.dynamicproxy;

public interface PersonBean
{
	String getLoginName ();
	String getName ();
	String getGender ();
	String getInterest ();
	int getHotOrNot ();
	
	void setLoginName (String loginName);
	void setName (String name);
	void setGender (String gender);
	void setInterest (String interest);
	void setHotOrNot (int rating);	
}
