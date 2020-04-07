package cs301.Soccer;

import android.util.Log;
import cs301.Soccer.soccerPlayer.SoccerPlayer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Soccer player database -- presently, all dummied up
 *
 * @author *** Vandan Bhargava ***
 * @version *** put date of completion here ***
 *
 */
public class SoccerDatabase implements SoccerDB {

    private HashMap<String, SoccerPlayer> map = new HashMap<String, SoccerPlayer>();

    /**
     * add a player
     *
     * @see SoccerDB#addPlayer(String, String, int, String)
     */

    public String getKey(String f, String l){
        return l + "#" + f;
    }
    @Override
    public boolean addPlayer(String firstName, String lastName,
                             int uniformNumber, String teamName) {
        String key = getKey(firstName, lastName);
        if(map.containsKey(key)){
            return false;
        } else {
            SoccerPlayer newP = new SoccerPlayer(firstName, lastName, uniformNumber, teamName);
            map.put(key, newP);
            return true;
        }
    }

    /**
     * remove a player
     *
     * @see SoccerDB#removePlayer(String, String)
     */
    @Override
    public boolean removePlayer(String firstName, String lastName) {
        String key = getKey(firstName, lastName);
        if(map.containsKey(key)){
            map.remove(key);
            return true;
        }
        return false;
    }

    /**
     * look up a player
     *
     * @see SoccerDB#getPlayer(String, String)
     */
    @Override
    public SoccerPlayer getPlayer(String firstName, String lastName) {
        String key = getKey(firstName, lastName);
        if(map.containsKey(key)){
            return map.get(key);
        }
        return null;
    }

    /**
     * increment a player's goals
     *
     * @see SoccerDB#bumpGoals(String, String)
     */
    @Override
    public boolean bumpGoals(String firstName, String lastName) {
        String key = getKey(firstName, lastName);
        if(map.containsKey(key)){
            map.get(key).bumpGoals();
            return true;
        }
        return false;
    }

    /**
     * increment a player's assists
     *
     * @see SoccerDB#bumpAssists(String, String)
     */
    @Override
    public boolean bumpAssists(String firstName, String lastName) {
        String key = getKey(firstName, lastName);
        if(map.containsKey(key)){
            map.get(key).bumpAssists();
            return true;
        }
        return false;
    }

    /**
     * increment a player's shots
     *
     * @see SoccerDB#bumpShots(String, String)
     */
    @Override
    public boolean bumpShots(String firstName, String lastName) {
        String key = getKey(firstName, lastName);
        if(map.containsKey(key)){
            map.get(key).bumpShots();
            return true;
        }
        return false;
    }

    /**
     * increment a player's saves
     *
     * @see SoccerDB#bumpSaves(String, String)
     */
    @Override
    public boolean bumpSaves(String firstName, String lastName) {
        String key = getKey(firstName, lastName);
        if(map.containsKey(key)){
            map.get(key).bumpSaves();
            return true;
        }
        return false;
    }

    /**
     * increment a player's fouls
     *
     * @see SoccerDB#bumpFouls(String, String)
     */
    @Override
    public boolean bumpFouls(String firstName, String lastName) {
        String key = getKey(firstName, lastName);
        if(map.containsKey(key)){
            map.get(key).bumpFouls();
            return true;
        }
        return false;
    }

    /**
     * increment a player's yellow cards
     *
     * @see SoccerDB#bumpYellowCards(String, String)
     */
    @Override
    public boolean bumpYellowCards(String firstName, String lastName) {
        String key = getKey(firstName, lastName);
        if(map.containsKey(key)){
            map.get(key).bumpYellowCards();
            return true;
        }
        return false;
    }

    /**
     * increment a player's red cards
     *
     * @see SoccerDB#bumpRedCards(String, String)
     */
    @Override
    public boolean bumpRedCards(String firstName, String lastName) {
        String key = getKey(firstName, lastName);
        if(map.containsKey(key)){
            map.get(key).bumpRedCards();
            return true;
        }
        return false;
    }

    /**
     * tells the number of players on a given team
     *
     * @see SoccerDB#numPlayers(String)
     */
    @Override
    // report number of players on a given team (or all players, if null)
    public int numPlayers(String teamName) {
        if(teamName == null){
            return map.size();
        } else {
            int num = 0;
            for (HashMap.Entry<String, SoccerPlayer> entry : map.entrySet()) {
                if(entry.getValue().getTeamName().equals(teamName)){
                    num++;
                }
            }
            return num;
        }
    }

    /**
     * gives the nth player on a the given team
     *
     * @see SoccerDB#playerNum(int, String)
     */
    // get the nTH player
    @Override
    public SoccerPlayer playerNum(int idx, String teamName) {
        if(!map.isEmpty()){
            if(teamName == null){
                Set<String> mapSet = map.keySet();
                Object[] keys = mapSet.toArray();
                if(idx>=map.size()){
                    return null;
                } else {
                    return map.get(keys[idx]);
                }
            } else {
                Set<String> mapSet = map.keySet();
                String[] keys = (String[]) mapSet.toArray();
                ArrayList<SoccerPlayer> list = new ArrayList<SoccerPlayer>();
                for(int i = 0; i<keys.length; i++){
                    if(map.get(keys[i]).getTeamName().equals(teamName)){
                        list.add(map.get(keys[i]));
                    }
                }
                if(idx>=list.size()){
                    return null;
                } else {
                    return list.get(idx);
                }
            }
        }
        return null;
    }

    /**
     * reads database data from a file
     *
     * @see SoccerDB#readData(java.io.File)
     */
    // read data from file
    @Override
    public boolean readData(File file){
        Scanner sc;
        try{
            sc = new Scanner(file);
        } catch (FileNotFoundException e){
            return false;
        }
        while(sc.hasNextLine()){
            String first = sc.nextLine();
            String last = sc.nextLine();
            int uniform = Integer.parseInt(sc.nextLine());
            String team = sc.nextLine();
            int goals = Integer.parseInt(sc.nextLine());
            int assists = Integer.parseInt(sc.nextLine());
            int shots = Integer.parseInt(sc.nextLine());
            int saves = Integer.parseInt(sc.nextLine());
            int fouls = Integer.parseInt(sc.nextLine());
            int yellowCards = Integer.parseInt(sc.nextLine());
            int redCards = Integer.parseInt(sc.nextLine());
            String key = getKey(first, last);
            if(!map.containsKey(key)){
                SoccerPlayer sp = new SoccerPlayer(first, last, uniform, team);
                for(int i = 0; i<goals; i++){
                    sp.bumpGoals();
                }
                for(int i = 0; i<assists; i++){
                    sp.bumpAssists();
                }
                for(int i = 0; i<shots; i++){
                    sp.bumpShots();
                }
                for(int i = 0; i<saves; i++){
                    sp.bumpSaves();
                }
                for(int i = 0; i<fouls; i++){
                    sp.bumpFouls();
                }
                for(int i = 0; i<yellowCards; i++){
                    sp.bumpYellowCards();
                }
                for(int i = 0; i<redCards; i++){
                    sp.bumpRedCards();
                }
                map.put(key, sp);
            }
        }

        return file.exists();
    }

    /**
     * write database data to a file
     *
     * @see SoccerDB#writeData(java.io.File)
     */
    // write data to file
    @Override
    public boolean writeData(File file){
        PrintWriter pw;
        try{
            pw = new PrintWriter(file);
        } catch (FileNotFoundException e){
            return false;
        }
        for (HashMap.Entry<String, SoccerPlayer> entry : map.entrySet()) {
            pw.println(logString(entry.getValue().getFirstName()));
            pw.println(logString(entry.getValue().getLastName()));
            pw.println(logString("" + entry.getValue().getUniform()));
            pw.println(logString(entry.getValue().getTeamName()));
            pw.println(logString("" + entry.getValue().getGoals()));
            pw.println(logString("" + entry.getValue().getAssists()));
            pw.println(logString("" + entry.getValue().getShots()));
            pw.println(logString("" + entry.getValue().getSaves()));
            pw.println(logString("" + entry.getValue().getFouls()));
            pw.println(logString("" + entry.getValue().getYellowCards()));
            pw.println(logString("" + entry.getValue().getRedCards()));
        }
        pw.flush();
        pw.close();
        return true;
    }

    /**
     * helper method that logcat-logs a string, and then returns the string.
     * @param s the string to log
     * @return the string s, unchanged
     */
    private String logString(String s) {
        Log.i("write string", s);
        return s;
    }

    /**
     * returns the list of team names in the database
     *
     * @see cs301.Soccer.SoccerDB#getTeams()
     */
    // return list of teams
    @Override
    public HashSet<String> getTeams() {
        HashSet<String> teamList = new HashSet<String>();
        for (HashMap.Entry<String, SoccerPlayer> entry : map.entrySet()) {
            if(!teamList.contains(entry.getValue().getTeamName())){
                teamList.add(entry.getValue().getTeamName());
            }
        }
        return teamList;
    }
}
