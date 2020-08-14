import java.awt.*;
import java.applet.AudioClip;

public class AudioPlay extends java.applet.Applet implements Runnable {

	AudioClip dot;
	AudioClip dash;
	Morse m;
	TextField tf;
	Label l;
	TextArea ta;
	Button btn_clear; Button btn_play;
	int txta_columns = 60;
	int txta_rows = 10;
	// thread vars
	Thread runner;
	boolean dotPlay = false;
	boolean dashPlay = false;
	boolean play = false;

	static int index = 0; //increment each time msg is parsed to change pos in string to set true or false for incrementMsg()

	void incrementMsg(){
		String testStr = ta.getText(); 
		if (index == testStr.length()){
			play = false;
			
			index = 0;
		}
		if(testStr.charAt(index) == '.'){
			dotPlay = true;
		}
		if(testStr.charAt(index) == '-'){
			dashPlay = true;
		}
		System.out.println(index);
		index += 1;
	}	

	public void start() {
		if (runner == null) {
			runner = new Thread(this);
			runner.start();
		}
	}
	public void stop() {
		if (runner != null) {
			//if (dot != null) dot.stop();
			//if (dash != null) dash.stop();
			runner.stop();
			runner = null;
		}
	}
	public void init() {
		dot = getAudioClip(getCodeBase(),"audio/dot.wav"); //bell.mp3
		dash = getAudioClip(getCodeBase(), "audio/dash.wav");
		m = new Morse();
		
		//setBackground(Color.white);
		l = new Label("Message", Label.LEFT);
		add(l);
		
		tf = new TextField("",30);
		add(tf);

		btn_clear = new Button("Clear");
		add(btn_clear);	
		
		ta = new TextArea("",txta_rows,txta_columns,1); //row, columns , SCROLLBARS_VERTICAL_ONLY=1
		add(ta);
	
		btn_play = new Button("Play");
		add(btn_play);
	}
	public boolean action(Event evt, Object arg) {
		if (evt.target instanceof TextField) {
			//add new line at > columns or
			String out = m.say(tf.getText());
			//ta.append(out);
			ta.setText(out); 
			out = "";
		}
		if (evt.target instanceof Button) {
			String label = (String)arg;
			if(label.equals("Clear")){
				ta.setText(""); 
				tf.setText(""); 
				
			}
			if(label.equals("Play")){
				play = true; 
				
			}  
		}
		return true;		
		
	
	}
	public void run() {
		
		while (runner != null) {
			// set true false here
			//incrementMsg() stops itself by setting play to false at the end of the message, reset this later
			try { Thread.sleep(300); }
			catch (InterruptedException e) { }
			if (dot != null & dash != null) {
				if (play){
					incrementMsg();
					if(dotPlay){
						dot.play();
						dotPlay = false; // so the dot plays once each pass, its then upto incrementMsg
					}
					if(dashPlay){
						dash.play();
						dashPlay = false;
					}
				}
			}
		}
	}
	
}
