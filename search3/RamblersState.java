import java.util.ArrayList;

import static java.lang.Math.max;


public class RamblersState extends SearchState{
    private Coords coords;

    public Coords getCoords(){return coords;}

    public RamblersState(Coords coords, int lc) {
        this.coords = coords;
        this.localCost = lc;
    }

    @Override
    boolean goalPredicate(Search searcher) {
        RamblersSearch rs2 = (RamblersSearch) searcher;
        Coords tar = rs2.getGoal();
        return coords.getx() == tar.getx() && coords.gety() == tar.gety();
    }

    @Override
    ArrayList<SearchState> getSuccessors(Search searcher) {
        RamblersSearch rsearcher = (RamblersSearch) searcher;
        TerrainMap map = rsearcher.getMap();
        int[][] tmap = map.getTmap();
        int width = map.getWidth();
        int depth = map.getDepth();
        ArrayList<SearchState> succs = new ArrayList<>();
        int y = coords.gety();
        int x = coords.getx();
        if (coords.getx() > 0) {
            int lc = max(tmap[y][x - 1] - tmap[y][x], 0) + 1;
            succs.add((SearchState) new RamblersState(new Coords(y, x - 1), lc));
        }
        if (coords.gety() > 0) {
            int lc = max(tmap[y - 1][x] - tmap[y][x], 0) + 1;
            succs.add((SearchState) new RamblersState(new Coords(y - 1, x), lc));
        }
        if (coords.getx() < width - 1) {
            int lc = max(tmap[y][x + 1] - tmap[y][x], 0) + 1;
            succs.add((SearchState) new RamblersState(new Coords(y, x + 1), lc));
        }
        if (coords.gety() < depth - 1) {
            int lc = max(tmap[y + 1][x] - tmap[y][x], 0) + 1;
            succs.add((SearchState) new RamblersState(new Coords(y + 1, x), lc));
        }
        return succs;
    }

    @Override
    boolean sameState(SearchState s2) {
        RamblersState rs2 = (RamblersState) s2;
        Coords coords2 = rs2.getCoords();
        return coords.getx() == coords2.getx() && coords.gety() == coords2.gety();
    }

    @Override
    public String toString() {
        return coords.gety() + ", " + coords.getx();
    }
}
