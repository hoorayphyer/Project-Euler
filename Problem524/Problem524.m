T1 = 3^12; %% 531441
M20 = 2^20; %% 1048576
% 2,(1xxxx,xxxxx,xxxxx,xxxxx)
T2 = (T1-1)/2; %% 265720
M19 = 2^19; %% 524288

% 2,1(xxxx,xxxxx,xxxxx,xxxxx)
T3 = T2/2; %% 132860
M18 = 2^18; %% 262144

% 2,1,3(xxx,xxxxx,xxxxx,xxxxx)
T4 = T3/2;
M17 = 2^17;

% 2,1,3,4(xx,xxxxx,xxxxx,xxxxx)
T5 = T4/2; %% 33215
M16 = 2^16; %% 65536 

% 2,1,3,4,11,5,6,7,8,9,10,(xxxxx,xxxxx)
T12 = (T5-63)/2^7; % 259
M9 = 2^9; % 512

% 2,1,3,4,11,5,6,7,8,9,10,14,12,13(xx,xxxxx)
T15 = (T12-3)/2^3; % 32
T6 = 2^6; %64

% 2,1,3,4,11,5,6,7,8,9,10,14,12,13,15,16,17,18,19,(xx)
T20 = (T15)/2^5; % 1

% 2,1,3,4,11,5,6,7,8,9,10,14,12,13,15,16,17,18,19,21,20
x = [2,1,3,4,11,5,6,7,8,9,10,14,12,13,15,16,17,18,19,21,20];

x = [2,1,3,4,6,12,7,8,9,10,11,15,13,14,5,16,17,18,19,21,20]; 

x = [2,1,3,4,6,8,10,14,11,12,13,17,15,16,9,7,5,18,19,21,20]; 

x = [2,1,3,4,6,8,10,12,15,13,14,18,16,17,11,9,7,5,19,21,20]; 

x = [2,1,3,4,6,8,10,12,14,16,15,19,17,18,13,11,9,7,5,21,20]; 

x = [2,1,3,4,6,8,10,12,14,16,15,18,21,19,17,13,11,9,7,5,20]; 

N = 21;
b = zeros(1,N);
total = int64(1);
min = 1;
for i=1:N
    b(x(i)) = 1;
    count = 0;
    for j = min:x(i)
       if(b(j)==0) 
           count = count + 1; 
       end
    end
    total = int64(total) + count * int64(factorial(N-i));
    if(i<N)
        while(b(min) == 1) 
            min = min + 1;
        end
    end
    
end
int64(total)

max = 0;
k_value = 0;
count_small = 0;
for i=1:N
    count_small = 0;
    if(x(i)>max)
        max = x(i);
    else
        for j=1:i-1
            if(x(j)<x(i))
                count_small = count_small + 1;
            end
        end
        k_value = k_value + 2^(count_small);
    end
end
k_value
















 

