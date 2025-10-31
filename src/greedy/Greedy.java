package greedy;

import java.util.*;

class HuffmanNode{

    char ch;
    int freq;
    HuffmanNode left;
    HuffmanNode right;

    public HuffmanNode(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
    }

    public HuffmanNode(char ch, int freq, HuffmanNode left, HuffmanNode right) {
        this.ch = ch;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }
}

public class Greedy {

    /* GENERAL GREEDY ALGO SUDO CODE
    *
    * getOptimal(item[] arr){
    *   res = 0;
    *   while(all items are not considered){
    *       i = selectAnItem(); // items may be kept in an order based on any criteria. ex:- sorted in coins problem, minHeapified in shortest path problems
    *       if(feasible(i)) res = res + i;
    *   }
    *   return res;
    * }
    *
    * */

    public static int maximumNoOfActivities(int[][] activities){
        // activity is (startTime,endTime)
        Arrays.sort(activities,Comparator.comparingInt(activity -> activity[1])); // sorting based on endTime
        int cnt = 0;
        int currTime = 0;
        System.out.println("Selected activities in order to execute maximum possible, one at a time by a Machine: ");
        for(int[] activity: activities){
            if(activity[0] >= currTime){
                // activity can be selected if it's startTime is greater than currTime
                System.out.print(Arrays.toString(activity) + " ");
                cnt++;
                currTime = activity[1]; // updating currTime to current selected activity endTime
            }
        }
        System.out.println();
        return cnt;
    }

    public static void testActivitySelectionProblem(){
        int[][] activities = {{2,3},{1,4},{5,8},{6,10}};
        System.out.println("Maximum no og activities : "+maximumNoOfActivities(activities));
    }


    public static int fractionalKnapsack(int[][] data, int capacity){
        /*
        * here data holds list of pairs (weight,value)
        * for a given weight capacity, we need to optimize maximum value possible in a knapsack.
        * we can take fractional weight also
        * */
        // sorting in descending order of value/weight
        // minus '-' in comparator makes it descending order
        Arrays.sort(data, Comparator.comparingDouble(item -> -((double) item[1] / item[0]))); // double is needed when considering fractions
//        System.out.println(Arrays.deepToString(data));
        int knapsackValue = 0;
        for(int[] pair: data){
            int weight = pair[0];
            int value = pair[1];
            int taking = Math.min(weight,capacity);
            knapsackValue += taking * ((double) value/weight); // applying double to value only to get double answer
            capacity -= taking;
            if(capacity == 0) return knapsackValue;
        }

        return knapsackValue;
    }

    public static void testFractionalKnapsack(){
        int[][] data = {{50,600},{20,500},{30,400}};
        int capacity = 70;
        System.out.println("Maximum fractionKnapsack value: " + fractionalKnapsack(data,capacity));
    }

    public static int jobSequencingMaximumProfitManoj(int[][] jobs){
        // My Logic: from maxDeadline to 1(reverse order), I pick jobs and place them in profitable order
        // each job is defined as a pair (deadline,profit)
        // i am using hashmap
        // where deadlines as keys and MaxHeap of Profits of respective deadlines as values
        HashMap<Integer,PriorityQueue<Integer>> hm = new HashMap<>();
        int maxDeadline = 0;
        for(int[] job: jobs){
            int deadline = job[0];
            int profit = job[1];
            if(hm.containsKey(deadline)){
                hm.get(deadline).add(profit);
            }else{
                hm.put(deadline,new PriorityQueue<>(Comparator.reverseOrder())); // maxHeap, will give maximum profit assigned to particular deadline
                hm.get(deadline).add(profit);
            }
            maxDeadline = Math.max(maxDeadline,deadline);
        }
        int[] profits = new int[maxDeadline+1]; // we need maxdeadline as index
        for(int deadline = maxDeadline;deadline > 0; deadline--){
            // we should fill profits in decreasing order of deadlines
            int pick = -1;
            for(int j = maxDeadline; j>=deadline; j--){
                if(hm.containsKey(j) && !hm.get(j).isEmpty()){
                    if(hm.get(j).peek() > profits[deadline]){
                        profits[deadline] = hm.get(j).peek();
                        pick = j; // picking maximum profit job within deadline
                    }
                }
            }
            if(pick != -1) hm.get(pick).poll(); // removing as it is used in profits[deadline]
        }
        System.out.println(Arrays.toString(profits));
        return Arrays.stream(profits).sum();
    }

    public static int jobSequencingMaximumProfit(int[][] jobs){
        // author logic: for each maxProfitableJob, author is picking slot available Job's deadline to 1(reverse order).
        Arrays.sort(jobs, Comparator.comparingInt(job -> -job[1])); // minus '-' makes it decreasing order of profits
        int maxDeadline = Arrays.stream(jobs).map(job -> job[0]).reduce(Math::max).get();
        int[] profits = new int[maxDeadline+1]; // we need maxdeadline index
        for(int[] job: jobs){
            int deadline = job[0];
            int profit = job[1];
            for(int j = deadline; j>=1; j--){
                if(profits[j] == 0){
                    profits[j] = profit; // job assigned
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(profits));
        return Arrays.stream(profits).sum();
    }

    public static void testJobSequencingMaximumProfit(){
        int[][] jobs = {{4,50},{1,5},{1,20},{5,10},{5,80}};
        System.out.println(jobSequencingMaximumProfitManoj(jobs));
        System.out.println(jobSequencingMaximumProfit(jobs));
    }

    public static HuffmanNode buildHuffmanBT(HashMap<Character,Integer> charFreq){
        // Huffman Binary Tree building for encoding chars along with their frequencies
        // build all leaf Nodes and put them in minHeap based on freq of char
        PriorityQueue<HuffmanNode> mnh = new PriorityQueue<>(Comparator.comparingInt(node -> node.freq));
        for(Map.Entry<Character,Integer> e: charFreq.entrySet()){
            mnh.add(new HuffmanNode(e.getKey(),e.getValue())); // adding nodes in mnh, they will be used as leaf nodes
        }

        while(mnh.size() > 1){
            HuffmanNode left = mnh.poll(); // first minimum will be left node
            HuffmanNode right = mnh.poll(); // second minimum will be right node
            HuffmanNode currRoot = new HuffmanNode('$',left.freq+right.freq, left,right); // using $ for non-leaf node
            mnh.add(currRoot);
        }

        return mnh.poll(); // last node rootNode of HuffmanBT
    }

    public static void printHuffmanCodes(HuffmanNode root, String code){
        if(root == null) return;
        if(root.ch != '$'){
            // it is a leaf node
            System.out.println(" (%c,%d) ---> code : %s | No of total bits : %d".formatted(root.ch,root.freq,code,root.freq*code.length()));
        }else{
            printHuffmanCodes(root.left,code+"0");
            printHuffmanCodes(root.right,code+"1");
        }
    }

    public static void testHuffmanCoding(){
//        HashMap<Character,Integer> charFreq = new HashMap<>(Map.of('a',5,'d',13,'c',12,'b',9,'e',16,'f',45));
        HashMap<Character,Integer> charFreq = new HashMap<>(Map.of('a',1,'b',2,'c',2,'d',2,'e',3));
        HuffmanNode root = buildHuffmanBT(charFreq);
        System.out.println("Printing Huffman codes of each char: ");
        printHuffmanCodes(root,"");
    }

    public static void main(String[] args) {
        testActivitySelectionProblem();
        testFractionalKnapsack();
        testJobSequencingMaximumProfit();
        testHuffmanCoding();
    }

}
