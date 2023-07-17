package com.gdsc.CGLH.entity;

public enum WasteStatus {
    PERMIT("승인"), WAITING("대기"), REFUSE("거절");
    String description;

    WasteStatus(String description) {
        this.description = description;
    }
}
