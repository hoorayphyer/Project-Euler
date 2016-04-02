import java.util.ArrayList;
import java.lang.Math;
public class Modulo {
    ArrayList<Long> prime;
    ArrayList<Integer> order;
    long output;
    long base;
    public Modulo(In in, long base) {
	
	prime = new ArrayList<Long>();
	order = new ArrayList<Integer>();
	for(int i=0;i<500084;i++) {
	    prime.add(in.readLong());
	    order.add(in.readInt());
	}
	output = 1;
	this.base = base;
    }
    public void multiply() {
	for(int i=0;i<prime.size();i++) {
	    for(int j=0;j<Math.pow(2,order.get(i))-1;j++) {
		output = output*prime.get(i)%base;
	    }
	}
    }
    public static void main(String[] args) {
	In in = new In(args[0]);
	long base = 500500507;
	Modulo modulo = new Modulo(in,base);
	modulo.multiply();
	System.out.println(modulo.output);
    }
}
