// keys chr
public class Morse  {
	static final String[] alpha = {"A .-","B -...","C -.-.","D -..","E .","F ..-.","G --.","H ....","I ..","J .---","K -.-","L .-..","M --","N -.","O ---","P .--.","Q --.-","R .-.","S ...","T -","U ..-","V ...-","W .--","X -..-","Y -.--","Z --..","1 .----","2 ..---","3 ...--","4 ....-","5 .....","6 -....","7 --...","8 ---..","9 ----.","0 -----"};

	String get_morse(char letter){
		String morse = "";
		
		for(int x = 0; x < alpha.length; x++){
			if(letter == alpha[x].charAt(0)){
				morse = alpha[x].substring(1);
				break;
			}
		}
		
		return morse;
	}
	
	// go through each letter
	String say(String msg){
		msg = msg.toUpperCase();
		char letters[] = msg.toCharArray();
		
		String send = "\\";	
		for(int x = 0; x < letters.length; x++){
			
			send += get_morse(letters[x]);
			send += "\\";
			
		}
		System.out.println(send);
		return send;
	}
	
	

}
