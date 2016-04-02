function F1 = myfun1(x)
b = 1/2;
A = x(1);
     
B = 1;
for n=2:10
    B = B + A^(2^n-2)/2^(n-1);
end
F1 = x - b - 1/4 * B * x; 