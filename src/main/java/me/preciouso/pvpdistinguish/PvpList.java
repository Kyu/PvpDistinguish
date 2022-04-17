package me.preciouso.pvpdistinguish;

import java.util.ArrayList;
import java.util.UUID;

// Singleton to represent the list of players in PvP mode
public class PvpList {
    private static PvpList instance;
    private final ArrayList<UUID> pvpList = new ArrayList<>();

    private PvpList() {
    }

    public static PvpList getList()
    {
        if (instance == null) {
            instance = new PvpList();
        }

        return instance;
    }

    public boolean inPvp(UUID id) {
        return (pvpList.contains(id));
    }

    public boolean addPvp(UUID id) {
        if (!inPvp(id)) {
            pvpList.add(id);
            return true;
        }

        return false;
    }

    public boolean removePvp(UUID id) {
        if (inPvp(id)) {
            pvpList.remove(id);
            return true;
        }

        return false;
    }

    public ArrayList<UUID> getPvpList() {
        return pvpList;
    }
}
