package balloons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class balloons 
{

    public static void main(String[] args) 
    {
        Scanner in = new Scanner(System.in);
        
        int n = in.nextInt();
        
        int dist=0;
        
        int aBalloons = in.nextInt();
        int bBalloons = in.nextInt();
        
        while(!(n==0 && aBalloons==0 && bBalloons==0))
        {
            ArrayList<Room> a = new ArrayList<>();
            
            for(int i=0; i<n; i++)
                a.add(new Room(in.nextInt(), in.nextInt(), in.nextInt()));
            
            Collections.sort(a);
            
            for(Room r:a)
            {
                if(aBalloons==0 && bBalloons!=0)
                {
                    dist += r.numBalloons * r.distB;
                    bBalloons-=r.numBalloons;
                    r.numBalloons = 0;
                }
                else if(bBalloons==0 && aBalloons!=0)
                {
                    dist += r.numBalloons * r.distA;
                    aBalloons-=r.numBalloons;
                    r.numBalloons = 0;
                }
                else
                {
                    if(r.distA<=r.distB)
                    {
                        if(r.numBalloons>aBalloons)
                        {
                            dist+=aBalloons * r.distA;
                            r.numBalloons = r.numBalloons-aBalloons;

                            aBalloons=0;
                            dist+=r.numBalloons * r.distB;
                            bBalloons=bBalloons-r.numBalloons;
                            r.numBalloons=0;

                        }
                        else if(r.numBalloons==aBalloons)
                        {
                            dist+= aBalloons * r.distA;
                            aBalloons=0;
                            r.numBalloons=0;
                        }
                        else
                        {
                            dist+=r.numBalloons*r.distA;
                            aBalloons = aBalloons-r.numBalloons;
                            r.numBalloons = 0;
                        }
                    }
                    else
                    {
                        if(r.numBalloons>=bBalloons)
                        {
                            dist+=bBalloons * r.distB;
                            r.numBalloons = r.numBalloons-bBalloons;
                            bBalloons=0;
                            dist+=r.numBalloons * r.distA;
                            aBalloons=aBalloons-r.numBalloons;
                            r.numBalloons=0;
                        }
                        else if(r.numBalloons==bBalloons)
                        {
                            dist+= bBalloons * r.distB;
                            bBalloons=0;
                            r.numBalloons=0;
                        }
                        else
                        {
                            dist+=r.numBalloons*r.distB;
                            bBalloons = bBalloons-r.numBalloons;
                            r.numBalloons = 0;
                        }
                    }
                }
            }

            System.out.println(dist);
            
            n = in.nextInt();
        
            dist=0;

            aBalloons = in.nextInt();
            bBalloons = in.nextInt();
        }
        
        
    }
    
    
    
}


class Room implements Comparable<Room>
{
    public int distA, distB;
    public int numBalloons;
    
    Room(int numBalloons, int distA, int distB)
    {
        this.numBalloons = numBalloons;
        this.distA = distA;
        this.distB = distB;
    }
    
    @Override
    public String toString()
    {
        return numBalloons+", "+distA+", "+distB;
    }
    
    @Override
    public int compareTo(Room t) {
        
        if(java.lang.Math.abs(this.distA-this.distB)>java.lang.Math.abs(t.distA-t.distB))
            return -1;
        else if(java.lang.Math.abs(this.distA-this.distB)<java.lang.Math.abs(t.distA-t.distB))
            return 1;
        
        return 0;
    }
    
}