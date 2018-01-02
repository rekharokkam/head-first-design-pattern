package com.learning.design.compound.model2.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.learning.design.compound.mvc.controller.BeatControllerInterface;
import com.learning.design.compound.mvc.model.BeatModel;
import com.learning.design.compound.mvc.model.BeatModelInterface;

public class BeatControllerServlet extends HttpServlet implements BeatControllerInterface
{
	public void init ()
		throws ServletException
	{
		BeatModelInterface model = new BeatModel ();
		model.initialize();
		getServletContext().setAttribute("beatModel", model);
	}
	
	/*
	 * Receives the Web Browser request as HTTPRequest and translates it so tat it can make request on the Model.
	 */
	public void doGet (HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException
	{
		String operation = request.getParameter("operation");
System.out.println("THE OPERATION PRESSED BY THE USER IS : " + operation);	

		if (operation.equals("decrease"))
		{
			decreaseBPM();
		}
		else if (operation.equals("increase"))
		{
			increaseBPM();
		}
		else if (operation.equals("on"))
		{
			start();
		}
		else if (operation.equals("off"))
		{
			stop();
		}
		else if (operation.equals("set"))
		{
			int bpm = Integer.parseInt(request.getParameter("bpm"));
			setBPM(bpm);
		}
		
		/*
		 * After making request on the Model, Controller forwards the Control to View along with JavaBean representing the Model's state.
		 */
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/DJView.jsp");
		request.setAttribute("beatModel", getServletContext().getAttribute("beatModel"));
		dispatcher.forward(request, response);
	}
		
	public void decreaseBPM()
	{
		BeatModelInterface beatModel = (BeatModelInterface)getServletContext().getAttribute("beatModel");
		beatModel.setBPM(beatModel.getBPM() - 1);
	}

	public void increaseBPM()
	{
		BeatModelInterface beatModel = (BeatModelInterface)getServletContext().getAttribute("beatModel");
		beatModel.setBPM(beatModel.getBPM() + 1);
	}

	public void setBPM(int bpm)
	{
		BeatModelInterface beatModel = (BeatModelInterface)getServletContext().getAttribute("beatModel");
		beatModel.setBPM(bpm);
	}

	public void start()
	{
		BeatModelInterface beatModel = (BeatModelInterface)getServletContext().getAttribute("beatModel");
		beatModel.on();
	}

	public void stop()
	{
		BeatModelInterface beatModel = (BeatModelInterface)getServletContext().getAttribute("beatModel");
		beatModel.off ();
	}
}
