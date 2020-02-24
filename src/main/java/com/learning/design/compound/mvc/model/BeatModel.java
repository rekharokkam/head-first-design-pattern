package com.learning.design.compound.mvc.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

import com.learning.design.compound.mvc.BPMObserver;
import com.learning.design.compound.mvc.BeatObserver;

public class BeatModel implements BeatModelInterface, MetaEventListener
{
	private int bpm = 90;
	
	private Sequence sequence;
	private Sequencer sequencer;
	private Track track;
	
	private List<BeatObserver> beatObservers = new ArrayList<BeatObserver>();
	private List<BPMObserver> bpmObservers = new ArrayList<BPMObserver>();
	
	public int getBPM()
	{
		return bpm;
	}

	public void initialize()
	{
		setUpMIDI ();
		buildTrackAndStart ();
	}

	public void off()
	{
		setBPM (0);
		sequencer.stop();
	}

	public void on()
	{
		setBPM(90);
		sequencer.start();
	}

	public void setBPM(int bpm)
	{
		this.bpm = bpm;	
		sequencer.setTempoInBPM(getBPM());
		notifyBPMObservers ();
	}

	public void meta(MetaMessage message)
	{
		if (message.getType() == 47)
		{
			beatEvent ();
			sequencer.start();
			setBPM (getBPM ());
		}
	}
	
	public void setUpMIDI ()
	{
		try
		{
			sequencer = MidiSystem.getSequencer();
			sequencer.open();
			sequencer.addMetaEventListener(this);
			sequence = new Sequence (Sequence.PPQ, 4);
			track = sequence.createTrack();
			sequencer.setTempoInBPM(getBPM ());
		}
		catch (Exception exception)
		{
exception.printStackTrace(System.err);			
		}
	}
	
	public void buildTrackAndStart ()
	{
		int[] trackList = {35, 0, 46, 0};
		
		sequence.deleteTrack(null);
		track = sequence.createTrack();
		
		makeTracks (trackList);
		track.add(makeEvent (192, 9, 1, 0, 4));
		try
		{
			sequencer.setSequence(sequence);
		}
		catch (Exception exception)
		{
exception.printStackTrace(System.err);			
		}		
	}
	
	void beatEvent ()
	{
		notifyBeatObservers ();
	}
	
	public void makeTracks (int[] list)
	{
		for (int i = 0; i < list.length; i++)
		{
			int key = list[i];
			
			if (key != 0)
			{
				track.add(makeEvent (144, 9, key, 100, i));
				track.add(makeEvent (128, 9, key, 100, i + 1));
			}
		}
	}
	
	public MidiEvent makeEvent (int comd, int chan, int one, int two, int tick)
	{
		try
		{
			ShortMessage shortMessage = new ShortMessage ();
			shortMessage.setMessage(comd, chan, one, two);
			return new MidiEvent (shortMessage, tick);
		}
		catch (Exception exception)
		{
exception.printStackTrace(System.err);			
		}
		return null;
	}
	
	/*
	 * Informs the Observers (View) about the changes int the Sthate.
	 * Model is not aware of who its Observers are. There by its decoupled from other layers.
	 */
	public void notifyBeatObservers()
	{
		Iterator<BeatObserver> it = beatObservers.iterator();
		while (it.hasNext())
		{
			it.next().updateBeat();
		}
	}

	public void notifyBPMObservers()
	{
		Iterator<BPMObserver> it = bpmObservers.iterator();
		while (it.hasNext())
		{
			it.next().updateBPM();
		}
	}

	public void registerBeatObserver(BeatObserver observer)
	{
		beatObservers.add(observer);		
	}

	public void registerBPMObserver(BPMObserver observer)
	{
		bpmObservers.add(observer);
	}

	public void removeBeatObserver(BeatObserver observer)
	{
		beatObservers.remove(observer);
	}

	public void removeBPMObserver(BPMObserver observer)
	{
		bpmObservers.remove(observer);
	}
}
