
public class Problem427 {
    
    int M = 1000000009;
    int N = 7500000;
    int T = 3000;
    int[][] table;
    int[] residue_N;
    int[] pre_Counts;
    
    public Problem427() {
	residue_N = new int[N];
	residue_N[0] = 1;
	for (int i=1; i<N; i++) {
	    residue_N[i] = (int)((long)residue_N[i-1] * (long)N % M);
	}
	pre_Counts = new int[N+1];
	// ****** Initialize Table ****** // 
	//table = new int[N+1][T+1];
	table = new int[N+1][2];
	for(int i=0; i<N+1; i++) {
	    for(int j=0; j<2; j++) {
		table[i][j] = 0;
	    }
	}
	// ****** Initialize Table ****** // 
    }

    public void construct_table() {

	for(int k=2; T*(k-2)<=N; k++) {
	    pre_Counts[0] = 1;
	    //table[T*(k-2)][T] += 1;
	    table[T*(k-2)][T%2] += 1;
	    for(int n=1; T*(k-2)+n<=N && n<k; n++) {
		pre_Counts[n] = residue_N[n];
		//table[T*(k-2)+n][T] = (table[T*(k-2)+n][T] + pre_Counts[n]) % M;
		table[T*(k-2)+n][T%2] = (table[T*(k-2)+n][T%2] + pre_Counts[n]) % M;
	    }
	    if (T*(k-2)+k<=N) {
		pre_Counts[k] = (int)((((long)pre_Counts[k-1] * (long)N) - (long)N ) % M);
		//table[T*(k-2)+k][T] = (table[T*(k-2)+k][T] + pre_Counts[k]) % M;
		table[T*(k-2)+k][T%2] = (table[T*(k-2)+k][T%2] + pre_Counts[k]) % M;
	    }
	    for (int n=k+1; T*(k-2)+n<=N; n++ ) {
		pre_Counts[n] = (int)(((long)pre_Counts[n-1] * (long)N - (long)pre_Counts[n-k] * (long)(N-1)) % M );
		//table[T*(k-2)+n][T] = (table[T*(k-2)+n][T] + pre_Counts[n]) % M;
		table[T*(k-2)+n][T%2] = (table[T*(k-2)+n][T%2] + pre_Counts[n]) % M;
	    }
	    if(k%10==0) {
		System.out.println("k = " + k);
	    }
	}
	for(int t=T-1; t>1; t--) {
	    //table[0][t] = 1;
	    //table[1][t] = N;
	    table[0][t%2] = 1;
	    table[1][t%2] = N;
	    for(int n=2; n<=N; n++) {
		//table[n][t] = (int)(((long)table[n-1][t] * (long)N - (long)table[n-2][t+1] * (long)(N-1)) % M );
		table[n][t%2] = (int)(((long)table[n-1][t%2] * (long)N - (long)table[n-2][(t+1)%2] * (long)(N-1)) % M );
		if((n-2)%(t+1) == 0) table[n][t%2] --;
		if(n%t == 0) table[n][t%2] ++;
	    }
	    if(t%10==0) {
		System.out.println("t = " + t);
	    }
	}
	table[0][1] = 1;
	table[1][1] = N+1;
	for(int n=2; n<=N; n++) {
	    table[n][1] = (int)(((long)table[n-1][1] * (long)N - (long)table[n-2][2%2] * (long)(N-1)) % M);
	    if((n-2)%(1+1) == 0) table[n][1] --;
	    if(n%1 == 0) table[n][1] ++;
	}
	
	table[0][0] = N-1;
	table[1][0] = (int)((long)N*(long)(N-1) % M);
	for(int n=2; n<=N; n++) {
	    table[n][0] = (int)(((long)table[n-1][0] * (long)N - (long)table[n-2][1] * (long)(N-1) - 1) % M);
	}

	
	int final_Value = (int)((((long)residue_N[N-1] * (long)N % M * (long)N % M - (long)table[N][0]) % M + M) % M);
	
	System.out.println("final_Value: " + final_Value);
	  /*
	  System.out.println("Print Table:");
	  for(int i=0; i<N+1; i++) {
	  for(int j=0; j<T+1; j++) {
	  System.out.print(table[i][j] + "  ");
	  }
	  System.out.println();
	  }
	*/
    }
    
    public int calculate(int n) {
	assert n >= 2;
	int value = 0;
	pre_Counts[0] = 1;
	value = (int)((long)pre_Counts[0] * (long)N % M * (long)residue_N[N-n] % M);
	for(int i=1; i<n && i<=N-n; i++) {
	    pre_Counts[i] = residue_N[i];
	    value = (int)(((long)value + (long)pre_Counts[i] * (long)(N-1) % M * (long)residue_N[N-n-i]) % M);
	    value = value % M;
	}

	if (n<=N-n) {
	    pre_Counts[n] = (int)(((long)pre_Counts[n-1] * (long)N - N) % M);
	    value = (int)(((long)value + (long)pre_Counts[n] * (long)(N-1) % M * (long)residue_N[N-n-n]) % M);
	    value = value % M;
	}
	
	for(int i=n+1; i<=N-n; i++) {
	    pre_Counts[i] = (int)(((long)pre_Counts[i-1] * (long)N - (long)pre_Counts[i-n] * (long)(N-1)) % M);
	    value = (int)(((long)value + (long)pre_Counts[i] * (long)(N-1) % M * (long)residue_N[N-n-i]) % M);
	    value = value % M;
	}
	return value;
    }

    public int calculate_N(int n) {
	pre_Counts[0] = 1;
	for(int i=1; i<n; i++) {
	    pre_Counts[i] = residue_N[i];
	}
	pre_Counts[n] = (int)(((long)pre_Counts[n-1] * (long)N - N) % M);
	for(int i=n+1; i<=N; i++) {
	    pre_Counts[i] = (int)(((long)pre_Counts[i-1] * (long)N - (long)pre_Counts[i-n] * (long)(N-1)) % M);
	}
	/*
	System.out.print(n + ": ");
	for(int i=0; i<=N; i++) {
	    System.out.print(pre_Counts[i] + " ");
	}
	System.out.println();
	*/
	return pre_Counts[N];
  
    }

    public void subtract() {
	int total = (int)((long)N * (long)N % M * (long)residue_N[N-1] % M);
	for (int t=2; t<=N; t++) {
	    total = (int)(((long)total-(long)calculate_N(t) + (long)M) % M);
	    if( t % 10 == 0) {
		System.out.println(t);
	    }
	}
	total = (total + M) % M;
	System.out.println("Substrate total: " + total);
    }
    
    public void addup() {
	int total = (int)((long)N * (long)residue_N[N-1] % M);
	for (int t=2; t<=N; t++) {
	    total = (int)(((long)total + (long)calculate(t)) % M);
	    if( t % 10 == 0) {
		System.out.println(t);
	    }
	}
	System.out.println("Addup total: " + total);
    }

    public static void main(String[] args) {
	Problem427 solution = new Problem427();
	//solution.addup();
	//solution.subtract();
	solution.construct_table();
    }
}
