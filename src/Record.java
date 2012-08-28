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

public class Record {

    public String getBranchid() {
        return branchid;
    }

    public void setBranchid(String branchid) {
        this.branchid = branchid;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getInvacc() {
        return invacc;
    }

    public void setInvacc(String invacc) {
        this.invacc = invacc;
    }

    public char getOc() {
        return oc;
    }

    public void setOc(char oc) {
        this.oc = oc;
    }

    public String getPbu() {
        return pbu;
    }

    public void setPbu(String pbu) {
        this.pbu = pbu;
    }

    public int getQuanty() {
        return quanty;
    }

    public void setQuanty(int quanty) {
        this.quanty = quanty;
    }

    public int getSb() {
        return sb;
    }

    public void setSb(int sb) {
        this.sb = sb;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public String getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(String seqNum) {
        this.seqNum = seqNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    private String seqNum;
    private String type;
    private String pbu;
    private String invacc;
    private String branchid;
    private String security;
    private String price;
    private char oc;
    private int sb;
    private int quanty;
    
}
