package graph;

import jdk.nashorn.internal.objects.NativeUint8Array;

import java.util.ArrayList;
import java.util.List;
import java.util.*;
import static graph.Main.DEFAULT_HEIGHT;
import static graph.Main.DEFAULT_WIDTH;

public class Dfs {
    /*
    public static List<Tile> calculateDFS(Maze maze) {
        // TODO implement depth first search
        // Return a path from the start to the end

        Tile start = maze.getStartTile();
        Dfs dfs = new Dfs();

        return dfs.implementDFS(start, maze);
    }


    ArrayList<Tile> implementDFS(Tile start, Maze maze) {
           // check whether the tile the goal tile
           if (start.isGoal(maze.getGoalTile().getX(), maze.getGoalTile().getY())) {
                  ArrayList<Tile> path = new ArrayList<Tile>();
                  path.add(start);    // add tiles into the path backward, which means from the goal backtrace to the
               // start tile
                  return  path;

           }
           // lable the tile visited
           start.visit();

           // go to  all the adjacent tiles which are tranversable and not visited yet
           for(Tile tile : maze.getAdjacentTiles(start.getX(), start.getY())) {
                   if(!tile.isVisited() && tile.isTraversable()) {
                       ArrayList<Tile> res = implementDFS(tile, maze);//since we need to do all the steps above
                       // again, we could use recursion. If we go deeper and deeper and find the goal, start build
                       // the path. If we exhaust all the edges and could not find the goal, return null.

                       if (res != null) {  // Only the correct path will pass this condition. Only when the tile
                              // is located on the path from start to goal, it will return the path, which is non null.

                              res.add(0, start);  // add start tile in the beginning of the path.
                              return res;
                       }
                   }


           }
           return null;

    }
   */
    public static List<Tile> calculateDFS(Maze maze) {
        // TODO implement breadth first search
        // Return a path from the start to the end
        Stack<Tile> stack = new Stack<Tile>();
        stack.add(maze.getStartTile());
        maze.getStartTile().visit();
        maze.getStartTile().setParent(null);
        while(!stack.isEmpty()) {
            Tile top = stack.peek();

            for (Tile adjTile : maze.getAdjacentTiles(top)) {
                if (adjTile.isTraversable() && !adjTile.isVisited()) {
                    // set parent first
                    adjTile.setParent(top);
                    // check whether it is the goal tile. Why check goal after setParent? we do not want to call
                    // getpath() before we set parent of the goal tile. It would not get the path.
                    if (adjTile == maze.getGoalTile()) {
                        List<Tile> path = getpath(adjTile, maze );
                        return path;
                    }
                    // add adjTile into stack
                    stack.push(adjTile);
                    adjTile.visit();
                    break; // as long as we push one tile into stack, we go back to stack.peek(). // the first
                    // difference between DFS and DFS except change from queue to stack, queue.pull to stakc.peek.
                    // BFS queue add all the adjacent tiles first, DFS stack: once it add one tile on stack, it
                    // will check the adjacent tiles of the new top tile of the stack.
                    // BFS: add the whole level and check each one, for each one, add the whole level and check each
                    // one. DFS: add one and check next level.Then add one and check next level.
                }
            }
            // the second difference between DFS and DFS except change from queue to stack, queue.pull to stakc.peek.
            if (top == stack.peek()) { // which means we did not add any new tile to the stack
                stack.pop();
            }

        }

        return null;
    }

    public static List<Tile> getpath(Tile goal, Maze maze) {
        ArrayList<Tile> path = new ArrayList<Tile>();
        path.add(0, goal);
        Tile temp = goal;  // travese all the parent starting from goal
        while (temp.getParent() != maze.getStartTile()) {
            path.add(0, temp.getParent());
            temp = temp.getParent();  // traverse to next parent.
        }
        path.add(0, maze.getStartTile());
        return path;
    }

}