package regex;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Created by wulei on 16/1/19.
 * 检测正则表达式
 * {Args: abcabcabcdefabc "abc+" "(abc)+" "(abc){2,}"}
 */
public class PrintPattern {
    public static void main(String[] args){
        args=new String[]{"abcabcabcdefabc","abc+","(abc)+","(abc){2,}",".c$"};
        if(args.length<2){
            System.out.print("参数不正确");
            System.exit(0);
        }
        System.out.println("Input: \""+args[0] +"\"");
        for(String arg:args){
            System.out.println("Regular expression :\""+arg+"\"");
            Pattern p=Pattern.compile(arg);
            Matcher m=p.matcher(args[0]);
            while(m.find()){
                System.out.println("Match \""+m.group()+"\" at positions "+
                m.start()+"-"+(m.end()-1));
            }
            if(p.matcher(args[0]).matches()){
                System.out.println("完整匹配"+arg);
            }
            if(p.matcher(args[0]).lookingAt()){
                System.out.println("开头匹配"+arg);
            }
        }
    }
}/*output:
Input: "abcabcabcdefabc"
Regular expression :"abcabcabcdefabc"
Match "abcabcabcdefabc" at positions 0-14
Regular expression :"abc+"
Match "abc" at positions 0-2
Match "abc" at positions 3-5
Match "abc" at positions 6-8
Match "abc" at positions 12-14
Regular expression :"(abc)+"
Match "abcabcabc" at positions 0-8
Match "abc" at positions 12-14
Regular expression :"(abc){2,}"
Match "abcabcabc" at positions 0-8
 */
