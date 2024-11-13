package huawei.od;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 3221.2023B-磁盘容量
 * 中等	华为OD	排序
 * 磁盘的容量单位常用的有M、G、T。他们之间的换算关系为1T =1024G，1G=1024M。现在给定n块磁盘的容量，请对他们按从小到大的顺序进行稳定排序。
 * 例如给定5块盘的容量1T、20M、3G、10G6T、3M12G9M，排序后的结果为20M、3G、3M12G9M、1T、10G6T。注意单位可以重复出现上述3M12G9M表示的容量即为3M12G9M和12M12G相等。
 * 所谓稳定排序，指的是对于大小相同的元素，应该按照元素在原先数组中的位置进行排序。
 *
 * 输入
 * 输入第一行包含一个整数n ，2 <=n<=100，表示磁盘的个数。
 * 接下来的n行，每行一个字符串，2<长度<30，表示磁盘的容量，由一个或多个格式为Mv的子串组成，其中M表示容量大小，v表示容量单位，例如20M、1T。
 * 磁盘容量的范围是1~1024的正整数，单位M、G、T。
 * 输出
 * 输出n行，表示n块磁盘容量排序后的结果
 */
public class DiskCapacitySort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = Integer.parseInt(scanner.nextLine());
        List<String> diskList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            diskList.add(scanner.nextLine());
        }

        diskList.sort(Comparator.comparingLong(DiskCapacitySort::convert2M));
        System.out.println(String.join(",", diskList));
    }

    public static long convert2M(String diskCapacity) {
        long val = 0;
        StringBuilder num = new StringBuilder();

        for (int i = 0; i < diskCapacity.length(); i++) {
            char c = diskCapacity.charAt(i);
            if (c == 'M') {
                val += Integer.parseInt(num.toString());
            } else if (c == 'G') {
                val += Integer.parseInt(num.toString()) * 1024;
            } else if (c == 'T') {
                val += Integer.parseInt(num.toString()) * 1024*1024;
            } else {
                num.append(c);
            }
        }
        return val;
    }
}
