package org.houstondragonacademy.archer.dao.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "children")
@Builder
public class Children {
    String name;
    String gradeLevel;
    String fluencyChinese;
    String notes;
}
