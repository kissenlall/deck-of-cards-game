package com.homework.game.model;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Getter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class EntityWithUUID {

    @Id @Type(type = "pg-uuid")
    @Getter
    private UUID id;

    public EntityWithUUID() {
        this.id = UUID.randomUUID();
    }
}