package huawei.od;


import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * 华为OD 排序
 * 题目描述
 * VLAN是一种对局域网设备进行逻辑划分的技术，为了标识不同的VLAN，引入 VAN ID(1-4094 之间的整数)的概念.定义一个 VAN I 的资源池(下称VLAN资源池)，资源池中连续的VLAN用 开始vLAN-结東VLA 表示;不连续的用单个整数表示所有的VLAN用英文逗号连接起来。现在有一个VLAN资源池，业务需要从资源池中申请一个VLAN，需要你输出从VLAN资源池中移除申请的VLAN后的资源池。
 * 输入描述
 * 第一行为字符串格式的VLAN资源池，第二行为业务要申请的VLAN，VLAN的取值范围为[1，4094]之间的整数。
 * 输出描述
 * 从输入VLAN资源池中移除申请的VLAN后字符串格式的VLAN资源池，输出要求满足题目描述中的格式，并目按照VLAN从小到大升序输出。
 * 如果申请的VLAN不在原VLAN资源池内，输出原VLAN资源池升序排序后的字符串即可
 * 备注
 * 输入VLAN资源池中VLAN的数量取值范围为[2-4894]间的整数，资源池中VLAN不重复且合法([1,4094]之间的整数)，输入是乱序的。
 */
public class VlanManager {
    public static void main(String[] args) {
        String[] vlanPool = new String[]{"1", "2", "9-10", "11", "7"};
        VlanManager vlanManager = new VlanManager(vlanPool);
        vlanManager.removeVlan(9);
        System.out.println(vlanManager.toStringWithoutForm());
    }

    private TreeSet<int[]> vlanPool;

    public VlanManager(String[] vlanPool) {
        this.vlanPool = new TreeSet<>(Comparator.comparing(item -> item[0]));
        for (int i = 0; i < vlanPool.length; i++) {
            String[] vlanStrRange = vlanPool[i].split("-");
            int vlanStart = Integer.parseInt(vlanStrRange[0]);
            int vlanEnd = vlanStrRange.length > 1 ? Integer.parseInt(vlanStrRange[1]) : vlanStart;
            this.vlanPool.add(new int[]{vlanStart, vlanEnd});
        }
    }

    public void removeVlan(int vlan) {
        Iterator<int[]> iterator = this.vlanPool.iterator();
        while(iterator.hasNext()){
            int[] vlanRange = iterator.next();
            if (vlan<=vlanRange[1]) {
                iterator.remove();
                if(vlan>vlanRange[0]){
                    this.vlanPool.add(new int[]{vlanRange[0], vlan - 1});
                }
                if(vlan<vlanRange[1]){
                    this.vlanPool.add(new int[]{vlan + 1, vlanRange[1]});
                }
                break;
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int[] vlanItem : this.vlanPool) {
            if (sb.length()>0) {
                sb.append(",");
            }
            if (vlanItem[0] == vlanItem[1]){
                sb.append(vlanItem[0]);
            }else {
                sb.append(vlanItem[0] + "-" + vlanItem[1]);
            }
        }
        return sb.toString();
    }

    public String toStringWithoutForm() {
        StringBuilder sb = new StringBuilder();
        for (int[] vlanItem : this.vlanPool) {
            if (sb.length()>0) {
                sb.append(",");
            }
            sb.append(vlanItem[0] + "-" +vlanItem[1]);
        }
        return sb.toString();
    }
}
