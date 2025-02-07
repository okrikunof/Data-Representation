package Set;

public class Set {
    private CycledList cycledList;

    public Set() {
        cycledList = new CycledList();
    }

    public Set(Set set) {
        this.cycledList = new CycledList(set.cycledList);
    }
}
