package com.learning.design.compound.mvc.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.learning.design.compound.mvc.BPMObserver;
import com.learning.design.compound.mvc.BeatObserver;
import com.learning.design.compound.mvc.controller.BeatControllerInterface;
import com.learning.design.compound.mvc.model.BeatModelInterface;

public class DJView implements ActionListener, BeatObserver, BPMObserver
{
	/*
	 * Has model as an instance variable to register itself for any changes in the Model's sthate
	 */
	private BeatModelInterface beatModel;
	
	/*
	 * Controller is the behaviour of the View. Controller is the Strategy Object here.
	 * Any time View needs a different behaviour it can replace this Controller with some other Controller.
	 */
	BeatControllerInterface beatController;
	JFrame viewFrame;
	JPanel viewPanel;
	BeatBar beatBar;
	JLabel bpmOutputLabel;
	
	JFrame controlFrame;
	JPanel controlPanel;
	JLabel bpmLabel;
	JTextField bpmTextField;
	JButton setBPMButton;
	JButton increaseBPMButton;
	JButton decreaseBPMButton;
	
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem startMenuItem;
	JMenuItem stopMenuItem;
	
	public DJView (BeatControllerInterface beatController, BeatModelInterface beatModel)
	{
		this.beatController = beatController;
		this.beatModel = beatModel;
		
		/*
		 * Registers with Model for Sthate Changes as Observer. It gets the Model's sthate it displays directly from the Model.
		 */
		beatModel.registerBeatObserver(this);
		beatModel.registerBPMObserver(this);
	}
	
	public void createView ()
	{
		viewPanel = new JPanel (new GridLayout (1, 2));
		
		viewFrame = new JFrame ("View");
		viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		viewFrame.setSize(new Dimension (100, 80));
		
		bpmOutputLabel = new JLabel ("Offline", SwingConstants.CENTER);
		
		beatBar = new BeatBar ();
		beatBar.setValue(0);
		
		JPanel bpmPanel = new JPanel (new GridLayout (2, 1));
		bpmPanel.add(beatBar);
		bpmPanel.add(bpmOutputLabel);
		
		viewPanel.add(bpmPanel);
		viewFrame.getContentPane().add(viewPanel, BorderLayout.CENTER);
		viewFrame.pack();
		viewFrame.setVisible(true);
	}
	
	public void createControls ()
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		controlFrame = new JFrame ("Control");
		controlFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		controlFrame.setSize(new Dimension (100, 80));
		
		controlPanel = new JPanel (new GridLayout (1, 2));
		
		menuBar = new JMenuBar ();
		menu = new JMenu ("DJ Control");
		startMenuItem = new JMenuItem ("Start");
		menu.add(startMenuItem);
		startMenuItem.addActionListener(new ActionListener ()
		{
			public void actionPerformed (ActionEvent actionEvent)
			{
				beatController.start();
			}
		}
		);
		
		stopMenuItem = new JMenuItem ("Stop");
		menu.add(stopMenuItem);
		stopMenuItem.addActionListener(new ActionListener ()
		{
			public void actionPerformed (ActionEvent actionEvent)
			{
				beatController.stop();
			}
		}
		);
		
		JMenuItem exit = new JMenuItem ("Quit");
		menu.add(exit);
		exit.addActionListener(new ActionListener ()
		{
			public void actionPerformed (ActionEvent actionEvent)
			{
				System.exit(0);
			}
		}
		);
		
		menuBar.add(menu);
		
		controlFrame.setJMenuBar(menuBar);
		
		bpmTextField = new JTextField(2);
		bpmLabel = new JLabel ("Enter BPM", SwingConstants.RIGHT);
		setBPMButton = new JButton ("Set");
		setBPMButton.setSize(new Dimension (10, 40));
		increaseBPMButton = new JButton (">>");
		decreaseBPMButton = new JButton ("<<");
		setBPMButton.addActionListener(this);
		increaseBPMButton.addActionListener(this);
		decreaseBPMButton.addActionListener(this);
		
		JPanel buttonPanel = new JPanel (new GridLayout (1, 2));
		
		buttonPanel.add(increaseBPMButton);
		buttonPanel.add(decreaseBPMButton);
		
		JPanel enterPanel = new JPanel (new GridLayout(1, 2));
		enterPanel.add(bpmLabel);
		enterPanel.add(bpmTextField);
		
		JPanel insideControlPanel = new JPanel (new GridLayout (3, 1));
		insideControlPanel.add(enterPanel);
		insideControlPanel.add(setBPMButton);
		insideControlPanel.add(buttonPanel);
		controlPanel.add(insideControlPanel);
		
		bpmLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		bpmOutputLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		controlFrame.getRootPane().setDefaultButton(setBPMButton);
		controlFrame.getContentPane().add(controlPanel, BorderLayout.CENTER);
		
		controlFrame.pack();
		controlFrame.setVisible(true);
	}
	
	public void enableStopMenuItem ()
	{
		stopMenuItem.setVisible(true);
	}
	
	public void disableStopMenuItem ()
	{
		stopMenuItem.setVisible(false);
	}

	public void enableStartMenuItem ()
	{
		startMenuItem.setVisible(true);
	}
	
	public void disableStartMenuItem ()
	{
		startMenuItem.setVisible(false);
	}
	
	/*
	 * View delegates the user action to Controller.
	 */
	public void actionPerformed (ActionEvent actionEvent)
	{
		if (actionEvent.getSource() == setBPMButton)
		{
			int bpm = Integer.parseInt(bpmTextField.getText());
			beatController.setBPM(bpm);
		}
		else if (actionEvent.getSource() == increaseBPMButton)
		{
			beatController.increaseBPM();
		}
		else if (actionEvent.getSource() == decreaseBPMButton)
		{
			beatController.decreaseBPM();
		}
	}
	
	/*
	 * Modifies the display when notified by the Model directly for any sthate changes.
	 */
	public void updateBeat()
	{
		beatBar.setValue(100);
	}

	public void updateBPM()
	{
		int bpm = beatModel.getBPM();
		if (bpm == 0)
		{
			bpmOutputLabel.setText("Offline");
		}
		else
		{
			bpmOutputLabel.setText("Current BPM : " + beatModel.getBPM());
		}
	}
}

class BeatBar extends JProgressBar implements Runnable
{
	JProgressBar jProgressBar;
	Thread thread;
	
	public BeatBar ()
	{
		thread = new Thread (this);
		setMaximum(100);
		thread.start();
	}
	
	public void run ()
	{
		while (true)
		{
			int value = getValue();
			value = (int)(value * .75);
			setValue(value);
			repaint ();
			try
			{
				Thread.sleep(50);				
			}
			catch (Exception exception)
			{
exception.printStackTrace(System.err);				
			}			
		}
	}
}
