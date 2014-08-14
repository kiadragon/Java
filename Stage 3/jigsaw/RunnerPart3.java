
import java.io.IOException;

public class RunnerPart3 {
    /**测试脚本-1
     * 实验任务一：利用广度优先搜索，求指定3*3拼图（8-数码问题）的最优解
     * 要求：不修改脚本内容，程序能够运行，且得出预期结果
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        // 检查节点维数是否为3
        if(JigsawNode.getDimension() != 5){
            System.out.print("节点维数不正确，请将JigsawNode类的维数dimension改为5");
            return;
        }

        int total = 0;
        JigsawNode destNode = new JigsawNode(new int[]{25,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,0}); 
        for (int i = 0; i < 20; i++) {
        Jigsaw j = new Jigsaw(Jigsaw.scatter(destNode, 1000), destNode);
        j.ASearch();
        total += j.getSearchedNodesNum();
        }
        System.out.print("Average:  " + total / 20);

        // 执行广度优先搜索算法
    }
}
