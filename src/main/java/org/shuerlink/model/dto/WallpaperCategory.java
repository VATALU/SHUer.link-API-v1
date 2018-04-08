package org.shuerlink.model.dto;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

public class WallpaperCategory {
    public Boolean shu = true;
    public Boolean others = false;

    public Boolean isShu() {
        return shu;
    }

    public void setShu(Boolean shu) {
        this.shu = shu;
    }

    public Boolean isOthers() {
        return others;
    }

    public void setOthers(Boolean others) {
        this.others = others;
    }

    @Override
    public String toString() {
        return "WallpaperCategory{" +
                "SHU=" + shu +
                ", others=" + others +
                '}';
    }
}
