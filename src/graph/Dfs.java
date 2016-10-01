package graph;

import jdk.nashorn.internal.objects.NativeUint8Array;

import java.util.ArrayList;
import java.util.List;

import static graph.Main.DEFAULT_HEIGHT;
import static graph.Main.DEFAULT_WIDTH;

public class Dfs {

    public static List<Tile> calculateDFS(Maze maze) {
        // TODO implement depth first search
        // Return a path from the start to the end

        Tile start = maze.getStartTile();
        Dfs dfs = new Dfs();

        return dfs.implementDFS(start, maze);
    }


    ArrayList<Tile> implementDFS(Tile start, Maze maze) {
           if (start.isGoal(maze.getGoalTile().getX(), maze.getGoalTile().getY())) {
                  ArrayList<Tile> path = new ArrayList<Tile>();
                  path.add(start);
                  return  path;

           }
           start.visit();
           for(Tile tile : maze.getAdjacentTiles(start.getX(), start.getY())) {
                   if(!tile.isVisited() && tile.isTraversable()) {
                          ArrayList<Tile> res = implementDFS(tile, maze);
                          if (res != null) {
                              res.add(0, start);
                              return res;
                          }
                   }


           }

           return null;


    }
}
