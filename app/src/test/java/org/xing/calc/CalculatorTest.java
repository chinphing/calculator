package org.xing.calc;

import org.junit.Test;

import java.io.FileInputStream;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import static org.xing.calc.Calculator.createDefault;

/**
 * Created by Administrator on 2017/1/8 0008.
 */
public class CalculatorTest{

    @Test
    public void testEval() throws Exception {
        Calculator calc = createDefault(new FileInputStream("src\\main\\res\\raw\\token"));

        Map<String, String> chnMap = new HashMap<String, String>();

        chnMap.put("sin12", "-0.536573");
        chnMap.put("10%", "0.1");
        chnMap.put("2除以2/3", "3");
        chnMap.put("2除以2/根号3", "1.732050");
        chnMap.put("2除以根号三分之二", "2.449490");

        chnMap.put("sin12", "-0.536573");
        chnMap.put("余弦12", "0.843854");
        chnMap.put("反余弦余弦0.35", "0.35");
        chnMap.put("反正弦正弦0.35", "0.35");
        chnMap.put("反正弦0.54", "0.570437");


        chnMap.put("1000+两千再加3000=多少", "6000");
        chnMap.put("一百加1150加1480+两千+3600", "8330");


        chnMap.put("对数0.54", "-0.616186");
        chnMap.put("3对数0.54", "-0.560877");
        chnMap.put("3根号0.54", "0.814325");
        chnMap.put("3对数百分之二", "-3.560877");
        chnMap.put("七的七对数二次方", "2.0");

        chnMap.put("1加上三十五点二", "36.2");
        chnMap.put("1减10", "-9");
        chnMap.put("log(3点2加15)^2除2乘以3", "26.282218");
        chnMap.put("15*3", "45");
        chnMap.put("(12)", "12");
        chnMap.put("负12", "-12");
        chnMap.put("一加三十五", "36");
        chnMap.put("一减十", "-9");
        chnMap.put("百分之一", "0.01");
        chnMap.put("十五除以二", "7.5");
        chnMap.put("十五乘以三", "45");
        chnMap.put("15×3", "45");
        chnMap.put("十五分之三", "0.2");
        chnMap.put("一点三五加三十五点二零", "36.55");
        chnMap.put("零点二四减去二点五三", "-2.29");
        chnMap.put("零点二四乘以二点五三", "0.6072");
        chnMap.put("零点二四除以二点五三", "0.094861");
        chnMap.put("零点二四除以五分之三点二", "0.375");
        chnMap.put("一百零一点三加一万零一百", "10201.3");
        chnMap.put("一百零一减去一万零一百", "-9999.0");
        chnMap.put("一百零一乘一万零一百", "1020100.0");
        chnMap.put("一百零一除以一万零一百点五六", "0.009999");
        chnMap.put("零点二四减去二点五三的平方", "-6.160899");
        chnMap.put("零点二四乘以二点五三的开方", "0.381743");
        chnMap.put("零点二四除以二点五三的五次方", "0.002315");
        chnMap.put("零点二四除以五的立方", "0.001919");
        chnMap.put("零点二四加五分之三点二", "0.88");
        chnMap.put("零点二四减五分之三点二", "-0.4");
        chnMap.put("零点二四乘五分之三点二", "0.1536");
        chnMap.put("三千零十五万点二零加上一百五十点三五乘以一百零二", "30165335.9");
        chnMap.put("三千零十五万点二零减去一百五十点三五除以一百零二", "30149998.725980");

        chnMap.put("一百一加一万零一百", "10210");
        chnMap.put("一百一加一万一", "11110");
        chnMap.put("一百零一减去一万零一百", "-9999");
        chnMap.put("二^2", "4");

        //小数，函数，分数 测试
        chnMap.put("零点二四加sin五分之三点二", "0.837195");
        chnMap.put("零点二四减cos五分之三点二", "-0.562096");
        chnMap.put("零点二四乘tan五分之三点二", "0.178691");
        chnMap.put("零点二四加lg五分之三点二", "0.0461800");
        chnMap.put("零点二四减ln五分之三点二", "0.686287");
        chnMap.put("零点二四乘log五分之三点二", "-0.154525");

        //函数，小数，分数，幂运算 混合测试
        chnMap.put("零点二四加sin五分之三点二的三点二次方", "0.432120");
        chnMap.put("零点二四减cos五分之三点二的三点二次方", "-0.253769");
        chnMap.put("零点二四乘tan五分之三点二的三点二次方", "0.093381");

        chnMap.put("零点二四加lg五分之一百三点二的三点二次方", "2.640244");
        chnMap.put("零点二四减ln五分之一百三点二的三点二次方", "-34.381491");
        chnMap.put("零点二四乘log五分之一百三点二的三点二次方", "26.848222");

        chnMap.put("零点二四加lg(五分之一百三点二的三点二次方)", "4.447071");
        chnMap.put("零点二四减ln(五分之一百三点二的三点二次方)", "-9.447139");
        chnMap.put("零点二四乘log(五分之一百三点二的三点二次方)", "3.354140");

        //括号测试
        chnMap.put("括号1+3乘以括号1+2括号括号", "10.0");
        chnMap.put("|1+3乘以括号1+2|括号", "10.0");
        chnMap.put("log|1+3乘以括号1+2|括号", "3.321928");

        //多重幂运算
        chnMap.put("根号四的负四分之三次方的二次方", "0.353553");
        chnMap.put("根号根号八", "1.681792");
        chnMap.put("三的四次方的负四分之三次方", "0.037037");
        chnMap.put("零点零零八一的四分之一次方加上四的负四分之三次方的二次方加上根号八的负四分之三次方减去16的负零点75次方", "0.758502");
        chnMap.put("log根号二十七除以log3加上lg二十五加上lg4加上七的括号log2除以log7括号次方加上负九点八的零次方", "6.5");

        //postFuncname测试
        chnMap.put("三开方", "1.732051");
        chnMap.put("三的开方", "1.732051");
        chnMap.put("三开平方", "1.732051");
        chnMap.put("三开立方", "1.442250");
        chnMap.put("三的平方根", "1.732051");
        chnMap.put("三的立方根", "1.442250");
        chnMap.put("三平方根", "1.732051");
        chnMap.put("三乘以三开方", "5.196152");

        //弧度制和π
        chnMap.put("负三点一五派", "-9.896017");
        chnMap.put("正弦六十度", "0.866025");
        chnMap.put("正弦三十度", "0.500000");
        chnMap.put("1000+两千再加3000=多少", "6000");
        chnMap.put("派", "3.1415926");
        chnMap.put("负派", "-3.141593");
        chnMap.put("根号派", "1.772454");

        int correctNum = 0;
        int wrongNum = 0;
        for(String expr : chnMap.keySet()) {
            double value = Double.parseDouble(chnMap.get(expr));

            double result = Double.NaN;
            try{
                result = calc.eval(expr);
            }catch(Exception ex) {
                System.err.println(expr);
                ex.printStackTrace();
            }

            System.out.printf("%s\n%s\n\t%.6f\n", expr, calc.getReadExpr(), result);
            if(Double.isNaN(result) || Math.abs(result - value) > 1e-6) {
                System.out.println("错误："+expr+", "+value+", 错误结果："+result+"\n");
                wrongNum ++;
            } else {
                correctNum ++;
            }
        }

        //连续运算
        Vector<Map.Entry<String, String>> contiExpr = new Vector<>();

        contiExpr.add(new AbstractMap.SimpleEntry<String, String>("6.471928-3.15", "3.321928"));
        contiExpr.add(new AbstractMap.SimpleEntry<String, String>("加三点一五", "6.471928"));
        contiExpr.add(new AbstractMap.SimpleEntry<String, String>("减三点一五", "3.321928"));
        contiExpr.add(new AbstractMap.SimpleEntry<String, String>("乘三点一五", "10.464073"));
        contiExpr.add(new AbstractMap.SimpleEntry<String, String>("区三点一五", "3.321928"));
        contiExpr.add(new AbstractMap.SimpleEntry<String, String>("根号", "1.822616"));
        contiExpr.add(new AbstractMap.SimpleEntry<String, String>("对数", "0.600273"));
        contiExpr.add(new AbstractMap.SimpleEntry<String, String>("争先", "0.564868"));
        contiExpr.add(new AbstractMap.SimpleEntry<String, String>("反正弦", "0.600273"));
        contiExpr.add(new AbstractMap.SimpleEntry<String, String>("开方", "0.774773"));
        contiExpr.add(new AbstractMap.SimpleEntry<String, String>("平方根", "0.880212"));

        contiExpr.add(new AbstractMap.SimpleEntry<String, String>("三又四分之一", "3.25"));
        contiExpr.add(new AbstractMap.SimpleEntry<String, String>("负三又四分之一", "-3.25"));

        contiExpr.add(new AbstractMap.SimpleEntry<String, String>("一千万", "10000000"));
        contiExpr.add(new AbstractMap.SimpleEntry<String, String>("十六万亿", "16000000000000"));
        contiExpr.add(new AbstractMap.SimpleEntry<String, String>("二对数三乘以二队戍午", "3.680169"));

        contiExpr.add(new AbstractMap.SimpleEntry<String, String>("一百除二等于多少", "50"));

        contiExpr.add(new AbstractMap.SimpleEntry<String, String>("三点一点零点一加", "3"));
        contiExpr.add(new AbstractMap.SimpleEntry<String, String>("三点一点零点一", "3"));
        contiExpr.add(new AbstractMap.SimpleEntry<String, String>("26.9点59.9", "-33"));
        contiExpr.add(new AbstractMap.SimpleEntry<String, String>("点一八×两点四", "-76.2"));
        contiExpr.add(new AbstractMap.SimpleEntry<String, String>("点42", "-118.2"));

        contiExpr.add(new AbstractMap.SimpleEntry<String, String>("21的三九减四点六", "16.79"));
        contiExpr.add(new AbstractMap.SimpleEntry<String, String>("21.39点四点六的差除以七的三", "20.759863"));
        contiExpr.add(new AbstractMap.SimpleEntry<String, String>("21.39减四点六的差除以七的三", "20.759863"));
        contiExpr.add(new AbstractMap.SimpleEntry<String, String>("五点四乘六得四八成二", "69.984"));
        contiExpr.add(new AbstractMap.SimpleEntry<String, String>("正玄三十度", "0.5"));
        contiExpr.add(new AbstractMap.SimpleEntry<String, String>("副根号1.96", "-1.4"));
        contiExpr.add(new AbstractMap.SimpleEntry<String, String>("三平方", "9"));
        contiExpr.add(new AbstractMap.SimpleEntry<String, String>("三平方根", "1.732051"));
        contiExpr.add(new AbstractMap.SimpleEntry<String, String>("负二的零次方", "1.732051"));

        for(Map.Entry<String, String> en : contiExpr) {
            String expr = en.getKey();
            double value = Double.parseDouble(en.getValue());

            double result = Double.NaN;
            try{
                result = calc.eval(expr);
            }catch(Exception ex) {
                System.err.println(expr);
                ex.printStackTrace();
            }

            System.out.printf("%s\n%s\n\t%.6f\n", expr, calc.getReadExpr(), result);
            if(Double.isNaN(result) || Math.abs(result - value) > 1e-6) {
                System.out.println("错误："+expr+", "+value+", 错误结果："+result+"\n");
                wrongNum ++;
            } else {
                correctNum ++;
            }
        }

        System.out.println("总计：\n\t正确 "+correctNum+", 错误 "+wrongNum);
    }

}