import java.util.ArrayList;

import static java.lang.Math.*;


public class RamblersState extends SearchState {
    private Coords coords;
    private String estimate_way;

    public Coords getCoords() {
        return coords;
    }

    public RamblersState(Coords coords, int lc, int rc, String estimate_way) {
        this.coords = coords;
        this.localCost = lc;
        this.estRemCost = rc;
        this.estimate_way = estimate_way;
    }

    private int estbetween(int[][] tmap, Coords src, Coords tar) {
        if ("Manhattan".equals(estimate_way)) {
            return abs(src.gety() - tar.gety()) + abs(src.getx() - tar.getx());
        } else if ("Euclidean".equals(estimate_way)) {
            return (int)sqrt(pow(src.gety() - tar.gety(), 2) + pow(src.getx() - tar.getx(), 2.));
        } else if ("height difference".equals(estimate_way)) {
            return max(tmap[tar.gety()][tar.getx()] - tmap[src.gety()][src.getx()], 0);
        } else if ("combinations of Manhattan and height difference".equals(estimate_way)) {
            return abs(src.gety() - tar.gety()) + abs(src.getx() - tar.getx()) + max(tmap[tar.gety()][tar.getx()] - tmap[src.gety()][src.getx()], 0);
        } else if("combinations of Euclidean and height difference".equals(estimate_way)){
            return abs(src.gety() - tar.gety()) + abs(src.getx() - tar.getx()) + max(tmap[tar.gety()][tar.getx()] - tmap[src.gety()][src.getx()], 0);
        }
        return 0;
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
            succs.add((SearchState) new RamblersState(new Coords(y, x - 1), lc, estbetween(tmap, coords, rsearcher.getGoal()), estimate_way));
        }
        if (coords.gety() > 0) {
            int lc = max(tmap[y - 1][x] - tmap[y][x], 0) + 1;
            succs.add((SearchState) new RamblersState(new Coords(y - 1, x), lc, estbetween(tmap, coords, rsearcher.getGoal()), estimate_way));
        }
        if (coords.getx() < width - 1) {
            int lc = max(tmap[y][x + 1] - tmap[y][x], 0) + 1;
            succs.add((SearchState) new RamblersState(new Coords(y, x + 1), lc, estbetween(tmap, coords, rsearcher.getGoal()), estimate_way));
        }
        if (coords.gety() < depth - 1) {
            int lc = max(tmap[y + 1][x] - tmap[y][x], 0) + 1;
            succs.add((SearchState) new RamblersState(new Coords(y + 1, x), lc, estbetween(tmap, coords, rsearcher.getGoal()), estimate_way));
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
        return "(" + coords.gety() + ", " + coords.getx() + ")";
    }
}
