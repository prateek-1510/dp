import java.util.*;

public class MinimumCost {

    /*
    *Minimise the cost to reach from n to m, using even divisors of n only
    * cost= n/divisor
    * new (n)=old(n)+cost
    *  */
    private static int minCost=Integer.MAX_VALUE;
    private static Map<Integer, List<Integer>> map=new HashMap<>();
    private static Map<Integer, Integer> memo=new HashMap<>();

    public static void main(String[] args) {

        int n=6;
        int m=100;

        minCost=Integer.MAX_VALUE;

        int res=new MinimumCost().solve(6,100000,0);
        System.out.println(minCost);

    }

    public int solve(int n, int m, int cost)
    {

        if(n==m)
        {
            minCost=Math.min(minCost, cost);
            return cost;

        }

        if(memo.containsKey(n))
        {

            //System.out.println(cost+memo.get(n));
            minCost=Math.min(minCost, cost+memo.get(n));
            return cost+memo.get(n);
        }

        if(n>m)
            return Integer.MAX_VALUE;

        List<Integer> ls=null;
        if(!map.containsKey(n))
        {
            ls=findEvenDivisors(n);
            map.put(n,ls);
        }

        ls=map.get(n);
        int c=0;

        for(int div : ls)
        {
            c=solve(n+div,m, cost+n/div);
            //System.out.println(c);

            if(!memo.containsKey(n) && c!=Integer.MAX_VALUE && c!=0)
            {
                memo.put(n,c-cost);

            }
            else if(c!=Integer.MAX_VALUE && c!=0)
            {
                int val=memo.get(n);
                memo.put(n,Math.min(c-cost,val));
            }


        }




        return c;
    }

    public List<Integer> findEvenDivisors(int n)
    {
        int num=n;
        n=n/2;

        List<Integer> ls=new ArrayList<>();
        while(n>1)
        {
            if(n%2==0)
            {
                if(num%n==0)
                {
                    ls.add(n);
                }

                n=n-2;
            }
            else
            {
                n=n-1;

            }
        }


        return ls;

    }

}
