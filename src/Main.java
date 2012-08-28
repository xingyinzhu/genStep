/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author xyzhu
 */


public class Main {
    
    
    public static void main(String arg[])
    {
        int oneGroupNum = Basic.MAXN / Basic.GROUP;
        
        for(int i = 0; i < Basic.PBU.length; i++)
        {
            Operation Oper = new Operation(i,oneGroupNum);
            Oper.start();
                
        }
    
    }
}
