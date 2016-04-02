import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
public class Problem500 {

    public ArrayList<Long> prime;
    public ArrayList<Integer> order;
    public PriorityQueue<Node> pq;
    public long target;
    public long base;
    public long maxP;
    public Node node1;
    public Node node2;
    
    public Problem500(long t,long b) {
	target = t;
	base = b;
	prime = new ArrayList<Long>();
	prime.add((long)2);
	order = new ArrayList<Integer>();
	order.add(0);
	node1 = new Node(0,2);
	pq = new PriorityQueue<Node>();
	pq.add(node1);
	maxP = 2;
    }

    public void operate() {
	int temp;
	for(long Nstep=0;Nstep<target;Nstep++) {
	    node2 = pq.peek();
	    //System.out.println(node2.value + " get");
	    if(node2.value>maxP) {
		findMorePrimes(node2.value);
	    }
	    node2 = pq.poll();
	    temp = order.get(node2.index);
	    order.set(node2.index,temp+1);
	    node2.value = node2.value*node2.value;
	    pq.add(node2);
	    if(Nstep%100==0) {
		System.out.println(Nstep + " steps finished");
	    }
	}
    }
    
    public void findMorePrimes(long max) {
	for(long j=maxP+1;j<=max;j++) {
	    if(isPrime(j)) {
		prime.add(j);
		//System.out.println(j+" Prime found");
		maxP = j;
		order.add(0);
		node1 = new Node(prime.size()-1,j);
		pq.add(node1);
	    }
	}
    }
    private boolean isPrime(Long q) {
	for(int i=0;i<prime.size();i++) {
	    if(q%prime.get(i)==0){
		return false;
	    }
	}
	return true;
    }
    
    class Node implements Comparable<Node>{
	public int index;
	public long p;
	public long value;
	Node(int index,long p) {
	    this.index = index;
	    p = p;
	    value = p;
	}
	public int compareTo(Node n) {
	    if(this.value>n.value)
		return 1;
	    else if(this.value==n.value)
		return 0;
	    else
		return -1;
	}
    }
    public void printResult() {
	int i = 0;
	while(i<order.size() && order.get(i)!=0) {
	    System.out.println(prime.get(i) + ": " + order.get(i));
	    i++;
	}
    }
    public void printFile(PrintWriter pw) {
	int i = 0;
	while(i<order.size() && order.get(i)!=0) {
	    pw.println(prime.get(i) + ": " + order.get(i));
	    i++;
	}

    }
    
    public static void main(String[] args) throws IOException {
	Problem500 pr = new Problem500(500500,500500507);
	pr.operate();
	pr.printResult();
	File file = new File("out.txt");
	PrintWriter pw = new PrintWriter(new FileOutputStream(file));
	pr.printFile(pw);
	pw.close();
    }
}
