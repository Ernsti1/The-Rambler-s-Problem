public class RunRamblersAstart {

    public static void main(String[] arg) {

        TerrainMap tmc = new TerrainMap("diablo.pgm");
        Coords start = new Coords(7, 0), goal = new Coords(223, 198);

        RamblersSearch searcher = new RamblersSearch(tmc, goal);
        SearchState initState = (SearchState) new RamblersState(start, 0, 0, "combinations of Euclidean and height difference");

        String res_a_star = searcher.runSearch(initState, "AStar");
        System.out.println(res_a_star);
    }
}
