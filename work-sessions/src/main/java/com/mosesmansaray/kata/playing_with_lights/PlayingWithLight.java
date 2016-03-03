package com.mosesmansaray.kata.playing_with_lights;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by moses.mansaray on 01/03/2016.
 */
public class PlayingWithLight {
  List<String> rowData;
  BitSet switchGridBitset;

  public static void main(String[] args){
    final long startTime = System.nanoTime();
    PlayingWithLight playingWithLight = new PlayingWithLight("lots_of_switches.txt", true);
    playingWithLight.toggleRows();
    System.out.println(playingWithLight.getTotalNumberOfSwitchesOn());
    System.out.println("Took: " + TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime) );
  }

  public BitSet getSwitchGridBitset() {
    return switchGridBitset;
  }



  /**
   * initialise from raw string
   *
   * @param rawData representation of the grid and child's run
   */
  public PlayingWithLight(String rawData) {
    rowData = Arrays.asList(rawData.split("\n"));
    switchGridBitset = new BitSet(Integer.parseInt(rowData.get(0)));
  }


  /**
   * Initialize grid from file
   * @param filePath representation of the grid and child's run
   * @param fromPath
   */
  public PlayingWithLight(String filePath, boolean fromPath) {
    try {
      String path = getClass().getClassLoader().getResource(filePath).getPath();
      rowData = Files.readAllLines(Paths.get(path), Charset.defaultCharset());
      switchGridBitset = new BitSet(Integer.parseInt(rowData.get(0)));
    } catch (IOException e) {
      // log?throw?alternative
    }
  }

  public void toggleRows() {
    for (int i = 1; i < rowData.size(); i++) {
      String[] split = rowData.get(i).split(" ");
      toggleRange(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
    }
  }

  public int getTotalNumberOfSwitchesOn() {
//    switchGridBitset.cardinality();
//    int count = 0;
//    for (int i = 0; i < switchGridBitset.length; i++) {
//        if (switchGridBitset[i]){
//          count ++;
//        }
//    }
    return switchGridBitset.cardinality();
  }


  public void toggleRange(int startingPos, int endPos) {

//    if (startingPos <= endPos) {
//      int i = startingPos;
//      while (i <= endPos) {
//        switchGridBitset[i] = !switchGridBitset[i];
//        i++;
//      }
//    }else {
//      int i = startingPos;
//      while (i >= endPos) {
//        switchGridBitset[i] = !switchGridBitset[i];
//        i--;
//      }
//
//    }
    if (startingPos > endPos) {
      int temp = startingPos;
      startingPos = endPos;
      endPos = temp;
    }
    switchGridBitset.flip(startingPos, endPos+1);
  }
}
