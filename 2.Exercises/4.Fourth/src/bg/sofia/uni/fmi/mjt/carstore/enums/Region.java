package bg.sofia.uni.fmi.mjt.carstore.enums;

public enum Region {
    SOFIA("CB"), BURGAS("A"), VARNA("B"), PLOVDIV("PB"), RUSE("P"), GABROVO("EB"), VIDIN("BH"), VRATSA("BP");

    private String value;
    private int carsInRegion;

    Region(String value) {
        this.value = value;
        this.carsInRegion = 1000;
    }

    public String getPrefix() {
        return value;
    }

    public int getCarsInRegion() {
        carsInRegion ++;
        return carsInRegion - 1;
    }
}
