package cn.hzyichuan.fast.web.kaoshi;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {

    // 常量定义
    private static final double CM_TO_INCH = 2.54;
    private static final double KG_TO_LB = 0.454;
    private static final double VOLUME_WEIGHT_BASE = 250.0;

    // 将厘米转换为英寸并向上取整
    private static int convertCmToInches(double cm) {
        return (int) Math.ceil(cm / CM_TO_INCH);
    }

    public static List<String> test(float length, float width, float height, float weight) {
        List<Integer> integers = new ArrayList<>();
        integers.add(convertCmToInches(length));
        integers.add(convertCmToInches(width));
        integers.add(convertCmToInches(height));
        integers.sort((a, b)->{
            if (Objects.equals(a, b)) return 0;
            else if (a>b) return -1;
            else return 1;
        });
        float first = integers.get(0);
        float second = integers.get(1);
        float third = integers.get(2);

        int girth = (int)Math.ceil(first + (second+third)*2);
        int volumeWeight =  (int)Math.ceil(first*second*third/(VOLUME_WEIGHT_BASE));//*
        double actualWeight = Math.max(Math.ceil(weight/KG_TO_LB), volumeWeight);
        List<String> list = new ArrayList<>();
        if (actualWeight>150 || first>108 || girth>165) {
            list.add("OUT_SPACE");
        } else if (girth>130 && girth<=165 || first >=96 && first<108) {
            list.add("OVERSIZE");
        } else {
            if (actualWeight>50 && actualWeight<=150) {
                list.add("AHS-WEIGHT");
            }
            if (girth>105 || first>=48 && third<108 || second>=30) {
                list.add("AHS-SIZE");
            }
        }

        return list;
    }
    public static void main(String[] args) {
        System.out.println(test(68, 70, 60, 23));
        System.out.println(test(114.50f, 42, 26, 47.5f));
        System.out.println(test(162, 60, 11, 14));
        System.out.println(test(113, 64, 42.5f, 35.85f));
        System.out.println(test(114.5f, 17, 51.5f, 16.5f));
    }
}
