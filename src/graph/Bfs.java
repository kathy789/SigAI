package graph;


import java.util.*;
/*
// notes for BFS:
   add the fist tile to the queue and lable it visited.
   while(queue is not empty) {
        dequeue the front tile.
        add all the adjacent tiles  which is tranversable and not visited yet to the queue.{
               set parent for the tile;
               check whether the tile is goal , if it is, return the path of all the parents;
               add the tile to queue;
               lable the tile visited;
        }
   }
// set parent when we add the tile to the queue.
// checking  whether it is goal tile when we enqueue is faster than checking that when we dequeue.
// return the path from start tile to end tile, not all the tiles we visited (like the queue).
*/
public class Bfs {
    public static List<Tile> calculateBFS(Maze maze) {
        // TODO implement breadth first search
        // Return a path from the start to the end
        Queue<Tile> queue = new LinkedList<Tile>();
        queue.add(maze.getStartTile());
        maze.getStartTile().visit();
        maze.getStartTile().setParent(null);
        while(!queue.isEmpty()) {
            Tile front= queue.poll();

            for (Tile adjTile : maze.getAdjacentTiles(front)) {
                if (adjTile.isTraversable() && !adjTile.isVisited()) {
                    // set parent first
                    adjTile.setParent(front);
                    // check whether it is the goal tile. Why check goal after setParent? we do not want to call
                    // getpath() before we set parent of the goal tile. It would not get the path.
                    if (adjTile == maze.getGoalTile()) {
                        List<Tile> path = getpath(front, maze );
                        return path;
                    }
                    // add adjTile into queue
                    queue.add(adjTile);
                    adjTile.visit();
                }
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
