N = 600;
a = zeros(N,1);
M = 50;
V = zeros(M,3);
W = zeros(M,3);
a(1) = 0;
a(2) = 1;
a(3) = 1;
S = 0;
K = 10^7;
for i=4:N
    a(i) = mod(a(i-1) + a(i-2) + a(i-3),K);
end


for j=1:M
    V(j,1) = mod(a(12*j-11),K) - mod(a(12*j-10),K);
    V(j,2) = mod(a(12*j-9),K) + mod(a(12*j-8),K);
    V(j,3) = mod(a(12*j-7),K) * mod(a(12*j-6),K);
    W(j,1) = mod(a(12*j-5),K) - mod(a(12*j-4),K);
    W(j,2) = mod(a(12*j-3),K) + mod(a(12*j-2),K);
    W(j,3) = mod(a(12*j-1),K) * mod(a(12*j),K);
    
    r(1) = -W(j,1)/V(j,1);
    r(2) = -W(j,2)/V(j,2);
    r(3) = -W(j,3)/V(j,3);
    
    
    
    if(r(1)<r(2) && r(2)<r(3))
        order = [3,2,1];
    else if(r(1)<r(3) && r(3)<r(2))
            order = [2,3,1];
        else if(r(2)<r(1) && r(1)<r(3))
                order = [3,1,2];
            else if(r(2)<r(3) && r(3)<r(1))
                    order = [1,3,2];
                else if(r(3)<r(2) && r(2)<r(1))
                        order = [1,2,3];
                    else if(r(3)<r(1) && r(1)<r(2))
                            order= [2,1,3];
                        end
                    end
                end
            end
        end
    end

    if(abs(V(j,order(1))) > (abs(V(j,order(2)))+abs(V(j,order(3)))))
        index = order(1);
    else if(abs(V(j,order(3))) > (abs(V(j,order(2)))+abs(V(j,order(1)))))
            index = order(3);
        else
            index = order(2);
        end
    end
    slope = -W(j,index)/V(j,index);
   
    min1 = sum(abs(V(j,:)));
    min2 = sum(abs(W(j,:)));
    if(min1<min2)
        min = min1;
    else
        min = min2;
    end
    for l=1:ceil(min/( abs(slope*V(j,1)+W(j,1)) + abs(slope*V(j,2)+W(j,2)) + abs(slope*V(j,3)+W(j,3)) ))
        distance1 = abs(floor(slope*l)*V(j,1)+l*W(j,1)) + abs(floor(slope*l)*V(j,2)+l*W(j,2)) + abs(floor(slope*l)*V(j,3)+l*W(j,3));
        if(distance1<min)
            min = distance1;
        end
        distance2 = abs(ceil(slope*l)*V(j,1)+l*W(j,1)) + abs(ceil(slope*l)*V(j,2)+l*W(j,2)) + abs(ceil(slope*l)*V(j,3)+l*W(j,3));
        if(distance2<min)
            min = distance2;
        end
    end
   
    
    %******************************************%%
    rW(1) = -V(j,1)/W(j,1);
    rW(2) = -V(j,2)/W(j,2);
    rW(3) = -V(j,3)/W(j,3);
    
    
    
    if(rW(1)<rW(2) && rW(2)<rW(3))
        orderW = [3,2,1];
    else if(rW(1)<rW(3) && rW(3)<rW(2))
            orderW = [2,3,1];
        else if(rW(2)<rW(1) && rW(1)<rW(3))
                orderW = [3,1,2];
            else if(rW(2)<rW(3) && rW(3)<rW(1))
                    orderW = [1,3,2];
                else if(rW(3)<rW(2) && rW(2)<rW(1))
                        orderW = [1,2,3];
                    else if(rW(3)<rW(1) && rW(1)<rW(2))
                            orderW= [2,1,3];
                        end
                    end
                end
            end
        end
    end
    
    
    
    if(abs(W(j,orderW(1))) > (abs(W(j,orderW(2)))+abs(W(j,orderW(3)))))
        indexW = orderW(1);
    else if(abs(W(j,orderW(3))) > (abs(W(j,orderW(2)))+abs(W(j,orderW(1)))))
            indexW = orderW(3);
        else
        indexW = orderW(2);
        end
    end
    slope = -V(j,indexW)/W(j,indexW);
   
    if(indexW~=index)
        j
    end
    for k=1:ceil(min/( abs(V(j,1)+slope*W(j,1)) + abs(V(j,2)+slope*W(j,2)) + abs(V(j,3)+slope*W(j,3)) ))
        distance1 = abs(k*V(j,1)+floor(slope*k)*W(j,1)) + abs(k*V(j,2)+floor(slope*k)*W(j,2)) + abs(k*V(j,3)+floor(slope*k)*W(j,3));
        if(distance1<min)
            min = distance1;
        end
        distance2 = abs(k*V(j,1)+ceil(slope*k)*W(j,1)) + abs(k*V(j,2)+ceil(slope*k)*W(j,2)) + abs(k*V(j,3)+ceil(slope*k)*W(j,3));
        if(distance2<min)
            min = distance2;
        end
    end
    %%******************************************%%
        
    S = S + min;
end








