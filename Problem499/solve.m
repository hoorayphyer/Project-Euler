x0 = 0.06*ones(14,1);
options = optimoptions('fsolve','Display','iter','TolFun',1e-60,'TolX',1e-60);
[x1,fval14] = fsolve(@myfun14,x0,options);
[x2,fval14] = fsolve(@myfun14,x1,options);
[x3,fval14] = fsolve(@myfun14,x2,options);
[x4,fval14] = fsolve(@myfun14,x3,options);
[x5,fval14] = fsolve(@myfun14,x4,options);
[x,fval14] = fsolve(@myfun14,x5,options);

for t=1:30
    [x,fval14] = fsolve(@myfun14,x,options);
end

A = [0,0,0,0,0,0,0,0,0,0,0,0,0,x(1);
     1,0,0,0,0,0,0,0,0,0,0,0,0,x(2);
     0,1,0,0,0,0,0,0,0,0,0,0,0,x(3);
     0,0,1,0,0,0,0,0,0,0,0,0,0,x(4);
     0,0,0,1,0,0,0,0,0,0,0,0,0,x(5);
     0,0,0,0,1,0,0,0,0,0,0,0,0,x(6);
     0,0,0,0,0,1,0,0,0,0,0,0,0,x(7);
     0,0,0,0,0,0,1,0,0,0,0,0,0,x(8);
     0,0,0,0,0,0,0,1,0,0,0,0,0,x(9);
     0,0,0,0,0,0,0,0,1,0,0,0,0,x(10);
     0,0,0,0,0,0,0,0,0,1,0,0,0,x(11);
     0,0,0,0,0,0,0,0,0,0,1,0,0,x(12);
     0,0,0,0,0,0,0,0,0,0,0,1,0,x(13);
     0,0,0,0,0,0,0,0,0,0,0,0,1,x(14)];

 xfinal = (A)^((10^9)-15)*x;

 
y0 = 0.1*ones(5,1);
options = optimoptions('fsolve','Display','iter','TolFun',1e-30,'TolX',1e-60);
[y,fval5] = fsolve(@myfun5,y0,options);
 
 B = [0,0,0,0,y(1);
      1,0,0,0,y(2);
      0,1,0,0,y(3);
      0,0,1,0,y(4);
      0,0,0,1,y(5);];
 yfinal = (B)^(10000-6)*y;
 
 z0 = 0.5;
 options = optimoptions('fsolve','Display','iter','TolFun',1e-30,'TolX',1e-60);
[z,fval1] = fsolve(@myfun1,z0,options);