package org.shuerlink.model.dto;

public class WallpaperCategory {
    public Boolean SHU = true;
    public Boolean others = false;

    public Boolean isSHU() {
        return SHU;
    }

    public void setSHU(Boolean SHU) {
        this.SHU = SHU;
    }

    public Boolean isOthers() {
        return others;
    }

    public void setOthers(Boolean others) {
        this.others = others;
    }
}
