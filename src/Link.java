
/**
 * Created by 远志 on 12/22/2016.
 */
public class Link {
    int node1;
    int node2;
    boolean isCongested;
    Double length;
    public Link(int node1, int node2, Double length) {
        this(node1,node2,length,true);
    }
    public Link(int node1, int node2, Double length, boolean isCongested) {
        this.node1=node1;
        this.node2=node2;
        this.length=length;
        this.isCongested=isCongested;
    }


}
