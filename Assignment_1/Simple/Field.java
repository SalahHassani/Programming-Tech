package Assignment_1.Simple;

public enum Field {
    Property,
    Lucky,
    Service;


    private int serviceFee;
    private int LuckyBonus;

    Field(){
        this.serviceFee = 100;
        this.LuckyBonus = 200;
    }

    public void setLuckyBonus(int num){
        this.LuckyBonus = num;
    }

    public int getLuckyBounus(){
        return this.LuckyBonus;
    }

    public void setServiceFee(int num){
        this.serviceFee = num;
    }

    public int getServiceFee(){
        return this.serviceFee;
    }
}
