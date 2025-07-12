package util;

public class MilestoneData {
    private final String customId;
    private final int level;


    public MilestoneData(String customId, int level) {
        this.customId = customId;
        this.level = level;
    }


    public String getCustomId() { return customId; }
    public int getLevel() { return level; }
}
