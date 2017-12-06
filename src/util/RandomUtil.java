package util;
/**
 * 随机生成工具类
 * @author wulei
 *
 */
public class RandomUtil {

	/**
	 * 生成随机的双精度范围值
	 * @param min 最小的值
	 * @param max 最大的值
	 * @return
	 */
	public static double randomDouble(double min,double max){
		return Math.random()*(max-min)+min;
	}
	/**
	 * 生成随机long类型
	 * @param min
	 * @param max
	 * @return
	 */
	public static long randomLong(long min,long max){
		return (long) (Math.random()*(max-min+1)+min);
	}
	public static int randomInt(int min,int max){
		return (int) (Math.random()*(max-min+1)+min);
	}
	public static void main(String args[]){
		System.out.println(randomLong(1,2 ));
	}
	/**
	 * 返回指定位数的随机字符串
	 * @param count 位数
	 * @return
	 */
    public static String randomStr(int count){
    	String randomL="1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    	String random="";
    	while(count>0){
    	int randomInt=randomInt(0,35);
    	random+=randomL.charAt(randomInt);
    	count--;
    	}
    	return random;
    }
	
}
