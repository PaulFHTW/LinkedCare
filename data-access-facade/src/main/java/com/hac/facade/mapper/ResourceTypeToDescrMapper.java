package com.hac.facade.mapper;

import org.springframework.stereotype.Component;

public class ResourceTypeToDescrMapper {

    public static int mapType(String type) {
        switch (type) {
            case "Appointment": return 6;
            case "Observation": return 5;
            case "Conditopn": return 8;
            default: return 0;
        }
    }
}
