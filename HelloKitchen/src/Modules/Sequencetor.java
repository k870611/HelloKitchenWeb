package Modules;

public class Sequencetor {
    private static Sequencetor myInstance;
	private static int counter=0;
	private Sequencetor(){
		
	}
	//-------------------
	public static Sequencetor getSequencetorIntance(){
		if(myInstance == null){
			myInstance = new Sequencetor();
		}
		return myInstance;
	}
	public static int getCounter(){
		if(counter == 1000){
			counter=0;
		}
		
		return ++counter;
	}
}
