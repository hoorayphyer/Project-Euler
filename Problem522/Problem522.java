import java.util.ArrayList;
import java.lang.Math;

public class Problem522 {

    int N;
    ArrayList<Integer> prime_List;
    int[] unit_Table;

    int M = 135707531;
    int[] digits_2 = new int[28];
    int[] res = new int[28];
    
    public Problem522(int N) {
	this.N = N;
	prime_List = new ArrayList<Integer>();
	unit_Table = new int[N];
    }

    public void create_Prime_List() {

	boolean prime;
	prime_List.add(2);
	for (int i=3; i<N; i++) {
	    prime = true;
	    for (int j=0; j<prime_List.size(); j++) {
		if (prime_List.get(j) > Math.sqrt(i)) { break; }
		if (i % prime_List.get(j) == 0) {
		    prime = false;
		    break;
		}
	    }
	    if (prime) {
		prime_List.add(i);
		//System.out.println(i);
	    }
	}
	System.out.println("Number of primes < " + N + ": " + prime_List.size());
    }

    public void create_Digits_2() {
	int temp = M-2;
	int i = 0;
	while (temp>0) {
	    digits_2[i] = temp % 2;
	    temp = temp/2;
	    System.out.println(i + ": " + digits_2[i]);
	    i++;
	}
    }
    
  
    /* n to the power of n+1 */
    public int calculate_pow(int n) {
	int[] digits = new int[(int)(Math.log(n+1)/Math.log(2))+1];
	int[] powers = new int[digits.length];
	int result = 1;
	powers[0] = n;
	int i = 0;
	int temp = n + 1;
	while (temp>0) {
	    digits[i] = temp % 2;
	    temp = temp/2;
	    //System.out.println(i + ": " + digits[i]);
	    i++;
	}
	result = digits[0]==0 ? result: (int)((long)result * (long)powers[0] % M);
	for (int j=1; j<powers.length; j++) {
	    powers[j] = (int)((long)powers[j-1] * (long)powers[j-1] % M);
	    result = digits[j]==0 ? result : (int)((long)result * (long)powers[j] % M);
	    //System.out.println(powers[j]);
	    //System.out.println("::" + result);
	}
	return result;
    }
    

    public int calculate_inverse(int p) {
	res[0] = p;
	int inverse_value = 1;
	inverse_value = digits_2[0]==0 ? inverse_value : inverse_value*res[0];
	for (int i=1; i<res.length; i++) {
	    res[i] = (int) ((long)res[i-1] * (long)res[i-1] % M);
	    inverse_value = digits_2[i]==0 ? inverse_value : (int)((long)inverse_value * (long)res[i] % M);
	}
	return inverse_value;
    }

    public int sum_up() {
	int record = N;
	int total = 0;
	int temp1, temp2;
	for (int i=2; i<=N-2; i++) {
	    record = (int)((long)record * (long)(N-i+1) % M );
	    temp1 = calculate_inverse(i);
	    temp2 = calculate_pow(N-i-1);
	    total = (int)(((long)total + ((long)record * (long)temp1 % M) * (long)temp2) % M);
	    if (i % 100000 == 0) {System.out.println( i + " Done"); }
	}

	total = (int)(((long)total + ((long)N * (long)(N-1) % M) * (long)calculate_pow(N-2)) % M);
	
	return total;
    }
    
    public static void main(String[] args) {

	int N = 12344321;
	//	N = 1000000;
	Problem522 solution = new Problem522(N);
	solution.create_Digits_2();
	System.out.println(solution.calculate_inverse(10));
	System.out.println(solution.calculate_pow(10));
	System.out.println(solution.sum_up());
		
    }

    
}
