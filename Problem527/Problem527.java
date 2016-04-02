import java.lang.Math;

public class Problem527 {

    Problem527(long N) {
	this.N = N;
    }
    private long N;

    public double binarySearch () {
	System.out.println("Binary:");
	
	long binaryResult = 0;
	int index = (int) (Math.log(N+1)/Math.log(2));
	
	for(int i=1; i<=index; i++) {
	    binaryResult += i * (long)Math.pow(2,i-1);
	}
	binaryResult += (N+1 - (long)Math.pow(2,index)) * (index+1);

	double binaryAverage = (double) binaryResult / N;
	
	System.out.println(binaryAverage);
	return binaryAverage;
    }

    public double randomSearch () {
	System.out.println("Random:");
	double previousSum = 0.0;
	double current = 1.0;

	for(long i=2; i<=N; i++) {
	    previousSum += current;
	    current = (previousSum * 2) / i + i;
	    if (i%100000000==0) {
		System.out.println(i);
	    }
	}

	double randomAverage = current / N;

	System.out.println(randomAverage);
	return randomAverage;
    }

    
    public static void main(String[] args) {
	long N = 10000000000L;
	Problem527 solution  = new Problem527(N);
	System.out.println(solution.randomSearch() - solution.binarySearch());
    }
    
}
