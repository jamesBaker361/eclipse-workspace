
public class PayFee {

	public PayFee() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double fee=0.0;
		double money=IO.readDouble();
		if((money <= 500)&&(money>=0)) {
			fee=10.0;
		}else if((money>500)&&(money<5000)) {
			if((0.01*money)>20.0){
				fee=0.01*money;
			}else {
				fee=20.0;
			}
		}else if((money>=5000)&&(money<10000)) {
			if((.02*money)>120.0) {
				fee=0.02*money;
			}else {
				fee=120.0;
			}
		}else if((money >=10000)) {
			if(money-15000>0) {
				fee=350.0+(.04*(money-15000));
			}else {
				if(money-10000>0) {
					fee=200.0+(0.03*(money-10000));
				}else {
					fee=(.02*10000);
				}
			}
		}else if(money<0) {
			IO.reportBadInput();
		}
		IO.outputDoubleAnswer(fee);
	}

}
