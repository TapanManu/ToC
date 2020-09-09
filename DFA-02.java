import java.util.HashMap;

class State{
	private int id;
	private boolean fin;
	public HashMap<Integer,Integer> transition = new HashMap<>();
	public static State currentState;
	public static State start;
	State(int id,boolean final_state){
		this.id=id;
		this.fin = final_state;		//whether accepting state or not
	}
	public int getID(){
		return id;
	}
	public boolean isFinal(){
		return this.fin;
	}
	public void next(int q,int trans){
		this.transition.put(trans,q);
	}
	public static State obj(int id){
		for(State s:DFA.q){
			if(s.getID()==id)
				return s;
		}
		return null;
	}
	public static void current(State s1,int trans){
		int idvalue = s1.transition.get(trans);
		currentState = obj(idvalue);
		if(currentState==null){
			System.out.println("invalid transition");
		}
	}
	

}


class DFA{
	public static State q[]= new State[4];
	public static void main(String[] args) {
		/*language that end with 101 */
		String input = "00011010111110001010100101";
		
		q[0] = new State(0,false);
		q[1] = new State(1,false);
		q[2] = new State(2,false);
		q[3] = new State(3,true);
		//Defining DFA
		q[0].next(1,1);
		q[0].next(0,0);
		q[1].next(2,0);
		q[1].next(1,1);
		q[2].next(3,1);
		q[2].next(0,0);
		q[3].next(2,0);
		q[3].next(1,1);

		State.start=q[0];  //starting state
		State.currentState=State.start;
		for(Character c:input.toCharArray()){
			State.current(State.currentState,Integer.parseInt(c.toString()));
		}
		if(State.currentState.isFinal()){
			System.out.println("the string is accepted");
		}
		else
			System.out.println("the string is not accepted");
	}
}