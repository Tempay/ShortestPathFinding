import java.util.*;

/**
 * Created by 远志 on 12/23/2016.
 */
public class CongestedPathFinder {

    double delayConstant=10;
    int precise=100;

    public Map<Set<Integer>,Integer> findCongestedShortedPath(Graph graph, int source, int target) {
        List<Node> nodes=connectLinksToNodes(graph);
        List<Solution> solutions= new ArrayList<>();
        for (int i = 0; i <precise ; i++) {
            Solution thisSolution=findShortestPath(nodes,source,target);
            for (int j = 0; j <thisSolution.path.size() ; j++) {
                int linkId=thisSolution.path.get(j);
                if(!graph.links.get(linkId).isCongested){
                    continue;
                }
                graph.links.get(linkId).length+=delayConstant;
                nodes.get(graph.links.get(linkId).node1).changeConnection(graph.links.get(linkId).length,linkId);
                nodes.get(graph.links.get(linkId).node2).changeConnection(graph.links.get(linkId).length,linkId);
            }
            solutions.add(thisSolution);
        }
        Map<Set<Integer>,Integer> result=new HashMap<>();
        for(Solution solution:solutions) {
            Set<Integer> thisPath=new HashSet<>(solution.path);
            if(result.containsKey(thisPath)){
                result.put(thisPath,result.get(thisPath)+1);
            } else {
                result.put(thisPath,1);
            }
        }
        return result;
    }

    public Solution findShortestPath(List<Node> nodes,int source,int target) {

        Solution solution=new Solution(new ArrayList<>(),(double)Integer.MAX_VALUE);
        int length=0;
        Map<Integer,Solution> map = new HashMap<>();
        map.put(target,new Solution(new ArrayList<>(),0d));
        Set<Integer> path=new HashSet<>();
        //path.add(source);
        return helper(nodes,source,target,map,path);


    }

    private Solution helper(List<Node> nodes, int source, int target, Map<Integer, Solution> map, Set<Integer> path) {
        if(map.containsKey(source)) {
            return map.get(source);
        }
        Solution solution=new Solution(new ArrayList<>(),(double)Integer.MAX_VALUE);
        for(Connection connection : nodes.get(source).connections) {
            int newSource=connection.target;
            int thisPath=connection.id;
            if(path.contains(thisPath)){
                continue;
            }
            Set<Integer> newPath=new HashSet<>(path);
            newPath.add(thisPath);
            Solution newSolution = helper(nodes,newSource,target,map, newPath);
            if(newSolution.length+connection.length<solution.length) {
                solution.length= newSolution.length+connection.length;
                solution.path = new ArrayList<>();
                solution.path.add(thisPath);
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
