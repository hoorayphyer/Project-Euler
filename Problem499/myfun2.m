function F2 = myfun2(x)
b = [1/2;1/4];
A = [0,x(1);1,x(2)];
B = A^(4-3);
for n=2:10
    B = B + A^(4^n-3)/2^(n-1);
end
F2 = x - b - 1/8 * B * x; 