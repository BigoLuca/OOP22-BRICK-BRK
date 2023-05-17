package brickbreaker;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

import brickbreaker.common.Mode;
import brickbreaker.model.rank.PlayerStats;

public class ResourceLoader {

    private static final Integer MAPCOL = 20;
    private static final Integer MAPLINE = 10;

    private static ResourceLoader instance;
    private String mapsPath;
    private String ranksPath;
    private String sep;
    private String previousMap;

    public static ResourceLoader getInstance() {
        if (instance == null) {
            instance = new ResourceLoader();
        }

        return instance;
    }

    private ResourceLoader() {
        this.previousMap = "";
        this.sep = File.separator + File.separator;
        this.mapsPath = "." + sep + "app" + sep + "src" + sep + "main" + sep + "resources" + sep + "mapsFile";
        this.ranksPath = "." + sep + "app" + sep + "src" + sep + "main" + sep + "resources" + sep + "ranks";
    }

    public List<String> getCampaignMaps() {

        return Arrays.asList(new File(mapsPath).list());
    }

    public List<Integer> loadMap(final String fileName) {

        List<Integer> mapLayout = new ArrayList<Integer>();
        String sep = File.separator + File.separator;

        if (!this.previousMap.equals(fileName)) {
            try (Scanner sc = new Scanner(new File(this.mapsPath + sep + fileName))) {
                while (sc.hasNextInt()) {
                    mapLayout.add(sc.nextInt());
                }
                sc.close();
                this.previousMap = fileName;
            } catch (FileNotFoundException e) {
                this.previousMap = "";
                e.printStackTrace();
                return List.of();
            }

            if (mapLayout.size() != MAPCOL * MAPLINE) {
                return List.of();
            }
        }
        return mapLayout;
    }

    @SuppressWarnings("unchecked")
    public SortedSet<PlayerStats> getRank(final Mode mode) {

        SortedSet<PlayerStats> rank = new TreeSet<>();
        String file = mode.equals(Mode.CAMPAIGN) ? "levels.txt" : "endless.txt";
        String path = this.ranksPath + sep + file;
        
        try (ObjectInput input = new ObjectInputStream(
            new BufferedInputStream(new FileInputStream(path)))) {
                rank.addAll((SortedSet<PlayerStats>) input.readObject());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rank;
    }

    public void writeRank(final SortedSet<PlayerStats> rank, final Mode mode) {
        String file = mode.equals(Mode.CAMPAIGN) ? "levels.txt" : "endless.txt";
        String path = this.ranksPath + sep + file;
        try(ObjectOutput output = new ObjectOutputStream(
            new BufferedOutputStream(new FileOutputStream(path)))) {
                output.writeObject(rank);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
