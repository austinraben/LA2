package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SongTracker {
        private LinkedList<Song> recent;
        private HashMap<Song, Integer> count;
        private Playlist recentSongs;
        private Playlist frequentSongs;

        public SongTracker() {
                recent = new LinkedList<>();
                count = new HashMap<>();
                recentSongs = new Playlist("Recent Songs");
                frequentSongs = new Playlist("Frequent Songs");
        }
        
        public Playlist getRecentSongsPlaylist() {
            return recentSongs;
        }

        public Playlist getFrequentSongsPlaylist() {
            return frequentSongs;
        }

        public void playSong(Song song) {
                song.incrementPlayCount();
                count.put(song, song.getPlayCount());
                recent.remove(song);
                recent.addFirst(song);
                if(recent.size()> 10) {
                        recent.removeLast();
                }
                recentSongs.getSongList(new ArrayList<>(recent));

                List<Map.Entry<Song, Integer>> mapEntries = new ArrayList<>(count.entrySet());
                mapEntries.sort((s1, s2) -> s2.getValue().compareTo(s1.getValue()));

                List<Song> frequent = new ArrayList<>();
        for (int i = 0; i < Math.min(10, mapEntries.size()); i++) {
            frequent.add(mapEntries.get(i).getKey());
        }
        frequentSongs.getSongList(frequent);
        }
}