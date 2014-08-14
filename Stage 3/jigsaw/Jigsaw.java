import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

/**
 *  in the class complete the code and finish the jigsaw games
 * 
 * @author abe
 *
 */
public class Jigsaw {
    JigsawNode beginJNode; 
    // start point
    JigsawNode endJNode; 
    // end point
    JigsawNode currentJNode;
    // current state
    private Vector<JigsawNode> openList; 
    // open list : save discovered nodes
    private Vector<JigsawNode> closeList;
    // close list : save visited node
    private Vector<JigsawNode> solutionPath;
    // save solutionPath
    private boolean isCompleted; 
    // finished mark 
    private int searchedNodesNum;
    // visited nodes' numbers

    /**
     * constructor of jigsaw
     * 
     * @param bNode
     *            - started point
     * @param eNode
     *            - ended point
     */
    public Jigsaw(JigsawNode bNode, JigsawNode eNode) {
        this.beginJNode = new JigsawNode(bNode);
        this.endJNode = new JigsawNode(eNode);
        this.currentJNode = new JigsawNode(bNode);
        this.openList = new Vector<JigsawNode>();
        this.closeList = new Vector<JigsawNode>();
        this.solutionPath = null;
        this.isCompleted = false;
        this.searchedNodesNum = 0;
    }

    /**
     * disperse the jigsaw
     * 
     * @param jNode
     *            - Start Status
     * @param len
     *            - randomly move
     * @return dispersed jigsaw
     */
    public static JigsawNode scatter(JigsawNode jNode, int len) {
        int randomDirection;
        len += (int) (Math.random() * 2);
        JigsawNode jigsawNode = new JigsawNode(jNode);
        for (int t = 0; t < len; t++) {
            int[] movable = jigsawNode.canMove();
            do {
                randomDirection = (int) (Math.random() * 4);
            } while (0 == movable[randomDirection]);
            jigsawNode.move(randomDirection);
        }
        jigsawNode.setInitial();
        return jigsawNode;
    }

    /**
     * get current node state
     * 
     * @return currentJNode
     */
    public JigsawNode getCurrentJNode() {
        return currentJNode;
    }

    /**
     * set current node state
     * 
     * @param jNode
     */
    public void setBeginJNode(JigsawNode jNode) {
        beginJNode = jNode;
    }

    /**
     * getBeginjNode()
     * 
     * @return beginJNode
     */
    public JigsawNode getBeginJNode() {
        return beginJNode;
    }

    /**
     * 
     * @param jNode
     */
    public void setEndJNode(JigsawNode jNode) {
        this.endJNode = jNode;
    }

    /**
     * getEndJNode
     * 
     * @return endJNode
     */
    public JigsawNode getEndJNode() {
        return endJNode;
    }

    /**
     * get is Completed?
     * 
     * @return isCompleted
     */
    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     * caculate solutionPath()
     * 
     * @return success boolean
     * */
    private boolean calSolutionPath() {
        if (!this.isCompleted()) {
            return false;
        } else {
            JigsawNode jNode = this.currentJNode;
            solutionPath = new Vector<JigsawNode>();
            while (jNode != null) {
                solutionPath.addElement(jNode);
                jNode = jNode.getParent();
            }
            return true;
        }
    }

    /**
     * 获取解路径文本
     * 
     * @return 解路径solutionPath的字符串，若有解，则分行记录从初始状态到达目标状态的移动路径中的每一个状态节点；
     *         若未解或无解，则返回提示信息。
     */
    public String getSolutionPath() {
        String str = new String();
        str += "Begin->";
        if (this.isCompleted) {
            for (int i = solutionPath.size() - 1; i >= 0; i--) {
                str += solutionPath.elementAt(i).toString() + "->";
            }
            str += "End";
        } else
            str = "Jigsaw Not Completed.";
        return str;
    }

    /**
     * 获取访问过的节点数searchedNodesNum
     * 
     * @return 返回所有已访问过的节点总数
     */
    public int getSearchedNodesNum() {
        return searchedNodesNum;
    }

    /**
     * 将搜索结果写入文件中，同时显示在控制台 若搜索失败，则提示问题无解，输出已访问节点数；
     * 若搜索成功，则输出初始状态beginJnode，目标状态endJNode
     * ，已访问节点数searchedNodesNum，路径深度nodeDepth和解路径solutionPath。
     * 
     * @param pw
     *            - 文件输出PrintWriter类对象，如果pw为null，则写入到D://Result.txt
     * @throws IOException
     */
    public void printResult(PrintWriter pw) throws IOException {
        boolean flag = false;
        if (pw == null) {
            pw = new PrintWriter(new FileWriter("Result.txt"));// 将搜索过程写入D://BFSearchDialog.txt
            flag = true;
        }
        if (this.isCompleted == true) {
            // 写入文件
            pw.println("Jigsaw Completed");
            pw.println("Begin state:" + this.getBeginJNode().toString());
            pw.println("End state:" + this.getEndJNode().toString());
            pw.println("Solution Path: ");
            pw.println(this.getSolutionPath());
            pw.println("Total number of searched nodes:"
                    + this.getSearchedNodesNum());
            pw.println("Length of the solution path is:"
                    + this.getCurrentJNode().getNodeDepth());

            // 输出到控制台
            System.out.println("Jigsaw Completed");
            System.out
                    .println("Begin state:" + this.getBeginJNode().toString());
            System.out.println("End state:" + this.getEndJNode().toString());
            System.out.println("Solution Path: ");
            System.out.println(this.getSolutionPath());
            System.out.println("Total number of searched nodes:"
                    + this.getSearchedNodesNum());
            System.out.println("Length of the solution path is:"
                    + this.getCurrentJNode().getNodeDepth());

        } else {
            // 写入文件
            pw.println("No solution. Jigsaw Not Completed");
            pw.println("Begin state:" + this.getBeginJNode().toString());
            pw.println("End state:" + this.getEndJNode().toString());
            pw.println("Total number of searched nodes:"
                    + this.getSearchedNodesNum());

            // 输出到控制台
            System.out.println("No solution. Jigsaw Not Completed");
            System.out
                    .println("Begin state:" + this.getBeginJNode().toString());
            System.out.println("End state:" + this.getEndJNode().toString());
            System.out.println("Total number of searched nodes:"
                    + this.getSearchedNodesNum());
        }
        if (flag)
            pw.close();
    }
    
    
    public void printSimpleResult(PrintWriter pw) throws IOException {
        boolean flag = false;
        if (pw == null) {
            pw = new PrintWriter(new FileWriter("Result.txt"));// 将搜索过程写入D://BFSearchDialog.txt
            flag = true;
        }
        if (this.isCompleted == true) {
            // 写入文件
            pw.println("Jigsaw Completed");
            pw.println("Begin state:" + this.getBeginJNode().toString());
            pw.println("End state:" + this.getEndJNode().toString());
            // 输出到控制台
            System.out.println("Jigsaw Completed");
            System.out
                    .println("Begin state:" + this.getBeginJNode().toString());
            System.out.println("Total number of searched nodes:"
                    + this.getSearchedNodesNum());


        } else {
            // 写入文件
            pw.println("No solution. Jigsaw Not Completed");
            pw.println("Begin state:" + this.getBeginJNode().toString());
            pw.println("End state:" + this.getEndJNode().toString());
            pw.println("Total number of searched nodes:"
                    + this.getSearchedNodesNum());

            // 输出到控制台
            System.out.println("No solution. Jigsaw Not Completed");
            System.out
                    .println("Begin state:" + this.getBeginJNode().toString());
            System.out.println("End state:" + this.getEndJNode().toString());
            System.out.println("Total number of searched nodes:"
                    + this.getSearchedNodesNum());
        }
        if (flag) {
            pw.close();
        }
    }

    /**
     * 探索所有与jNode邻接(上、下、左、右)且未曾被访问的节点
     * 
     * @param jNode
     *            - 要探索的节点
     * @return 包含所有与jNode邻接且未曾被访问的节点的Vector<JigsawNode>对象
     */
    private Vector<JigsawNode> findFollowJNodes(JigsawNode jNode) {
        Vector<JigsawNode> followJNodes = new Vector<JigsawNode>();
        JigsawNode tempJNode;
        for (int i = 0; i < 4; i++) {
            tempJNode = new JigsawNode(jNode);
            if (tempJNode.move(i) && !this.closeList.contains(tempJNode)
                    && !this.openList.contains(tempJNode)) {
                followJNodes.addElement(tempJNode);
            }
        }
        return followJNodes;
    }

    /**
     * 排序插入openList：按照节点的代价估值（estimatedValue）将节点插入openList中，估值小的靠前。
     * 
     * @param jNode
     *            - 要插入的状态节点
     */
    private void sortedInsertOpenList(JigsawNode jNode) {
        this.estimateValue(jNode);
        for (int i = 0; i < this.openList.size(); i++) {
            if (jNode.getEstimatedValue() < this.openList.elementAt(i)
                    .getEstimatedValue()) {
                this.openList.insertElementAt(jNode, i);
                return;
            }
        }
        this.openList.addElement(jNode);
    }

    // ****************************************************************
    // *************************实验任务************************
    /**
     * 实验任务一：广度优先搜索算法，求指定3*3拼图（8-数码问题）的最优解
     * 要求：填充广度优先搜索算法BFSearch()，执行测试脚本RunnerPart1 主要涉及函数：BFSearch()
     */
    /**
     * 实验任务二：启发式搜索算法，求解随机5*5拼图（24-数码问题）
     * 要求：1.修改启发式搜索算法ASearch()和代价估计函数estimateValue()，执行测试脚本RunnerPart2
     * 2.访问节点总数不超过25000个 主要涉及函数：ASearch()，estimateValue()
     */
    // ****************************************************************

    /**
     * （实验一）广度优先搜索算法，求解指定3*3拼图（8-数码问题）的最优解。 要求函数结束后：1,isCompleted记录了求解完成状态；
     * 2,closeList记录了所有访问过的节点； 3,searchedNodesNum记录了访问过的节点数；
     * 4,solutionPath记录了解路径。
     * 
     * @return isCompleted, 搜索成功时为true,失败为false
     * @throws IOException
     */
    public boolean BFSearch() throws IOException {
        // 将搜索过程写入D://BFSearchDialog.txt
        String filePath = "BFSearchDialog.txt";
        PrintWriter pw = new PrintWriter(new FileWriter(filePath));
        // *************************************

        // Write your code here.
        Vector<JigsawNode> followJNodes = new Vector<JigsawNode>();
        // isCompleted default false;
        isCompleted = false;
        // insert into open lists
        this.insertOpenList(beginJNode);
        // 形如 ASearch
        // 设定 50000 次后 如没有完成自动结束
        int index = 0;
        while (!this.openList.isEmpty() || index == 50000) {
            index++;
            this.currentJNode = this.openList.elementAt(0);
            // 判断 是否到达出口
            if (this.currentJNode.equals(this.endJNode)) {
                isCompleted = true;
                this.calSolutionPath();
                break;
            }

            this.openList.removeElementAt(0);
            this.closeList.addElement(this.currentJNode);
            // write and record searching process
            pw.println("Searching.....Number of searched nodes:"
                    + this.closeList.size() + "   Current state:"
                    + this.currentJNode.toString());
            System.out.println("Searching.....Number of searched nodes:"
                    + this.closeList.size() + "   Current state:"
                    + this.currentJNode.toString());
            // find all currentJNode unvisited and adjacent
            followJNodes = this.findFollowJNodes(this.currentJNode);
            while (!followJNodes.isEmpty()) {
                this.insertOpenList(followJNodes.elementAt(0));
                followJNodes.removeElementAt(0);
                // searchedNodesNum 自加
                searchedNodesNum++;
            }
        }

        // *************************************
        this.printResult(pw);
        pw.close();
        System.out.println("Record into " + filePath);
        return isCompleted;
    }

    /**
     * 无估计的广度优先搜， 供BFSearch使用
     * 
     * @param jNode
     */
    private void insertOpenList(JigsawNode jNode) {
        this.openList.addElement(jNode);
    }

    public boolean ASearch() throws IOException{
        // 将搜索过程写入ASearchDialog.txt
        String filePath = "ASearchDialog.txt";
        PrintWriter pw = new PrintWriter(new FileWriter(filePath));
        
        // 访问节点数大于30000个则认为搜索失败
        int maxNodesNum = 30000;  
        
        // 用以存放某一节点的邻接节点
        Vector<JigsawNode> followJNodes = new Vector<JigsawNode>(); 
        
        // 重置求解完成标记为false
        isCompleted = false;           
        
        // (1)将起始节点放入openList中
        this.sortedInsertOpenList(this.beginJNode);
        
        // (2) 如果openList为空，或者访问节点数大于maxNodesNum个，则搜索失败，问题无解;否则循环直到求解成功
        while (this.openList.isEmpty() != true && searchedNodesNum <= maxNodesNum) {
            
            // (2-1)访问openList的第一个节点N，置为当前节点currentJNode
            //      若currentJNode为目标节点，则搜索成功，设置完成标记isCompleted为true，计算解路径，退出。
            this.currentJNode = this.openList.elementAt(0);
            if (this.currentJNode.equals(this.endJNode)){
                isCompleted = true;
                this.calSolutionPath();
                break;
            }
            
            // (2-2)从openList中删除节点N,并将其放入closeList中，表示以访问节点         
            this.openList.removeElementAt(0);
            this.closeList.addElement(this.currentJNode);
            searchedNodesNum++;
            
                // 记录并显示搜索过程
                pw.println("Searching.....Number of searched nodes:" + this.closeList.size() + "   Current state:" + this.currentJNode.toString());
                System.out.println("Searching.....Number of searched nodes:" + this.closeList.size() + "   Current state:" + this.currentJNode.toString());         

            // (2-3)寻找所有与currentJNode邻接且未曾被访问的节点，将它们按代价估值从小到大排序插入openList中
            followJNodes = this.findFollowJNodes(this.currentJNode);
            while (!followJNodes.isEmpty()) {
                this.sortedInsertOpenList(followJNodes.elementAt(0));
                followJNodes.removeElementAt(0);
            }
        }
        
        this.printResult(pw);   // 记录搜索结果
        pw.close();             // 关闭输出文件
        System.out.println("Record into " + filePath);
        return isCompleted;
    }

    /**
     * （Demo+实验二）计算并修改状态节点jNode的代价估计值:f(n)=s(n)。 
     * 
     * @param jNode
     *            - 要计算代价估计值的节点；此函数会改变该节点的estimatedValue属性值。
     */
    private void estimateValue(JigsawNode jNode) {
        int result = 0;

        result = (int)(3 * estimate1(jNode) + 1 * estimate2(jNode) + 4 * estimate3(jNode));
        jNode.setEstimatedValue(result);
    }

    /**
     * compare with 
     * confirm de
     * @param jNode
     * @return s
     */
    private int estimate1(JigsawNode jNode) {
        // store unequal number
        int s = 0;
        int dimension = JigsawNode.getDimension();
        int[] array = jNode.getNodesState();
        for (int index = 1; index < dimension * dimension; index++) {
            // compare with rows
            if (array[index] + 1 != array[index + 1]) {
                s = s + 1;
            }
        }
//        for (int index = 0; index < dimension * dimension - dimension; index++) {
//            // compare with columns
//            if (array[index] + dimension != array[index + dimension]) {
//                s = s + 1;
//            }
//        }
        return s;
    }

    /**
     * compare with grid if each is equals to index
     * 
     * @param jNode
     * @return s
     */
    private int estimate2(JigsawNode jNode) {
        // s 值
        int s = 0;
        int dimension = JigsawNode.getDimension();
        int[] array = jNode.getNodesState();
        //
        for (int r = 1; r <= dimension * dimension; r++) {
            if (endJNode.getNodesState()[r] != array[r]) {
                s = s + 1;
            }
        }
        return s;
    }
    /**
     *  estimate3
     *  heuristics distance
     * @param jNode
     * @return  s
     */
    private int estimate3(JigsawNode jNode) {
        // s值
        int s = 0;
        int dimension = JigsawNode.getDimension();
        int[] array = jNode.getNodesState();
        for (int i = 1; i <= dimension * dimension; i++) {
            int difRow = Math.abs(((array[i] - 1)/ dimension) - ((i - 1)/ dimension));
            int difCol = Math.abs(((array[i] - 1) % dimension) - ((i - 1)% dimension));
            if (array[i] == 0) {
                difCol = Math.abs(dimension - 1 - ((i - 1) % dimension));
                difRow = Math.abs(dimension - 1 - ((i - 1)/ dimension));
            }
            // System.out.print("difRow:" + difRow +" difCol:" + difCol + "  array[i]:" + array[i] + "   i:" + i  + "\n");
            s += (difCol + difRow);
        }
        return s;
    }
}
