public class RamblersSearch extends Search {
    private TerrainMap map; // the map we want to search
    private Coords goal; // goal

    public TerrainMap getMap() {
        return map;
    }

    public Coords getGoal() {
        return goal;
    }

    public RamblersSearch(TerrainMap m, Coords g) {
        map = m;
        goal = g;
    }
}
