//My first Java Sound App
import javax.sound.midi.*;
public class music_app{
	public static void main(String[] args){
		music_app mini=new music_app();
		if(args.length<2)
			System.out.println("Dont forget the instrument and the note");
		else{
			int instrument=Integer.parseInt(args[0]);
			int note=Integer.parseInt(args[1]);
			mini.play(instrument,note);
		}
	}
	public void play(int instrument,int note){
		try{
			Sequencer player=MidiSystem.getSequencer();
			player.open();
			Sequence seq=new Sequence(Sequence.PPQ,4);
			Track track=seq.createTrack();
			
			MidiEvent event=null;
			ShortMessage first=new ShortMessage();
			first.setMessage(192,1,instrument,0);
			MidiEvent changeinstrument=new MidiEvent(first,1);
			track.add(changeinstrument);
			
			ShortMessage a=new ShortMessage();
			a.setMessage(144,1,note,127);
			MidiEvent noteon=new MidiEvent(a,1);
			track.add(noteon);
			
			ShortMessage b=new ShortMessage();
			b.setMessage(128,1,note,127);
			MidiEvent noteoff=new MidiEvent(b,16);
			track.add(noteoff);
			
			player.setSequence(seq);
			player.start();
		
		}catch(Exception ex){ex.printStackTrace();}
	}
}
