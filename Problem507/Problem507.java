import java.lang.Math.*;

public class Problem507 {

    int N;
    int Mol;
    long[] a;
    long[] V;
    long[] W;
    int[] order;
    double[] r;
    long S;
    int index;
    double slope;
    long minK;
    long minL;
    long min;
    long distance1;
    long distance2;
    long distance3;
    long upperBound;
    
    public Problem507(int N) {
	this.N = N;
	Mol = (int)Math.pow(10,7);
	a = new long[12];
	V = new long[3];
	W = new long[3];
	order = new int[3];
	r = new double[3];
	S = (long)0L;

	a[0] = 0L;
	a[1] = 1L;
	a[2] = 1L;
	for(int i=3;i<12;i++) {
	    a[i] = (a[i-1]+a[i-2]+a[i-3])%Mol;
	}

	for(int i=0;i<N;i++) {

	    
	    if(i>=1) {
		for(int t=0;t<12;t++)
		    a[t] = (a[(t+11)%12] + a[(t+10)%12] + a[(t+9)%12])%Mol;
	    }
	    

	    
	    V[0] = a[0] - a[1];
	    V[1] = a[2] + a[3];
	    V[2] = a[4] * a[5];
	    W[0] = a[6] - a[7];
	    W[1] = a[8] + a[9];
	    W[2] = a[10] * a[11];

	    //System.out.println(V[i][0] + "  " + V[i][1] + "  " + V[i][2] );
	    //System.out.println(W[i][0] + "  " + W[i][1] + "  " + W[i][2] );
	    
	    for(int j=0;j<3;j++)
		r[j] = -W[j]/(double)V[j];


	    //*********Put r in order************

	    order[0] = 0;
	    order[1] = 1;
	    order[2] = 2;

	    int temp;
	    if(r[order[0]]<r[order[1]]) {
		temp = order[0];
		order[0] = order[1];
		order[1] = temp;
	    }
	    if(r[order[0]]<r[order[2]]) {
		temp = order[0];
		order[0] = order[2];
		order[2] = temp;
	    }
	    if(r[order[1]]<r[order[2]]) {
		temp = order[1];
		order[1] = order[2];
		order[2] = temp;
	    }
	    //************************************
	    
	    if(Math.abs(V[order[0]])>Math.abs(V[order[1]])+Math.abs(V[order[2]]))
		index = order[0];
	    else if(Math.abs(V[order[2]])>Math.abs(V[order[1]])+Math.abs(V[order[0]]))
		index = order[2];
	    else
		index = order[1];

	    slope = r[index];

	    minK = Math.abs(V[0]) + Math.abs(V[1]) + Math.abs(V[2]);
	    minL = Math.abs(W[0]) + Math.abs(W[1]) + Math.abs(W[2]);
	    if(minK < minL)
		min = minK;
	    else
		min = minL;

	    upperBound = (long)( min / ( Math.abs(slope*V[0] + W[0]) + Math.abs(slope*V[1] + W[1]) + Math.abs(slope*V[2] + W[2]) ) );
	    //upperBound = 0;
	    
	    for(long l=1;l<=upperBound+1;l++) {
		
		distance1 =  Math.abs(((int)(slope*l))*V[0] + l*W[0]) +
		    Math.abs(((int)(slope*l))*V[1] + l*W[1]) + Math.abs(((int)(slope*l))*V[2] + l*W[2]);

		//System.out.println("***" + Math.abs(((int)(slope*l))*V[i][0] + l*W[i][0]));
	    
		if(distance1<min) {
		    min = distance1;
		    upperBound = (long)( min / ( Math.abs(slope*V[0] + W[0]) + Math.abs(slope*V[1] + W[1]) + Math.abs(slope*V[2] + W[2]) ) );
		}

		distance2 =  Math.abs(((int)(slope*l+1))*V[0] + l*W[0]) +
		    Math.abs(((int)(slope*l+1))*V[1] + l*W[1]) + Math.abs(((int)(slope*l+1))*V[2] + l*W[2]);
	    
		if(distance2<min) { 
		    min = distance2;
		    upperBound = (long)( min / ( Math.abs(slope*V[0] + W[0]) + Math.abs(slope*V[1] + W[1]) + Math.abs(slope*V[2] + W[2]) ) );
		}
		
		distance3 =  Math.abs(((int)(slope*l-1))*V[0] + l*W[0]) +
		    Math.abs(((int)(slope*l-1))*V[1] + l*W[1]) + Math.abs(((int)(slope*l-1))*V[2] + l*W[2]);
	    
		if(distance3<min) {
		    min = distance3;
		    upperBound = (long)( min / ( Math.abs(slope*V[0] + W[0]) + Math.abs(slope*V[1] + W[1]) + Math.abs(slope*V[2] + W[2]) ) );
		}
		
	    }

	    S = S + min;

	    if(i%100000==0)
		System.out.println(i + " finished");
	    
	}
	System.out.println("sum = " + S);
	    
    }

    public static void main(String[] args) {
	Problem507 solution = new Problem507(20000000);
	
    }
    
}
