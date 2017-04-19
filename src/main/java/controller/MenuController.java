package controller;

/**
*@author Vikrant Mainali and Tomas Mendes
* @version 0.00.00
* @name MenuController.java
*/

import interfaces.MenuListener;

public class MenuController implements MenuListener
{

	@Override
	public void onAddButtonClicked() 
	{
		System.out.println("Name timeline: \nSet duration for timeline");
	}

	@Override
	public void onOpenButtonClicked()
	{
		System.out.println("Select the timeline you want to launch");
	}

	@Override
	public void onDeleteButtonClicked() 
	{
		System.out.println("Are you sure you want to delete this timeline");
	}

	@Override
	public void onSaveButtonClicked() 
	{
		System.out.println("Browse to the location where you want to save the timeline");
	}

	@Override
	public void onNEWTimeLineSelected(String timeLineName) 
	{
		// TODO Auto-generated method stub
		
	}

}
