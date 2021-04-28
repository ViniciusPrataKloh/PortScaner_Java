package portscanner;

/**
 *
 * @author Vinícius
 */
public class Porta{
    
    private int num;
    private String status;
    
    
    public Porta(int num, String status){
        super();
        this.num = num;
        this.status = status;
        
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }    
}