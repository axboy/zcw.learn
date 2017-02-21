import java.math.BigInteger;

public class MainApp{
    
	public static BigInteger ONE = BigInteger.valueOf(1);
    public static BigInteger TWO = BigInteger.valueOf(2);

    public static void main(String[] args){
        MainApp app = new MainApp();
        BigInteger result = app.func1(10000);
        System.out.println("func1:" + result.toString());
    }

    /**
     * 方法一：用数学化简方法计算
	 * 化简结果：f(n)=2^n + 3*(n-1)*2^(n-1)
	 * @return
	 */
	public BigInteger func1(int n){
		BigInteger res1 = this.TWO.pow(n);   	 //res1 = 2^n;
		BigInteger res2 = this.TWO.pow(n-1);	 //res2 = 2^(n-1);
		int res3 = 9999 * 3;					 //res3 = (n-1)*3
		BigInteger res4 = BigInteger.valueOf(res3).multiply(res2);		//res4 = res3 * res2;
		return res1.add(res4);                   //res1 + res4
	}

    /**
     * 先计算0~10000的2^n的值，再递归求解，直接递归会内存溢出
     */
    public BigInteger func2(int n){
        return null;
    }

    
    /**
     * 逆向计算，未找到以前写的代码，有空补上
     */
    public BigInteger func3(int n){
        return null;
    }
}