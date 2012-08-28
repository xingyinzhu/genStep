/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author xyzhu
 */

/*
//add
178|OLP|1000000178|12708|799888||R||9=13935=D11=1000000178309=601398128C00440N10901=I44=5.20038=00010054=177=O453=3448=B559315376452=5448=12708452=1448=00J95452=4001||
*/

/*
//cancel
179|OLP|1000000179|12708|799888||R||9=15335=F11=100000017941=1000000178309=600252aaaaabbbbb10901=I44=5.20038=50000054=177=C453=3448=X000000001452=5448=12708452=1448=00J95452=4001||
*/


public class Basic {
    
    static char SOH = '\u0001';
    static int MAXN = 10000000;
    //static int MAXN =   10000;
    static Integer SEQNUM = 0000000173;
    static char SEP = '\t';
    static int GROUP = 5;
    
    
    static String[] PBU = {"20022","20023","20025","20026","20027"};
    static String[] INVACC = {"X900000150","X900000170","X900000190","X900000210","X900000230"};
    static String BRANCHID = "12345";
    static String[] SECURITY = {"510050127C00160N","510050127P00160N","601398127C00420U","601398127P00420U","601398127P00400U"};
    static double[] securityUpLimit = {0.334,0.221,0.438,0.482,0.001};
    static double[] securityDownLimit = {0.001,0.001,0.001,0.001,0.001};
    
    static char[] OC = {'O','C'};
    static char[] SB = {'1','2'};
        
    static int[] QUANTY = {1,2,3};
    
    static String[] TYPE = {"OLP","OLP","OLP","OLP","OLP","OLP","OLP","OLP","OMP","ONP"};
    static String INSTID = "799888";
    
    
    static String tag_10901 = "I";
   
}
