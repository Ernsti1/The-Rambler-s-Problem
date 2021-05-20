public class RunRamblersBB {

    public static void main(String[] arg) {

        TerrainMap tmc = new TerrainMap("diablo.pgm");
        Coords start = new Coords(7, 0), goal = new Coords(223, 198);

        RamblersSearch searcher = new RamblersSearch(tmc, goal);
        SearchState initState = (SearchState) new RamblersState(start, 0);

        String res_bb = searcher.runSearch(initState, "branchAndBound");
        System.out.println(res_bb);
    }
}
