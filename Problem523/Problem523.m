N = 30;
%% N,1,2,3,4,...,N-1
W = zeros(1,N);
V = zeros(1,N);
W(1) = 0; V(1) = 0;
W(2) = 1; V(2) = 1;
for i = 3:N
    W(i) = 2 * W(i-1) + 1;
    V(i) = V(i-1) + W(i);
end

%% All
S = zeros(1,N);
S(1) = 0;
S(2) = 1;
for i = 3:N
   S(i) = S(i-1)*i + factorial(i-1) * ( V(i-1) + i - 1 ); 
end

S(N)/factorial(N)