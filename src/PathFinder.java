import java.util.*;

/**
 * Created by 远志 on 12/22/2016.
 */
public class PathFinder {
    public Solution findShortestPath(Graph graph,int source, int target) {

        if(false){
            //wrong input;
        }
        List<Node> nodes=connectLinksToNodes(graph);
        return findShortestPath(nodes,source,target);
    }

    public Solution findShortestPath(List<Node> nodes,int source,int target) {
        Solution solution=new Solution(new ArrayList<>(),(double)Integer.MAX_VALUE);
        int length=0;
        Map<Integer,Solution> map = new HashMap<>();
        map.put(target,new Solution(new ArrayList<>(),0d));
        Set<Integer> path=new HashSet<>();
        path.add(source);
        return helper(nodes,source,target,map,path);


    }

    private Solution helper(List<Node> nodes, int source, int target, Map<Integer, Solution> map, Set<Integer> path) {
        if(map.containsKey(source)) {
            return map.get(source);
        }
        Solution solution=new Solution(new ArrayList<>(),(double)Integer.MAX_VALUE);
        for(Connection connection : nodes.get(source).connections) {
            int newSource=connection.target;
            if(path.contains(newSource)){
                continue;
            }
            Set<Integer> newPath=new HashSet<>(path);
            newPath.add(newSource);
            Solution newSolution = helper(nodes,newSource,target,map, newPath);
            if(newSolution.length+connection.length<solution.length) {
                solution.length= newSolution.length+connection.length;
                solution.path = new ArrayList<>();
                solution.path.add(source);
                solution.path.addAll(newSolution.path);
            }
        }
        map.put(source,solution);
        return solution;
    }



    public List<Node> connectLinksToNodes(Graph graph) {
        List<Node> nodes =new ArrayList<>();
        int numOfNodes=graph.numOfNodes;
        int numOfLinks=graph.numOfLinks;
        for (int i = 0; i < numOfNodes; i++) {
            nodes.add(new Node(i));
        }
        for (int i = 0; i <numOfLinks ; i++) {
            nodes.get(graph.links.get(i).node1).addConnection(graph.links.get(i).node2,graph.links.get(i).length,i);
            nodes.get(graph.links.get(i).node2).addConnection(graph.links.get(i).node1,graph.links.get(i).length,i);
        }

        return nodes;
    }
}
