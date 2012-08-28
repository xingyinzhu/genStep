
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Random;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author xyzhu
 */
public class Operation extends Thread{
    
    public Operation(int pbuIndex,int totalNum){
        //addOrder = new HashMap<Integer, Record>();
        this.pbuIndex = pbuIndex;
        this.totalNum = totalNum;
        this.SEQNUM = Basic.SEQNUM + pbuIndex * totalNum;
    }
    
    
    /*
    public void clear(){
        addOrder = new HashMap<Integer, Record>();
    }
    */      
    
    public void run(){
        
        int blockNum = totalNum / 100; // 20000
        int cellNum = blockNum / 4;   // 4000+1000 add+del
        int addNum = (int)(cellNum * 0.8);   //4000
        int delNum = (int)(cellNum * 0.2);   //1000
        try{
            FileChannel writer = new RandomAccessFile("C://" + Basic.PBU[pbuIndex] + ".txt","rw").getChannel();  
            ByteBuffer cc;
            Charset utf8 = Charset.forName("utf-8"); 
            
            for(int i=0;i<100;i++)
            {
                for(int j=0;j<4;j++)
                {
                    //add
                    addOrder = new HashMap<Integer, Record>();
                    int lowRange = SEQNUM;

                    String res = "";
                    for(int k=0;k<addNum;k++)
                    {
                        int oc = 0;
                        if (k % 2 == 0)
                        {
                            oc = 1;
                        }
                        else
                        {
                            oc = selectrandomNum(2);
                        }
                        int r = selectrandomNum(Basic.SECURITY.length);
                        res = genOneAddRecord(pbuIndex,r,oc)+"\n";
                        //System.out.println(res);
                        cc = utf8.encode(res);
                        writer.write(cc);

                    }


                    int highRange = SEQNUM;
                    //del
                    res = "";
                    for(int k=0;k<delNum;k++)
                    {
                        int r = selectrandomNum(highRange-lowRange);
                        int delSeqNum = lowRange + r;
                        res = genOneDelRecord(delSeqNum) + "\n";
                        cc = utf8.encode(res);
                        writer.write(cc);

                    }
                }

            }
            writer.close();
        }catch(IOException ex){
            
        }
    }
/*
9=13935=D11=1000000178309=601398128C00440N10901=I44=5.20038=00010054=177=O453=3448=B559315376452=5448=12708452=1448=00J95452=4001||
      |----------------------------------------------------------------139-----------------------------------------------------------------------|
*/  //OC == 1 : O
    //OC == 0 : C
    private String genAddStep(Record tmpR,int pbuIndex,int securityIndex,int OC,String comSeqNum)
    {
        String step ="";
        step += "35=D" + Basic.SOH;
        step += "11=" + comSeqNum + Basic.SOH;
        tmpR.setSeqNum(comSeqNum);
        
        String security = Basic.SECURITY[securityIndex];
        step += "309=" + security + Basic.SOH;
        tmpR.setSecurity(security);
        
        step += "10901=I" + Basic.SOH;
        
        String price = getDoublePrice(securityIndex);
        step += "44=" + price + Basic.SOH;
        tmpR.setPrice(price);
        
        int r = selectrandomNum(Basic.QUANTY.length);
        step += "38=" + Basic.QUANTY[r] + Basic.SOH;
        tmpR.setQuanty(Basic.QUANTY[r]);
        
        int sb = selectrandomNum(2)+1;
        step += "54=" + sb + Basic.SOH;
        tmpR.setSb(sb);
        
        char oc_tag;
        if(OC == 1){
            oc_tag = 'O';
        }else{
            oc_tag = 'C';
        }
        step += "77=" + oc_tag + Basic.SOH;
        tmpR.setOc(oc_tag);
        
        step += "453=3" + Basic.SOH;
        
        step += "448=" + Basic.INVACC[pbuIndex] + Basic.SOH + "452=5" + Basic.SOH;
        tmpR.setInvacc(Basic.INVACC[pbuIndex]);
        
        step += "448=" + Basic.PBU[pbuIndex] + Basic.SOH + "452=1" + Basic.SOH;
        step += "448=" + Basic.BRANCHID + Basic.SOH + "452=4001" + Basic.SOH + Basic.SEP + Basic.SEP;
        
        int len = step.length();
        len -= 2;
        step = "9="+len + Basic.SOH + step;
        return step;
    }
/*
9=15335=F11=100000017941=1000000178309=600252aaaaabbbbb10901=I44=5.20038=50000054=177=C453=3448=X000000001452=5448=12708452=1448=00J95452=4001||
     *|----------------------------------------------------------------------------153-------------------------------------------------------------------------|
     */
    private String genDelStep(Integer delSeqNum,String comSeqNum)
    {
        Record tmpR = addOrder.get(delSeqNum);
        String step = "";
        step += "35=F" + Basic.SOH;
        step += "11=" + comSeqNum + Basic.SOH;
        step += "41=" + tmpR.getSeqNum() + Basic.SOH;
        step += "309=" + tmpR.getSecurity() + Basic.SOH;
        step += "10901=I" + Basic.SOH;
        step += "44=" + tmpR.getPrice() + Basic.SOH;
        step += "38=" + tmpR.getQuanty() + Basic.SOH;
        step += "54=" + tmpR.getSb() + Basic.SOH;
        step += "77=" + tmpR.getOc() + Basic.SOH;
        step += "453=3" + Basic.SOH;
        step += "448=" + tmpR.getInvacc() + Basic.SOH + "452=5" + Basic.SOH;
        step += "448=" + tmpR.getPbu() + Basic.SOH + "452=1" + Basic.SOH;
        step += "448=" + Basic.BRANCHID + Basic.SOH + "452=4001" + Basic.SOH 
                +"448=" + Basic.BRANCHID + Basic.SOH + "452=13" + Basic.SOH 
                + Basic.SEP + Basic.SEP;
        
        int len = step.length();
        len -= 2;
        step = "9="+len + Basic.SOH + step;
        
        return step;
    
    }
    
/*
//add
178|OLP|1000000178|12708|799888||R|| + step
*/
    
    private String genOneAddRecord(int pbuIndex,int securityIndex,int OC){
        Record tmpR = new Record();
        String result = this.SEQNUM.toString() + Basic.SEP;
                
        int r = selectrandomNum(Basic.TYPE.length);
        
        tmpR.setType(Basic.TYPE[r]);
        result += Basic.TYPE[r] + Basic.SEP;
        
        String comSeqNum = completeSeqNum(this.SEQNUM);
        result += comSeqNum + Basic.SEP;
        
        tmpR.setPbu(Basic.PBU[pbuIndex]);
        result += Basic.PBU[pbuIndex] + Basic.SEP;
        
        result += Basic.INSTID + Basic.SEP + Basic.SEP + "R" + Basic.SEP + Basic.SEP;
        
        String step = genAddStep(tmpR,pbuIndex,securityIndex,OC,comSeqNum);
        result += step;
        
        addOrder.put(this.SEQNUM, tmpR);
        this.SEQNUM++;
        return result;
    }
 
/*
//cancel
179|OLP|1000000179|12708|799888||R||
*/
    private String genOneDelRecord(int delSeqNum){
        String result = this.SEQNUM.toString() + Basic.SEP;
        
        Record tmpR = addOrder.get(delSeqNum);
        result += tmpR.getType() + Basic.SEP;
        
        String comSeqNum = completeSeqNum(this.SEQNUM);
        result += comSeqNum + Basic.SEP;
        
        result += tmpR.getPbu() + Basic.SEP;
        
        result += Basic.INSTID + Basic.SEP + Basic.SEP + "R" + Basic.SEP + Basic.SEP;
        
        String step = genDelStep(delSeqNum,comSeqNum);
        result += step;
        
        this.SEQNUM++;
        return result;
    }
       
    private int selectrandomNum(int range)
    {
        Random random = new Random(System. currentTimeMillis());
        int k = random.nextInt();
        return Math.abs(k % range);
    }
    
    private String getDoublePrice(int securityIndex)
    {
        double high = Basic.securityUpLimit[securityIndex];
        double low = Basic.securityDownLimit[securityIndex];
        double interval = high - low;
        double result = low + Math.random()*interval;
        //
        DecimalFormat fnum = new DecimalFormat( "##0.000"); 
        String stringResult=fnum.format(result);
        return stringResult;
    }
    
    private String completeSeqNum(int num)
    {
        //10 bit
        int all = 10;
        String result = num+"";
        String tmp0="";
        int len = result.length();
        for(int i=0;i<10-len;i++)
        {
            tmp0 += "0";
        }
        return tmp0+result;
    }
    
    /*
    public static void main(String[] argv)
    {
        
    }
    */
    
    private HashMap<Integer,Record> addOrder;
    private int pbuIndex;
    private int totalNum;
    private Integer SEQNUM;
}
